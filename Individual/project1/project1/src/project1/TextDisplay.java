package project1;

import javax.swing.*;
import java.awt.*;

public class TextDisplay {
    private JTextArea textArea;

    // Homepage 객체를 파라미터로 받는 생성자
    public TextDisplay(Homepage homepage) {
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 24)); // 원하는 글씨체와 크기 설정
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 50, 600, 300); // 원하는 위치와 크기 설정

        // 기존의 Homepage에 JScrollPane을 추가
        homepage.add(scrollPane);
        homepage.setVisible(true); // Homepage가 보이도록 설정
    }

    public void appendText(String text) {
        textArea.append(text);
        textArea.setCaretPosition(textArea.getDocument().getLength()); // 항상 텍스트 아래로 스크롤
    }
}
