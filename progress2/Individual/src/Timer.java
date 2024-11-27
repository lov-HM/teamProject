import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Player {
    private String name;
    private int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}

class ScoreManager {
    private String fileName;
    private List<Player> players;

    public ScoreManager(String fileName) {
        this.fileName = fileName;
        this.players = new ArrayList<>();
        loadScores();
    }

    private void loadScores() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    players.add(new Player(name, score));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("파일이 존재하지 않습니다. 새로 생성합니다.");
        } catch (IOException e) {
            System.out.println("파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    public void addPlayer(String name, int score) {
        players.add(new Player(name, score));
        sortScores();
    }

    public void addPlayer(int score) {
        players.add(new Player("Time Player", score));
        sortScores();
    }

    private void sortScores() {
        players.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
    }

    public void printRanking() {
        System.out.println("\n순위표:");
        for (int i = 0; i < players.size(); i++) {
            System.out.printf("%d위: %s - %d점\n", i + 1, players.get(i).getName(), players.get(i).getScore());
        }
    }

    public void saveScores() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Player player : players) {
                bw.write(player.getName() + "," + player.getScore());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("파일을 쓰는 중 오류가 발생했습니다: " + e.getMessage());
        }
        System.out.println("\n결과가 파일에 저장되었습니다.");
    }
}

class NonogramMonitor {
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

    private long startTime;
    private boolean isGameFinished = false;

    public JPanel createGamePanel() {
        JPanel gamePanel = new JPanel(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridLayout(6, 6));
        JButton[][] buttons = new JButton[6][6];

        // 타이머 시작
        startTime = System.currentTimeMillis();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setPreferredSize(new Dimension(60, 60));
                buttons[i][j].setFocusPainted(false);

                buttons[i][j].addActionListener(e -> {
                    if (!isGameFinished) {
                        JButton sourceButton = (JButton) e.getSource();
                        sourceButton.setBackground(sourceButton.getBackground() == Color.WHITE ? Color.BLUE : Color.WHITE);
                    }
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
                long endTime = System.currentTimeMillis();
                int secDiffTime = (int) ((endTime - startTime) / 1000);

                // 게임이 끝났음을 알림
                isGameFinished = true;

                // 정답을 맞혔을 때 메시지 표시
                JOptionPane.showMessageDialog(null, "정답입니다!", "결과", JOptionPane.INFORMATION_MESSAGE);

                // 점수 관리 클래스 사용
                String fileName = "C:\\Users\\ckyst\\Desktop\\Ranking.txt";
                ScoreManager scoreManager = new ScoreManager(fileName);

                // 타이머 결과를 점수로 저장
                scoreManager.addPlayer(secDiffTime);

                // 순위 출력
                scoreManager.printRanking();

                // 결과를 파일에 저장
                scoreManager.saveScores();
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

public class Timer {
    public static void main(String[] args) {
        NonogramMonitor nonograms = new NonogramMonitor();
        nonograms.show();
    }
}
