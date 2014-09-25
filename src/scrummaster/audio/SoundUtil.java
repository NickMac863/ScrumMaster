/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scrummaster.audio;

import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import scrummaster.Constants;

/**
 *
 * @author Nick
 */
public class SoundUtil
{
    private AudioInputStream audioInputStream;
    private SourceDataLine line;
    
    public SoundUtil(String fileName)
    {
        try
        {
            this.audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(fileName));
            AudioFormat audioFormat = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(audioFormat);
            this.audioInputStream.mark(Constants.WAV_SIZE_BYTES);
        
        }
        catch (UnsupportedAudioFileException | IOException e)
        {
            System.out.println("Exception creating Audio Stream: " + e.getMessage());
        }
        catch (LineUnavailableException ex)
        {
            System.out.println("Exception accessing audio line: " + ex.getMessage());
        }
        
        
    }
    
    public void playSound()
    {
        this.line.start();
        int nBytesRead = 0;
        byte[] abData = new byte[Constants.WAV_BUF_SIZE_BYTES];
        while (nBytesRead != -1)
        {
            try
            {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
            }
            catch (IOException e)
            {
                System.out.println("Exception reading audio stream: " + e.getMessage());
            }
            if (nBytesRead >= 0)
            {
                int nBytesWritten = line.write(abData, 0, nBytesRead);
            }
            else
            {
                try
                {
                    this.audioInputStream.reset();
                }
                catch (IOException ex)
                {
                    System.out.println("Exception resetting audio stream: " + ex.getMessage());
                }
            }
        }
    }
    
}
