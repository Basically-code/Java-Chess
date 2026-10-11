package sound;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;

public class chessSound {
    public void moveSound(){
        new Thread(() -> {
            try (InputStream audioSrc = getClass().getResourceAsStream("/sound/piece-move.wav")) {
                if (audioSrc == null) {
                    throw new IOException("Missing audio resource: /sound/piece-move.wav");
                }

                try (AudioInputStream aInputStream =
                             AudioSystem.getAudioInputStream(new BufferedInputStream(audioSrc))) {
                    Clip clip = AudioSystem.getClip();
                    clip.open(aInputStream);

                    clip.addLineListener(event -> {
                        if (event.getType() == LineEvent.Type.STOP) {
                            clip.close();
                        }
                    });
                    clip.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
