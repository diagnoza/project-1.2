package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class GUI extends JFrame {

    static {
        System.out.println("GUI initialized..");
    }

    private Universe universe;
    private JFrame solarSimFrame = new JFrame("Solar System Simulation");
    private SolarSystem solarSimPanel;
    private ControlsButtonsPanel buttonPanel;

    public GUI(Universe universe) {

        this.universe = universe;


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
                try {
                    init();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.println("Simulation initialized..");
            }
        });
        initButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        initButton.setFocusable(false);
        panel.add(initButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        /*Exit button*/
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.out.println("Program terminated.");
                System.exit(1);
            }
        });
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setFocusable(false);
        panel.add(exitButton);


        setLocationRelativeTo(null);
        setVisible(true);
    }

    /*Creates a solarSimFrame for the solar system visualization*/
    public void init() throws IOException {
        solarSimFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        solarSimFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                new GUI(universe);
            }
        };
        solarSimFrame.addWindowListener(exitListener);

        solarSimFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.9;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        solarSimPanel = new SolarSystem();
        solarSimFrame.add(solarSimPanel, gbc);


        gbc.weightx = 0.01;
        gbc.gridx = 1;
        buttonPanel = new ControlsButtonsPanel();
        solarSimFrame.add(buttonPanel, gbc);


        solarSimFrame.setLocationRelativeTo(null);
        solarSimFrame.pack();
        solarSimFrame.setVisible(true);

    }


    class SolarSystem extends JPanel implements MouseWheelListener, MouseListener, MouseMotionListener {
        Graphics2D g2d;
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


        public SolarSystem() throws IOException {
            addMouseWheelListener(this);
            addMouseMotionListener(this);
            addMouseListener(this);

            setBackground(Color.black);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g2d = (Graphics2D) g;
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

            //drawCenteredCircle(Graphics2D, x, y, r) draws a filled circle, where (x,y) is
            //the circle's center. Replace x and y with coordinates from each planet's corresponding object.
            //Say we have:
            // Planet mercury = new Planet()
            //Then:
            /*drawCenteredCircle(g2d, universe.spaceObjects.get(1).getPos().getX(),
                     universe.spaceObjects.get(1).getPos().getY(), universe.spaceObjects.get(1).getRadius()/10);

            drawCenteredCircle(g2d, universe.spaceObjects.get(2).getPos().getX(),
                    universe.spaceObjects.get(2).getPos().getY(), universe.spaceObjects.get(2).getRadius());
                    */


            for (SpaceObject so : universe.spaceObjects)
                drawCenteredCircle(g2d, so.getPos().getX(), so.getPos().getY(), so.getRadius(), so.getImg());


            //Both X and Y coordinates are supposed to be calculated beforehand in Celestial.java.
            // */
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

        public void setZoomer(boolean x) {
            zoomer = x;
        }

        public void setDragger(boolean x) {
            dragger = x;
        }

        public void drawCenteredCircle(Graphics2D g, double x, double y, int r, BufferedImage img) {
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int factorX = (int) (x / 6E+7) + (int) (dimension.getWidth() / 2) - (r / 2);
            int factorY = (-1) * (int) (y / 6E+7) + (int) (dimension.getHeight() / 2) - (r / 2);


            //g.fillOval(factorX, factorY, r, r);
            g.drawImage(img, factorX, factorY, r, r, this);
        }

    }

    class ControlsButtonsPanel extends JPanel {
        private Thread t;
        private boolean initialized = false;

        public ControlsButtonsPanel() {

            setBackground(Color.black);
            setBorder(BorderFactory.createLineBorder(Color.red));
            setPreferredSize(new Dimension(300, 50));

            JButton button1 = new JButton("<<");
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /*call whatever method does all the calculations
                     * example: Celestial.doCalculations(>desired time step<)*/
                    universe.slowDown();

                    solarSimFrame.repaint();
                    solarSimPanel.setZoomer(true);


                }
            });
            button1.setPreferredSize(new Dimension(70, 30));
            button1.setAlignmentX(Component.CENTER_ALIGNMENT);
            button1.setFocusable(false);

            JButton button2 = new JButton(">>");
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /*call whatever method does all the calculations
                     * example: Celestial.doCalculations(>desired time step<)*/
                    universe.speedUp();
                    solarSimFrame.repaint();
                    solarSimPanel.setZoomer(true);
                }
            });
            button2.setPreferredSize(new Dimension(70, 30));
            button2.setAlignmentX(Component.CENTER_ALIGNMENT);
            button2.setFocusable(false);


            JButton startSim = new JButton("Start");
            startSim.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                    t = new Thread(new Runnable() {
                        public void run() {

                            initialized = true;
                            while (initialized) {
                                /*call whatever method does all the calculations
                                 * example: Celestial.doCalculations(>desired time step<)*/

                                universe.doStep();
                                solarSimPanel.repaint();
                                solarSimPanel.setZoomer(true);
                            }
                        }
                    });

                    t.start();

                }
            });
            startSim.setPreferredSize(new Dimension(70, 30));
            startSim.setAlignmentX(Component.CENTER_ALIGNMENT);
            startSim.setFocusable(false);


            JButton stopSim = new JButton("Stop");
            stopSim.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    initialized = false;
                }
            });
            stopSim.setPreferredSize(new Dimension(70, 30));
            stopSim.setAlignmentX(Component.CENTER_ALIGNMENT);
            stopSim.setFocusable(false);

            add(startSim);
            add(stopSim);
            add(button1);
            add(button2);

        }

    }

}
