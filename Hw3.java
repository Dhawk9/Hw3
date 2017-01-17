// Draw a triangle whith red, green, and blue verticies.
// Written in by Derek Hawkins on January 12, 2017.

import java.awt.*;
import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLJPanel;
import java.util.Scanner;

public class Hw3 extends JPanel implements GLEventListener {

	public static void main(String[] args){
		JFrame window = new JFrame("Hw3 using JOGL");
		Hw3 panel = new Hw3();
		window.setContentPane(panel);
		window.pack();
		window.setLocation(50,50);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		
			try{
				panel.input_int = Integer.parseInt(args[0]);

			}
			catch(java.lang.NumberFormatException exception){
				System.out.print("Error: Please only enter Integers: ");
				Scanner scanner = new Scanner(System.in);
				String input_string = scanner.nextLine();
				panel.input_int = Integer.parseInt(input_string);

				System.out.print(panel.input_int);

			}
		

	}

	public  Hw3(){
		GLJPanel gljPanel = new GLJPanel(new GLCapabilities(null));
		gljPanel.setPreferredSize(new Dimension(400, 400));
		gljPanel.addGLEventListener(this);
		add(gljPanel);
	}

	private int input_int = 3;

	//---------------- Methods of the GLEventListener interface--------------------

	public void display(GLAutoDrawable drawable) {	
		GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glColor3d(0.0, 1.0, 1.0);
		
		int t = getHeight();
		System.out.println("The window width is " + t);
		
		gl.glLoadIdentity();
		
		if(input_int == 3){ // this is to move my triangle
			gl.glTranslated(0.0,-0.2,0.0);
		}
		
		if(input_int % 2 == 0){ // this will rotate the polygons with and even number of sides
		  double rot = 360.0 / input_int;
			gl.glRotated(rot / 2.0, 0.0, 0.0, 1.0);
		}
		
		double r = 0.0;
		double g = 0.0;
		double b = 0.0;
		
		gl.glBegin(GL2.GL_POLYGON);			
			double angle = (2 * Math.PI) / input_int;
			double x, y;
			for(int i = 0; i < input_int; i++){
				x = Math.sin(i * angle);
				y = Math.cos(i * angle);
				
				if(r == 0 && g == 0 && b == 0){ // these if statements are for the coloring that I cant figure out
					r = 3.0 / input_int;
				}
				
				else if(r != 0){
					r = r - (3.0/ (2 * input_int)); g = 3.0 / input_int;
				}

				else {
					b = 3.0 / input_int;
				}
					
				gl.glColor3d(r,g,b); // the if statements would change r,g,b each time
				gl.glVertex2d(x, y);
				//System.out.println("x = " + x + ", y = " + y);
				System.out.println("r = " + r + ",g = " + g + ", b = " + b);
			}
			
		gl.glEnd();
	}

	public void init(GLAutoDrawable drawable) {
	}
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height){
		
	}
	public void dispose(GLAutoDrawable drawable) {
		
	}
}