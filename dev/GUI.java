import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;


public class GUI extends JFrame {
    JFrame frame = new JFrame("Solar System Simulation");;

    static {
        System.out.println("GUI initialized..");
    }

    public GUI() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 150);

        /*Background*/
        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        add(panel);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        /*Start button*/
        JButton initButton = new JButton("Start");
        initButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                init();
                System.out.println("Simulation initialized..");
            }
        });
        initButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(initButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        /*Exit button*/
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.out.println("Program terminated.");
            }
        });
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(exitButton);


        setLocationRelativeTo(null);
        setVisible(true);
    }

    /*Creates a frame for the solar system visualization*/
    public void init() {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                new GUI();
            }
        };
        frame.addWindowListener(exitListener);

        JPanel panel = new SolarSystem();
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

    }

    public void Planet() {
        // add and draw the planets in order of String name, double mass, double radius, int height, int width, double distance, int x, int y, Color planetBorderColor, Color planetFillColor, JFrame space
        //Planet mercury = new Planet(frame);
    }

    class SolarSystem extends JPanel implements MouseWheelListener, MouseListener, MouseMotionListener {

        private double zoomFactor = 1;
        private double prevZoomFactor = 1;
        private boolean zoomer;
        private boolean dragger;
        private boolean released;
        private double xOffset = 0;
        private double yOffset = 0;
        private int xDiff;
        private int yDiff;
        private Point startPoint;


        public SolarSystem() {
            addMouseWheelListener(this);
            addMouseMotionListener(this);
            addMouseListener(this);



            setBackground(Color.black);
            setBorder(BorderFactory.createLineBorder(Color.red, 4));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);


            if (zoomer) {
                AffineTransform at = new AffineTransform();

                double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
                double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

                double zoomDiv = zoomFactor / prevZoomFactor;

                xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
                yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

                at.translate(xOffset, yOffset);
                at.scale(zoomFactor, zoomFactor);
                prevZoomFactor = zoomFactor;
                g2d.transform(at);
                zoomer = false;
            }

            if (dragger) {
                AffineTransform at = new AffineTransform();
                at.translate(xOffset + xDiff, yOffset + yDiff);
                at.scale(zoomFactor, zoomFactor);
                g2d.transform(at);

                if (released) {
                    xOffset += xDiff;
                    yOffset += yDiff;
                    dragger = false;
                }

            }

            /*Get the center of the screen - this is where Sun will be placed*/
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) (dimension.getWidth() / 2);
            int y = (int) (dimension.getHeight() / 2);
            Ellipse2D sun = new Ellipse2D.Double();
            sun.setFrameFromCenter(x, y, x + 5, y + 5);

            g2d.setColor(Color.lightGray);
            g2d.setStroke(new BasicStroke(4.0f,
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND));

            g2d.draw(sun);
            GradientPaint orangeToWhite = new GradientPaint(0,0,Color.orange,70, 0,Color.white, true);
            g2d.setPaint(orangeToWhite);
            g2d.fill(sun);

            g2d.setColor(Color.red);
            g2d.setStroke(new BasicStroke(1.0f,
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND));

            Ellipse2D mercuryOrbit = new Ellipse2D.Double();
            mercuryOrbit.setFrameFromCenter(x, y, x + Formulas.mercAphelion / 3e6, y + Formulas.mercPerihelion / 8e6);
            g2d.draw(mercuryOrbit);

            Ellipse2D marsOrbit = new Ellipse2D.Double();
            marsOrbit.setFrameFromCenter(x, y, x + Formulas.marsAphelion / 3e6, y + Formulas.marsPerihelion / 8e6);
            g2d.draw(marsOrbit);

            Ellipse2D earthOrbit = new Ellipse2D.Double();
            earthOrbit.setFrameFromCenter(x, y, x + Formulas.earthAphelion / 3e6, y + Formulas.earthPerihelion / 8e6);
            g2d.draw(earthOrbit);

            Ellipse2D venusOrbit = new Ellipse2D.Double();
            venusOrbit.setFrameFromCenter(x, y, x + Formulas.venusAphelion / 3e6, y + Formulas.venusPerihelion / 8e6);
            g2d.draw(venusOrbit);

            Ellipse2D jupiterOrbit = new Ellipse2D.Double();
            jupiterOrbit.setFrameFromCenter(x, y, x + Formulas.jupiterAphelion / 3e6, y + Formulas.jupiterPerihelion / 8e6);
            g2d.draw(jupiterOrbit);

            Ellipse2D saturnOrbit = new Ellipse2D.Double();
            saturnOrbit.setFrameFromCenter(x, y, x + Formulas.saturnAphelion / 3e6, y + Formulas.saturnPerihelion / 8e6);
            g2d.draw(saturnOrbit);

            Ellipse2D uranusOrbit = new Ellipse2D.Double();
            uranusOrbit.setFrameFromCenter(x, y, x + Formulas.uranusAphelion / 3e6, y + Formulas.uranusPerihelion / 8e6);
            g2d.draw(uranusOrbit);

            Ellipse2D neptuneOrbit = new Ellipse2D.Double();
            neptuneOrbit.setFrameFromCenter(x, y, x + Formulas.neptuneAphelion / 3e6, y + Formulas.neptunePerihelion / 8e6);
            g2d.draw(neptuneOrbit);

            //Planet mercury = new Planet(frame);

        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

            zoomer = true;

            //Zoom in
            if (e.getWheelRotation() < 0) {
                zoomFactor *= 1.1;
                repaint();
            }
            //Zoom out
            if (e.getWheelRotation() > 0) {
                zoomFactor /= 1.1;
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point curPoint = e.getLocationOnScreen();
            xDiff = curPoint.x - startPoint.x;
            yDiff = curPoint.y - startPoint.y;

            dragger = true;
            repaint();

        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            released = false;
            startPoint = MouseInfo.getPointerInfo().getLocation();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            released = true;
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }
}
