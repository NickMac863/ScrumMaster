package scrummaster.randomize;

import java.util.Random;
import scrummaster.Candidate;
import scrummaster.Constants;

/**
 *
 * @author Nick
 */
public class RandomCandidateGenerator
{

    private final ProbabilityGenerator probGen;
    private final Random random;

    public RandomCandidateGenerator(ProbabilityGenerator probGen, Random random)
    {
        this.probGen = probGen;
        this.random = random;
    }

    public Candidate getRandomCandidate()
    {
        final double rand = Constants.BASE_PROBABILITY * random.nextDouble();
        double index = 0.0;
        for (Candidate candidate : Candidate.values())
        {
            double currentProb = probGen.getProbability(candidate);
            index += currentProb;
            if (index > rand && currentProb > 0)
            {
                return candidate;
            }
        }
        if (index > Constants.BASE_PROBABILITY && Constants.DEBUG)
        {
            System.err.println(
                    "Something's wrong: range index is expected to be "
                    + Constants.BASE_PROBABILITY + " Actually: " + index);
        }
        return null;
    }
}
