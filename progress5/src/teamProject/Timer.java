package teamProject;

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

    public Player() {
        this.name = "Unknown";
        this.score = 0;
    }
    
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

    private void sortScores() {
        players.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
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

    public List<Player> getPlayers() {
        return players;
    }
}
