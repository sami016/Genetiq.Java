package uk.co.samholder.genetiq.benchmark;

import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.representation.vector.Vector;

/**
 * @author Sam Holder
 */
public class SphereBenchmark extends AbstractBenchmark {

    /**
     * @param negative flip the landscape such that (0, 0, ...) will be the global maxima.
     */
    public SphereBenchmark(boolean negative) {
        super(negative);
    }
    
    @Override
    public double calculateFitness(Vector individual, Population<Vector> population) {
        double sum = 0;
        for (int i =0; i<individual.getDimensions(); i++) {
            sum += Math.pow(individual.getValue(i), 2);
        }
        return sum * getSign();
    }
    
    
}
