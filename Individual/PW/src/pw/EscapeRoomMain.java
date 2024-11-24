package pw;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EscapeRoomMain extends JFrame {
    private ImageIcon room = new ImageIcon("src/images/room.jpeg");
    private ImageIcon powerOff = new ImageIcon("src/images/black.btn.png");
    private ImageIcon powerOn = new ImageIcon("src/images/green.btn.png");
    private ImageIcon exitEx = new ImageIcon("src/images/7.png");
    private ImageIcon exitPr = new ImageIcon("src/images/8.png");

    private JButton powerOffbtn = new JButton(powerOff);
    private JButton exitbtn = new JButton(exitEx);
    
    private JLabel clueLabel;

    public EscapeRoomMain() {
        setUndecorated(true);
        setTitle("Escape Room");
        setSize(1200, 1000);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(null);
        
        exitbtn.setBounds(1155, 0, 30, 30);							
        exitbtn.setBorderPainted(false);
        exitbtn.setContentAreaFilled(false);
        exitbtn.setFocusPainted(false);
        exitbtn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {	
				exitbtn.setIcon(exitPr);
				exitbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {		
				exitbtn.setIcon(exitEx);
				exitbtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) { 
				
				System.exit(0);
			}
			
		});
		add(exitbtn);
		     
        powerOffbtn.setBounds(845, 140, 73, 73);
        powerOffbtn.setBorderPainted(false);
        powerOffbtn.setContentAreaFilled(false);
        powerOffbtn.setFocusable(false);

        powerOffbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                powerOffbtn.setIcon(powerOn);
		powerOffbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                powerOffbtn.setIcon(powerOff);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                powerOffbtn.setIcon(powerOn);
                new Password(EscapeRoomMain.this); 
		powerOffbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        add(powerOffbtn);

        
        clueLabel = new JLabel();
        clueLabel.setOpaque(true);
        clueLabel.setBackground(Color.BLUE);
        clueLabel.setBounds(1000, 750, 100, 50); 
        clueLabel.setVisible(false);
        add(clueLabel);

        
        JLabel roomLabel = new JLabel(room);
        roomLabel.setBounds(0, 0, 1200, 1000);
        add(roomLabel);
    }

   
    public void showClue() {
        clueLabel.setVisible(true);
      /*
        Timer timer = new Timer(500, new ActionListener() {
            private int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                clueLabel.setVisible(!clueLabel.isVisible());
                count++;
                if (count >= 6) { // Stop after 3 seconds
                    ((Timer) e.getSource()).stop();
                    clueLabel.setVisible(true); // Ensure it's visible at the end
                }
            }
        });
        timer.start();*/
    }

    public static void main(String[] args) {
        new EscapeRoomMain();
    }
}
