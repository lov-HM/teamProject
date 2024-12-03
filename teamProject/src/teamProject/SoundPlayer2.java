package teamProject;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer2 {
	private Clip effectClip;

    public void playEffectSound(String effectFilePath) {
        try {
            File effectFile = new File(effectFilePath);
            AudioInputStream effectStream = AudioSystem.getAudioInputStream(effectFile);
            effectClip = AudioSystem.getClip();
            effectClip.open(effectStream);
            effectClip.start();
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    
    public void playKeyAndDoor() {
        playEffectSound("src/sound/자물쇠열기2.wav");
    }
    
    public void playLockedDoor() {
        playEffectSound("src/sound/잠긴 문.wav");
    }
    
    public void playClickItem() {
        playEffectSound("src/sound/아이템선택1.wav");
    }
    
    public void playCorrect() {
        playEffectSound("src/sound/정답.wav");
    }
    
    public void playWrong() {
        playEffectSound("src/sound/오답1.wav");
    }
    
    public void playPaper() {
        playEffectSound("src/sound/노노그램1.wav");
    }
    
    public void playHmm() {
        playEffectSound("src/sound/흠1.wav");
    }
  
    
}
