/*import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;



class DrawingPanel extends JPanel {
    DrawingPanel() {
        int step = 15; // milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                repaint();
            }
        };
        new Timer(step, taskPerformer).start();
        Celestial b0 = new Star();
        addCelestial(b0);
        Celestial b1 = new Planet();
        addCelestial(b1);
        Celestial b2 = new Planet();
        addCelestial(b2);
    }

    List<Body> bodies = new ArrayList<>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        bodies.get(2).interact(bodies.get(1));
        bodies.get(0).interact(bodies.get(1));
        bodies.get(0).interact(bodies.get(2));

        for (Celestial b : bodies) {
            f.draw(b);
        }
    }
    public void addCelestial(Celestial b) {
        bodies.add(b);
    }

public void drawMultipleBalls(JFrame frame, Ball[] balls)
    {
        ballArray = balls;
        frame.getContentPane().add(new MyComponent2());
    }
    private class MyComponent extends JComponent{
        public void paintComponent(Graphics g){

            if (fillBall) //Fill first, and then draw outline.
            {
                g.setColor(ballFillColor);
                g.fillOval(getBallX(),getBallY(), getBallHeight(),getBallWidth());
            }

            g.setColor(getBallBorderColor());
            g.drawOval(getBallX(),getBallY(), getBallHeight(),getBallWidth());

        }
    }

    private class MyComponent2 extends JComponent{
        public void paintComponent(Graphics g){

            for (int i = 0; i < ballArray.length; i++)
            {
                if (ballArray[i].fillBall) //Fill first, and then draw outline.
                {
                    g.setColor(ballArray[i].ballFillColor);
                    g.fillOval(ballArray[i].getBallX(),ballArray[i].getBallY(), ballArray[i].getBallHeight(),ballArray[i].getBallWidth());
                }

                g.setColor(ballArray[i].getBallBorderColor());
                g.drawOval(ballArray[i].getBallX(),ballArray[i].getBallY(), ballArray[i].getBallHeight(),ballArray[i].getBallWidth());
            }
        }
    }



public void drawMultipleBalls(JFrame frame, Ball[] balls)
        {
        ballArray = balls;
        frame.getContentPane().add(new MyComponent2());
        }
private class MyComponent extends JComponent{
    public void paintComponent(Graphics g){

        if (fillBall) //Fill first, and then draw outline.
        {
            g.setColor(ballFillColor);
            g.fillOval(getBallX(),getBallY(), getBallHeight(),getBallWidth());
        }

        g.setColor(getBallBorderColor());
        g.drawOval(getBallX(),getBallY(), getBallHeight(),getBallWidth());

    }
}

private class MyComponent2 extends JComponent{
    public void paintComponent(Graphics g){

        for (int i = 0; i < ballArray.length; i++)
        {
            if (ballArray[i].fillBall) //Fill first, and then draw outline.
            {
                g.setColor(ballArray[i].ballFillColor);
                g.fillOval(ballArray[i].getBallX(),ballArray[i].getBallY(), ballArray[i].getBallHeight(),ballArray[i].getBallWidth());
            }

            g.setColor(ballArray[i].getBallBorderColor());
            g.drawOval(ballArray[i].getBallX(),ballArray[i].getBallY(), ballArray[i].getBallHeight(),ballArray[i].getBallWidth());
        }
    }
}




}*/