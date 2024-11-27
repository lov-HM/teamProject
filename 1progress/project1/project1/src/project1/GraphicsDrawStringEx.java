package project1;

import javax.swing.*;
import java.awt.*;

public class GraphicsDrawStringEx extends JFrame {
	private MyPanel panel = new MyPanel();
	
	public GraphicsDrawStringEx() {
		setTitle(" draw string Example");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(panel);
		
		setSize(700,700);
		setVisible(true);
	}

	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.setColor(Color.BLUE);
			g.drawString(" i want vacation", 30, 30);
			
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("how much", 30, 50);
			
			g.setColor(new Color(0x00ff00ff));
			for (int i = 1; i <=5; i++) {
				g.setFont(new Font("Jokerman", Font.ITALIC, i*10));
				g.drawString("this much", 30, 60 +i*60);
			}
		}
	}
	public static void main(String [] args) {
		new GraphicsDrawStringEx();
	}

}
