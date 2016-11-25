package uk.co.samholder.genetiq.benchmark;

import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.representation.vector.Vector;

/**
 * @author Sam Holder
 */
public class AckleyBenchmark extends AbstractBenchmark {

    /**
     * @param negative flip the landscape such that (0, 0, ...) will be the global maxima.
     */
    public AckleyBenchmark(boolean negative) {
        super(negative);
    }
    
    @Override
    public double calculateFitness(Vector individual, Population<Vector> population) {
        double sum = 0;
        for (int i =0; i<individual.getDimensions()-1; i++) {
            double xi = individual.getValue(i);
            sum += Math.pow(xi, 2);
        }
        return (20 + Math.E - 20 * Math.exp(-0.2 * Math.sqrt(sum / (double)individual.getDimensions()))) * getSign();
    }
    
    
}