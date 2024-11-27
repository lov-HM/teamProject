package pw;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Password extends JFrame {
    private JPanel contentPane = new JPanel();
    private JPasswordField passwordField = new JPasswordField();
    private JButton cancelButton = new JButton("Cancel");
    private JButton pwInputButton = new JButton("OK");
    private EscapeRoomMain mainFrame; 

    public Password(EscapeRoomMain mainFrame) {
        this.mainFrame = mainFrame; 
        setTitle("Password");
        setSize(400, 150);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        contentPane.setBackground(Color.LIGHT_GRAY);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        
        passwordField.setHorizontalAlignment(SwingConstants.LEFT);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordField.setForeground(Color.BLACK);
        passwordField.setBounds(12, 10, 360, 50);
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordCheck();
            }
        });
        contentPane.add(passwordField);

   
        cancelButton.setBackground(Color.WHITE);
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 25));
        cancelButton.setForeground(Color.BLACK);
        cancelButton.setBounds(12, 70, 147, 33);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.setText("");
            }
        });
        
        contentPane.add(cancelButton);

        pwInputButton.setForeground(Color.BLACK);
        pwInputButton.setFont(new Font("Arial", Font.BOLD, 25));
        pwInputButton.setBackground(Color.WHITE);
        pwInputButton.setBounds(183, 70, 191, 33);
        pwInputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordCheck();
            }
        });
        contentPane.add(pwInputButton);
    }

    // Password Check Method
    public void passwordCheck() {
        String inputPassword = new String(passwordField.getPassword());
        if ("323".equals(inputPassword)) {
            JOptionPane.showMessageDialog(null, "Correct! You got the clue.");
            mainFrame.showClue();
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Try Again");
        }
    }
}

