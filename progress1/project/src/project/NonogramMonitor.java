package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NonogramMonitor {

    private JFrame frame;

    public NonogramMonitor() {
        frame = new JFrame("6x6 네모로직");
        frame.setSize(1200, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon monitorBackground = new ImageIcon("C:\\Users\\ckyst\\Desktop\\img.png");
                g.drawImage(monitorBackground.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(null);

        NonogramsGame game = new NonogramsGame();
        JPanel gamePanel = game.createGamePanel();
        gamePanel.setBounds(300, 200, 600, 600);
        mainPanel.add(gamePanel);

        frame.add(mainPanel);
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        NonogramMonitor nonograms = new NonogramMonitor();
        nonograms.show();
    }
}

class NonogramsGame {

    private final boolean[][] solution = {
    		{true, true, true, true, false, true},
            {true, true, true, false, false, false},
            {true, false, true, true, false, true},
            {false, true, false, true, true, true},
            {false, false, false, true, true, true},
            {false, true, false, true, true, true}
    };

    public JPanel createGamePanel() {
        JPanel gamePanel = new JPanel(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridLayout(6, 6));
        JButton[][] buttons = new JButton[6][6];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setPreferredSize(new Dimension(60, 60));
                buttons[i][j].setFocusPainted(false);

                buttons[i][j].addActionListener(e -> {
                    JButton sourceButton = (JButton) e.getSource();
                    sourceButton.setBackground(sourceButton.getBackground() == Color.WHITE ? Color.BLUE : Color.WHITE);
                });

                gridPanel.add(buttons[i][j]);
            }
        }

        JPanel rowHintsPanel = new JPanel(new GridLayout(6, 1));
        String[] rowHints = {"{4, 1}", "{3}", "{1, 2, 1}", "{1, 3}", "{3}", "{1, 3}"};
        for (String hint : rowHints) {
            rowHintsPanel.add(createHintLabel(hint));
        }

        JPanel colHintsPanel = new JPanel(new GridLayout(1, 6));
        String[] colHints = {"{3}", "{2, 1, 1}", "{3}", "{1, 4}", "{3}", "{1, 4}"};
        for (String hint : colHints) {
            colHintsPanel.add(createHintLabel(hint));
        }

        JButton checkButton = new JButton("Check Answer");
        checkButton.addActionListener(e -> {
            if (isSolutionCorrect(buttons)) {
                JOptionPane.showMessageDialog(null, "정답입니다!", "결과", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "틀렸습니다. 다시 시도하세요.", "결과", JOptionPane.ERROR_MESSAGE);
            }
        });

        gamePanel.add(gridPanel, BorderLayout.CENTER);
        gamePanel.add(rowHintsPanel, BorderLayout.WEST);
        gamePanel.add(colHintsPanel, BorderLayout.NORTH);
        gamePanel.add(checkButton, BorderLayout.SOUTH);

        return gamePanel;
    }

    private JLabel createHintLabel(String hint) {
        JLabel label = new JLabel(hint, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private boolean isSolutionCorrect(JButton[][] buttons) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                boolean isBlue = buttons[i][j].getBackground() == Color.BLUE;
                if (isBlue != solution[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public class main {
        public static void main(String[] args) {
            NonogramMonitor nonograms = new NonogramMonitor();
            nonograms.show();
        }
    }
}