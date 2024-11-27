package Item;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class KeyAndDoorGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("키와 문 게임");

        // 게임 화면 패널
        KeyAndDoorGamePanel gamePanel = new KeyAndDoorGamePanel();
        frame.add(gamePanel);

        // 프레임 설정
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setVisible(true);
    }
}


