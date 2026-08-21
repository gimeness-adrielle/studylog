package me.gimenez.studylog.ai.audio;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class WavConverter {
    public static byte[] convert(byte[] pcm) throws IOException {
        AudioFormat audioFormat = new AudioFormat(24000, 16, 1, true, false);


        AudioInputStream stream = new AudioInputStream(
                new ByteArrayInputStream(pcm),
                audioFormat,
                pcm.length / audioFormat.getFrameSize()
        );

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        AudioSystem.write(stream, AudioFileFormat.Type.WAVE, out);
        return out.toByteArray();
    }
}
