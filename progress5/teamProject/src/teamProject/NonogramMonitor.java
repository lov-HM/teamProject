package teamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NonogramMonitor {

    private JFrame frame;
    private EscapeRoomMain mainFrame; // EscapeRoomMain 객체 추가

    // 기본 생성자: EscapeRoomMain을 전달받음
    public NonogramMonitor(EscapeRoomMain escapeRoomMain) {
        this(escapeRoomMain, "C:\\Users\\ckyst\\Desktop\\img.png"); // 기본 배경 이미지 경로 전달
    }

    // 생성자 오버로딩: 배경 이미지를 사용자 정의 가능
    public NonogramMonitor(EscapeRoomMain escapeRoomMain, String backgroundImagePath) {
        this.mainFrame = escapeRoomMain; // 전달받은 EscapeRoomMain 객체 초기화
        
        frame = new JFrame("6x6 네모로직");
        frame.setSize(647, 670);

        JPanel mainPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon monitorBackground = new ImageIcon(backgroundImagePath);
                g.drawImage(monitorBackground.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(null);

        NonogramsGame game = new NonogramsGame(mainFrame); // EscapeRoomMain 객체 전달
        JPanel gamePanel = game.createGamePanel();
        gamePanel.setBounds(10, 10, 600, 600);
        mainPanel.add(gamePanel);

        frame.add(mainPanel);
    }

    public void show() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // 여기서는 EscapeRoomMain 객체를 생성해야 합니다.
        EscapeRoomMain mainFrame = new EscapeRoomMain(); // 예시로 생성
        NonogramMonitor nonograms = new NonogramMonitor(mainFrame, "C:\\Custom\\Path\\to\\Background.png");
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

    private EscapeRoomMain mainFrame;

    public NonogramsGame(EscapeRoomMain mainFrame) {
        this.mainFrame = mainFrame;
    }

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
                mainFrame.showClue1(); // EscapeRoomMain의 showClue 메소드 호출
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(checkButton); // 버튼의 조상 JFrame을 찾음
                topFrame.dispose(); // 창 닫기
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
}
