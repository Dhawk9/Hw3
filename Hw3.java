// Draw a triangle whith red, green, and blue verticies.
// Written in by Derek Hawkins on January 12, 2017.

import java.awt.*;
import javax.swing.*;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLJPanel;

import java.util.Scanner;

public class Hw3 extends JPanel implements GLEventListener {

    public static void main(String[] args) {
        JFrame window = new JFrame("Hw3 using JOGL");
        Hw3 panel = new Hw3();
        window.setContentPane(panel);
        window.pack();
        window.setLocation(50, 50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);


        try {
            panel.input_int = Integer.parseInt(args[0]);

        } catch (java.lang.NumberFormatException exception) {
            System.out.print("Error: Please only enter Integers: ");
            Scanner scanner = new Scanner(System.in);
            String input_string = scanner.nextLine();
            panel.input_int = Integer.parseInt(input_string);

            System.out.print(panel.input_int);

        }


    }

    public Hw3() {
        GLJPanel gljPanel = new GLJPanel(new GLCapabilities(null));
        gljPanel.setPreferredSize(new Dimension(400, 400));
        gljPanel.addGLEventListener(this);
        add(gljPanel);
    }

    private int input_int = 3;

    //---------------- Methods of the GLEventListener interface--------------------

    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glLoadIdentity();

        int t = getHeight();
        System.out.println("The window width is " + t);

//        if (input_int == 3) { // this is to move my triangle
//            gl.glTranslated(0.0, -0.2, 0.0);
//        }

        if (input_int % 2 == 0) { // this will rotate the polygons with and even number of sides
            double rot = 360.0 / input_int;
            gl.glRotated(rot / 2.0, 0.0, 0.0, 1.0);
        }

        // Variable Declarations
        double angle = (2 * Math.PI) / input_int;
        System.out.println("Angle " + angle);
        double x = 0, y = 0;
        final double distance = 1.0472;

        gl.glBegin(GL2.GL_POLYGON);

        for (int i = 0; i < input_int; i++) {

            x = Math.sin(i * angle);
            y = Math.cos(i * angle);

            System.out.println(" Coordinates : (" + x + "," + y + ")");

            float degrees = getAngleDegrees(x, y);
            System.out.println("Degrees : " + degrees);

            // 0 - 60
            if (degrees <= 60) {
                double d = distanceBetweenPoints(x, y, 0.0, 1.0);
                float value = (float) (d / distance);
                gl.glColor3f(1, value, 0);
            } else if (60 < degrees && degrees <= 120) {

                double d = distanceBetweenPoints(x, y, 0.867, 0.5);
                float value = (float) (d / distance);

                gl.glColor3f(1 - value, 1, 0);
            } else if (120 < degrees && degrees <= 180) {
                double d = distanceBetweenPoints(x, y, 0.867, -0.5);

                float value = (float) (d / distance);
                gl.glColor3f(0, 1, value);
            } else if (180 < degrees && degrees <= 240) {
                double d = distanceBetweenPoints(x, y, 0.0, -1.0);

                float value = (float) (d / distance);
                gl.glColor3f(0, value - 1, 1);
            } else if (240 < degrees && degrees <= 300) {
                double d = distanceBetweenPoints(x, y, -0.867, -0.5);

                float value = (float) (d / distance);
                gl.glColor3f(1 - value, 0, 1);
            } else {
                double d = distanceBetweenPoints(x, y, -0.867, 0.5);

                float value = (float) (d / distance);
                gl.glColor3f(1, 0, value - 1);
            }


            // 60 - 120


            // 120 - 180


            // 180 - 240


            // 240 - 300


            //300 - 0


//            // RED
//            if (colorAngle == 90) {
//                gl.glColor3f(1, 0, 0);
//            }
//
//            // GREEN
//            if (colorAngle == 330) {
//                gl.glColor3f(0, 1, 0);
//            }
//
//            // BLUE
//            if (colorAngle == 210) {
//                gl.glColor3f(0, 0, 1);
//            }

            gl.glVertex2d(x, y);

            System.out.println("---------------------------------------------");
        }

        gl.glEnd();
    }

    private float getAngleDegrees(double x, double y) {
        float angle = (float) Math.toDegrees(Math.atan2(x - 0, y - 0));

        if (angle < 0) {
            angle += 360;
        }

        return angle;
    }

    private float distanceBetweenPoints(double aX, double aY, double bX, double bY) {
        float d = (float) Math.atan2(bY - aY, bX - aX);

        System.out.println("Distance Degrees: " + Math.toDegrees(d));
        System.out.println("Distance between points: " + d);
        return d;
    }

    public void init(GLAutoDrawable drawable) {
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }

    public void dispose(GLAutoDrawable drawable) {

    }
}
