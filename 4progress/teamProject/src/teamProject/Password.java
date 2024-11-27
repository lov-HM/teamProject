package teamProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Password extends JFrame {
    private JPanel contentPane = new JPanel();
    private JPasswordField passwordField = new JPasswordField();
    private JButton cancelButton = new JButton("Cancel");
    private JButton pwInputButton = new JButton("OK");
    private EscapeRoomMain mainFrame; 
    private JLabel passwordLabel = new JLabel("Enter Password:"); 

    public Password(EscapeRoomMain EscapeRoomMain) {
        this.mainFrame = EscapeRoomMain; 
        setTitle("Password");
        setSize(400, 150);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        contentPane.setBackground(Color.LIGHT_GRAY);
        setContentPane(contentPane);
        contentPane.setLayout(null);

     // JLabel 추가
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setBounds(12, 10, 360, 30);  // 위치 및 크기 설정
        contentPane.add(passwordLabel);
       
        
        passwordField.setHorizontalAlignment(SwingConstants.LEFT);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordField.setForeground(Color.BLACK);
        passwordField.setBounds(12, 50, 360, 50);
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
        cancelButton.setBounds(12, 110, 147, 33);
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
        pwInputButton.setBounds(183, 110, 191, 33);
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
            mainFrame.showClue2();
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Try Again");
        }
    }
}

