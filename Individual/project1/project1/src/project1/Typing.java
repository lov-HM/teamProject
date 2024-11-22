package project1;

import java.util.concurrent.TimeUnit;

public class Typing {

    public static void typeText(String text, int delay, TextDisplay display) {
        for (char c : text.toCharArray()) {
            display.appendText(String.valueOf(c));
            try {
                TimeUnit.MILLISECONDS.sleep(delay); // 지정된 시간만큼 지연
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("타이핑 효과 중 오류가 발생했습니다.");
            }
        }
        display.appendText("\n"); // 줄 바꿈
    }
}
