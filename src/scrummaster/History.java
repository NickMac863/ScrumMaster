package scrummaster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nick
 */
public class History
{

    private Map<Integer, Candidate> pastIterations;

    public History()
    {
        this.pastIterations = new HashMap<>();
        HistoryFileParser parser = new HistoryFileParser();
        try 
        {
            this.pastIterations = parser.parseFileForHistory(Constants.HISTORY_FILENAME);
        }
        catch(IOException | NumberFormatException e)
        {
            if(Constants.DEBUG)
            {
                System.out.println("Failure reading history from file: " + e.getMessage());
            }
            this.loadDefaults();
        }
    }

    private void loadDefaults()
    {
        this.pastIterations.put(1, Candidate.YASIR);
        this.pastIterations.put(2, Candidate.JOHN);
        this.pastIterations.put(3, Candidate.YASIR);
        this.pastIterations.put(4, Candidate.DAVID);
        this.pastIterations.put(5, Candidate.DAVID);
        this.pastIterations.put(6, Candidate.DAVID);
        this.pastIterations.put(7, Candidate.JUSTIN);
        this.pastIterations.put(8, Candidate.JUSTIN);
        this.pastIterations.put(9, Candidate.ANDREW);
        this.pastIterations.put(10, Candidate.ANDREW);
        this.pastIterations.put(11, Candidate.ANDREW);
        this.pastIterations.put(12, Candidate.AMANDA);
        this.pastIterations.put(13, Candidate.AMANDA);
        this.pastIterations.put(14, Candidate.AMANDA);
        this.pastIterations.put(15, Candidate.NICK);
        this.pastIterations.put(16, Candidate.NICK);
        this.pastIterations.put(17, Candidate.DAVID);
    }

    public List<Integer> getIterationsForCandidate(Candidate candidate)
    {
        List<Integer> result = new ArrayList<>();
        for (Integer key : this.pastIterations.keySet())
        {
            if (this.pastIterations.get(key).equals(candidate))
            {
                result.add(key);
            }
        }
        return result;
    }

    public Integer getLatestIteration()
    {
        int highest = 0;
        for (int i : this.pastIterations.keySet())
        {
            highest = Math.max(i, highest);
        }
        return highest;
    }

    public Candidate getLatestCandidate()
    {
        return this.pastIterations.get(this.getLatestIteration());
    }

    public Candidate getSecondLastCandidate()
    {
        Candidate latestCandidate = this.getLatestCandidate();
        int latestIteration = this.getLatestIteration();
        for (int i = latestIteration; i >= 0; i--)
        {
            Candidate currentCandidate = this.pastIterations.get(i);
            if (currentCandidate != null && !currentCandidate.equals(latestCandidate))
            {
                return currentCandidate;
            }
        }
        return latestCandidate;
    }

    @Override
    public String toString()
    {
        return this.pastIterations.toString();
    }
}
