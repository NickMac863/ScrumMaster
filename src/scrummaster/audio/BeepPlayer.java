/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scrummaster.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import scrummaster.Constants;

/**
 *
 * @author Nick
 */
public class BeepPlayer implements Runnable
{

    @Override
    public void run()
    {
        try
        {
            tone(Constants.TONE_HZ, Constants.TONE_MS, Constants.TONE_VOL);
        }
        catch (LineUnavailableException ex)
        {
            System.out.println("Exception playing beep: " + ex.getMessage());
        }
    }

    private static void tone(int hz, int msecs, double vol)
            throws LineUnavailableException
    {
        byte[] buf = new byte[1];
        AudioFormat af =
                new AudioFormat(
                Constants.TONE_SAMPLE_RATE, // sampleRate
                8, // sampleSizeInBits
                1, // channels
                true, // signed
                false);      // bigEndian
        try (SourceDataLine sdl = AudioSystem.getSourceDataLine(af))
        {
            sdl.open(af);
            sdl.start();
            for (int i = 0; i < msecs * 8; i++)
            {
                double angle = i / (Constants.TONE_SAMPLE_RATE / hz) * 2.0 * Math.PI;
                buf[0] = (byte) (Math.sin(angle) * 127.0 * vol);
                sdl.write(buf, 0, 1);
            }
            sdl.drain();
            sdl.stop();
            sdl.close();
        }
    }
}
