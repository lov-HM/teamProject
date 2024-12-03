package teamProject;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Player {
    private String name;
    private int score;

    public Player() {
        this.name = "손님";
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
    private final String fileName;
    private List<Player> players;

    public ScoreManager(String fileName) {
        this.fileName = fileName;
        this.players = new ArrayList<>();
        loadScores();
    }

    private void loadScores() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            List<String> lines = br.lines().collect(Collectors.toList());
            players = lines.stream()
                .map(line -> line.split(","))
                .filter(parts -> parts.length == 2)
                .map(parts -> {
                    try {
                        return new Player(parts[0].trim(), Integer.parseInt(parts[1].trim()));
                    } catch (NumberFormatException e) {
                        System.out.println("잘못된 형식의 데이터: " + Arrays.toString(parts));
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("파일을 처리하는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    public void addPlayer(String name, int score) {
        players.add(new Player(name, score));
        sortScores();
    }

    private void sortScores() {
        players.sort(Comparator.comparingInt(Player::getScore).reversed());
    }

    public void saveScores() {
        List<String> lines = players.stream()
            .map(player -> player.getName() + "," + player.getScore())
            .collect(Collectors.toList());
        writeToFile(fileName, lines);
    }

    private Optional<List<String>> readFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return Optional.of(br.lines().collect(Collectors.toList()));
        } catch (IOException e) {
            System.out.println("파일을 처리하는 중 오류가 발생했습니다: " + e.getMessage());
            return Optional.empty();
        }
    }

    private void writeToFile(String fileName, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
            System.out.println("\n결과가 파일에 저장되었습니다.");
        } catch (IOException e) {
            System.out.println("파일을 쓰는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
