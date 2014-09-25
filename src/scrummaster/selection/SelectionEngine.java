package scrummaster.selection;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import scrummaster.Candidate;
import scrummaster.randomize.RandomCandidateGenerator;
import scrummaster.Constants;
import scrummaster.audio.BeepPlayer;

/**
 *
 * @author Nick
 */
public class SelectionEngine extends SwingWorker<Candidate, Candidate>
{

    private final RandomCandidateGenerator generator;
    private final Random random;
    private final Map<Candidate, JPanel> panels;
    private final JProgressBar progressBar;
    private long duration;
    private long step;
    private long interval;

    public SelectionEngine(RandomCandidateGenerator generator, Random random, Map<Candidate, JPanel> panels,
            JProgressBar progressBar)
    {
        this.generator = generator;
        this.random = random;
        this.panels = panels;
        this.progressBar = progressBar;
        this.duration = 0;
        this.interval = Constants.START_INTERVAL_MS;
        this.step = this.getRandomStep();
    }

    @Override
    public Candidate doInBackground()
    {
        Candidate currentCandidate = generator.getRandomCandidate();

        while (duration < Constants.TOTAL_DURATION_MS)
        {
            try
            {
                currentCandidate = generator.getRandomCandidate();
                publish(currentCandidate);
                this.interval += step;
                this.step = this.getRandomStep();
                Thread.sleep(this.interval);
                this.duration += this.interval;
                this.progressBar.setValue((int) this.duration);
                if (Constants.DEBUG)
                {
                    System.out.println("Duration: " + (double) duration / 1000
                            + ",  Remaining: "
                            + (double) (Constants.TOTAL_DURATION_MS - duration)
                            / 1000);
                }
            }
            catch (InterruptedException ex)
            {
                System.out.println("Interrupted: " + ex.getMessage());
            }
        }
        return currentCandidate;
    }

    @Override
    public void process(List<Candidate> candidates)
    {
        Candidate latestCandidate = candidates.get(candidates.size() - 1);
        this.setAllPanelsWhite();
        JPanel currentPanel = panels.get(latestCandidate);
        currentPanel.setBackground(Color.red);
        new Thread(new BeepPlayer()).start();
    }

    @Override
    public void done()
    {
        try
        {
            Candidate finalCandidate = get();
            this.firePropertyChange(Constants.FINAL_CANDIDATE_EVENT, null, finalCandidate);
        }
        catch (InterruptedException ex)
        {
            System.out.println("Interrupted: " + ex.getMessage());
        }
        catch (ExecutionException ex)
        {
            System.out.println("Execution failed: " + ex.getMessage());
        }

    }

    private long getRandomStep()
    {
        return (long) (Constants.MAX_STEP_MS * random.nextDouble());
    }

    private void setAllPanelsWhite()
    {
        for (Candidate candidate : Candidate.values())
        {
            JPanel currentPanel = panels.get(candidate);
            currentPanel.setBackground(Color.white);
        }
    }
    
}
