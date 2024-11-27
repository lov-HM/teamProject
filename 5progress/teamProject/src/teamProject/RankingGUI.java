package teamProject;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class RankingGUI {

 public JPanel displayRanking(String filePath) {
     JTextArea rankingText = new JTextArea();
     rankingText.setEditable(false);
     rankingText.setFont(new Font("Noto Sans", Font.PLAIN, 20));
     rankingText.setOpaque(false);
     rankingText.setForeground(Color.white);

     List<Player> players = new ArrayList<>();
     
     try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
         String line;
         while ((line = reader.readLine()) != null) {
             String[] parts = line.split(",");
             if (parts.length == 2) {
                 String name = parts[0].trim();
                 int score;
                 try {
                     score = Integer.parseInt(parts[1].trim());
                     players.add(new Player(name, score));
                 } catch (NumberFormatException e) {
                     System.err.println("잘못된 점수 형식: " + parts[1]);
                 }
             }
         }
     } catch (IOException e) {
         System.err.println("파일을 읽을 수 없습니다: " + e.getMessage());
     }

     players.sort(Comparator.comparingInt(Player::getScore));

     StringBuilder rankingBuilder = new StringBuilder();
     for (int i = 0; i < players.size(); i++) {
         Player player = players.get(i);
         rankingBuilder.append(String.format("%d위: %s - %d점\n", i + 1, player.getName(), player.getScore()));
     }
     rankingText.setText(rankingBuilder.toString());

     JScrollPane scrollPane = new JScrollPane(rankingText) {
         @Override
         public void paintComponent(Graphics g) {
             super.paintComponent(g);
             ImageIcon backgroundImage = new ImageIcon("src/images/랭킹배경.png");
             g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
         }
     };
     scrollPane.setBounds(425, 285, 330, 415);
     scrollPane.setOpaque(true);
     scrollPane.getViewport().setOpaque(false);

     JPanel rankingPanel = new JPanel() {
         @Override
         protected void paintComponent(Graphics g) {
             super.paintComponent(g);
             ImageIcon backgroundImage = new ImageIcon("src/images/랭킹.png");
             g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
         }
     };
     rankingPanel.setLayout(null);
     rankingPanel.add(scrollPane);

     return rankingPanel;
 }
}