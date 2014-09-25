package scrummaster.randomize;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import scrummaster.Candidate;
import scrummaster.Constants;
import scrummaster.History;

/**
 *
 * @author Nick
 */
public class ProbabilityGenerator
{
    
    private Map<Candidate, Double> probabilityMap;
    private History history;
    
    public ProbabilityGenerator(History history)
    {
        this.probabilityMap = new EnumMap<>(Candidate.class);
        this.history = history;
        this.generate();
    }
    
    private void generate()
    {
        final double latest = this.history.getLatestIteration().doubleValue();
        final double totalProducts = this.sumOfIterationsProduct();
        for (Candidate candidate : Candidate.values())
        {
            if (!candidate.equals(history.getLatestCandidate()) && !candidate.equals(history.getSecondLastCandidate()))
            {
                List<Integer> iterations = history.getIterationsForCandidate(
                        candidate);
                double prob = this.generateProbability(iterations,
                        totalProducts);
                if (Constants.DEBUG)
                {
                    System.out.println("Candidate: " + candidate.getFirstName()
                            + " Prob: " + prob);
                }
                probabilityMap.put(candidate, prob);
            }
            else
            {
                probabilityMap.put(candidate, 0.0);
            }
        }
        normalize();
        if (Constants.DEBUG)
        {
            double sum = getMapSum();
            if (Math.round(sum) != Math.round(Constants.BASE_PROBABILITY))
            {
                System.err.println("Something's wrong: Expected "
                        + Constants.BASE_PROBABILITY
                        + " sum after normalization. Actual sum: " + sum);
            }
            
            System.out.println("Probabilities generated:\n" + probabilityMap.
                    toString());
        }
        
        
    }
    
    public double getProbability(Candidate candidate)
    {
        return probabilityMap.get(candidate);
    }
    
    public Map<Candidate, Double> getProbabilityMap()
    {
        return this.probabilityMap;
    }
    
    private Double generateProbability(List<Integer> iterations,
            final double totalProducts)
    {
        final double product = this.iterationsProduct(iterations);
        
        if (product <= 1)
        {
            return Constants.BASE_PROBABILITY;
        }
        else
        {
            return (Constants.PREVIOUS_MASTER_BASE_PROBABILITY)
                    - ((Constants.PREVIOUS_MASTER_BASE_PROBABILITY)
                    * product / totalProducts);
        }
        
        
    }
    
    private void normalize()
    {
        double sum = getMapSum();
        for (Candidate candidate : this.probabilityMap.keySet())
        {
            final double prob = this.probabilityMap.get(candidate);
            this.probabilityMap.put(candidate, Constants.BASE_PROBABILITY * prob
                    / sum);
        }
    }
    
    private double getMapSum()
    {
        double sum = 0;
        for (double prob : this.probabilityMap.values())
        {
            sum += prob;
        }
        return sum;
    }
    
    private double sumOfIterationsProduct()
    {
        double result = 0;
        for (Candidate candidate : Candidate.values())
        {
            if (!candidate.equals(history.getLatestCandidate()))
            {
                List<Integer> iterations = history.getIterationsForCandidate(
                        candidate);
                result += this.iterationsProduct(iterations);
            }
        }
        return result;
    }
    
    private double iterationsProduct(List<Integer> iterations)
    {
        double product = 1;
        for (int iter : iterations)
        {
            product *= (double) iter;
        }
        return product;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("\nProbability Map:\n");
        for (Candidate candidate : probabilityMap.keySet())
        {
            String name = candidate.getName();
            double prob = probabilityMap.get(candidate);
            sb.append("\tCandidate: ");
            sb.append(name);
            sb.append("\tProbability: ");
            sb.append(prob);
            sb.append("\n");
        }
        sb.append('\n');
        return sb.toString();
    }
}
