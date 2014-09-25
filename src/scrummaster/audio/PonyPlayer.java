package scrummaster.audio;

import javax.swing.SwingWorker;
import scrummaster.Constants;

/**
 *
 * @author Nick
 */
public class PonyPlayer extends SwingWorker
{
    private SoundUtil soundUtil; 
    
    public PonyPlayer()
    {
        this.soundUtil = new SoundUtil(Constants.PONY_WAV_PATH);
    }

    @Override
    protected Object doInBackground()
    {
        while (!this.isCancelled())
        {
            this.soundUtil.playSound();
        }
        return null;
    }

}
