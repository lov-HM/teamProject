package teamProject;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class RankingGUI {

    private JTextArea rankingText;
    private List<Player> players;
    private static final ImageIcon BACKGROUND_IMAGE = new ImageIcon("src/images/랭킹배경.png");
    private static final ImageIcon PANEL_BACKGROUND_IMAGE = new ImageIcon("src/images/랭킹.png");
    private ScoreManager scoreManager;

    public JPanel displayRanking(String filePath) {
        scoreManager = new ScoreManager(filePath);
        players = scoreManager.getPlayers();
        rankingText = new JTextArea();
        rankingText.setEditable(false);
        rankingText.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        rankingText.setOpaque(false);
        rankingText.setForeground(Color.white);

        JScrollPane scrollPane = new JScrollPane(rankingText);
        scrollPane.setBounds(425, 285, 330, 415);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        JPanel rankingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(PANEL_BACKGROUND_IMAGE.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        rankingPanel.setLayout(null);
        rankingPanel.add(scrollPane);

        updateRankingText();
        return rankingPanel;
    }

    private void updateRankingText() {
        players.sort(Comparator.comparingInt(Player::getScore));

        String ranking = players.stream()
            .map(p -> String.format("%d위: %s - %d초", players.indexOf(p) + 1, p.getName(), p.getScore()))
            .collect(Collectors.joining("\n"));
        rankingText.setText(ranking);
    }
}
