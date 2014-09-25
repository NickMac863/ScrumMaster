/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scrummaster.audio;

import javax.swing.SwingWorker;
import scrummaster.Constants;

/**
 *
 * @author Nick
 */
public class DrumPlayer extends SwingWorker
{
    private SoundUtil soundUtil;
    
    public DrumPlayer()
    {
        this.soundUtil = new SoundUtil(Constants.DRUMS_WAV_PATH);
    }

    @Override
    protected Object doInBackground() throws Exception
    {
        while(!this.isCancelled())
        {
            this.soundUtil.playSound();
        }
        return null;
    }
    
}
