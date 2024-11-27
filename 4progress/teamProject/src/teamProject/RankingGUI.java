package teamProject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RankingGUI {
    public void showRanking(ScoreManager scoreManager) {
        List<Player> players = scoreManager.getPlayers();

        JFrame frame = new JFrame("순위표");
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());

        // 순위를 보여줄 JTextArea
        JTextArea rankingArea = new JTextArea();
        rankingArea.setEditable(false);

        // 순위 텍스트 생성
        StringBuilder rankingText = new StringBuilder("순위표:\n");
        for (int i = 0; i < players.size(); i++) {
            rankingText.append(i + 1).append("위: ").append(players.get(i).getName()).append(" - ").append(players.get(i).getScore()).append("점\n");
        }

        rankingArea.setText(rankingText.toString());

        // JScrollPane에 JTextArea 추가
        JScrollPane scrollPane = new JScrollPane(rankingArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // 닫기 버튼
        JButton closeButton = new JButton("닫기");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.add(closeButton, BorderLayout.SOUTH);

        // 창 보이기
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
