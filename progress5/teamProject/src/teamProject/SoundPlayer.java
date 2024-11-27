package teamProject;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    private Clip bgmClip;

    public void playBGM(String filePath) {
        stopBGM();
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            bgmClip = AudioSystem.getClip();
            bgmClip.open(audioStream);
            bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopBGM() {
        if (bgmClip != null && bgmClip.isRunning()) {
            bgmClip.stop();
            bgmClip.close();
        }
    }

    public void playMenuBGM() {
        playBGM("src/sound/전설의족보.wav");
    }
    
    public void playGameBGM() {
        playBGM("src/sound/게임BGM.wav");
    }
    
    public void playEndding() {
        playBGM("src/sound/전설의엔딩.wav");
    }
  
    
}
