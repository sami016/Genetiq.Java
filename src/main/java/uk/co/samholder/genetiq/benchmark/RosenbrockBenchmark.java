package uk.co.samholder.genetiq.benchmark;

import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.representation.vector.Vector;

/**
 * @author Sam Holder
 */
public class RosenbrockBenchmark extends AbstractBenchmark {

    /**
     * @param negative flip the landscape such that (0, 0, ...) will be the global maxima.
     */
    public RosenbrockBenchmark(boolean negative) {
        super(negative);
    }
    
    @Override
    public double calculateFitness(Vector individual, Population<Vector> population) {
        double sum = 0;
        for (int i =0; i<individual.getDimensions()-1; i++) {
            double xi = individual.getValue(i);
            double xip1 = individual.getValue(i+1);
            sum += 100 * Math.pow(xip1 - Math.pow(xi, 2), 2) + Math.pow(xi - 1, 2);
        }
        return sum * getSign();
    }
    
    
}
