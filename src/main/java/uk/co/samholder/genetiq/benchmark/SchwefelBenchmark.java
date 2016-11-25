package uk.co.samholder.genetiq.benchmark;

import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.representation.vector.Vector;

/**
 * @author Sam Holder
 */
public class SchwefelBenchmark extends AbstractBenchmark {

    /**
     * @param negative flip the landscape such that (0, 0, ...) will be the global maxima.
     */
    public SchwefelBenchmark(boolean negative) {
        super(negative);
    }
    
    @Override
    public double calculateFitness(Vector individual, Population<Vector> population) {
        double sum = 418.9829 * individual.getDimensions();
        for (int i =0; i<individual.getDimensions()-1; i++) {
            double xi = individual.getValue(i);
            sum += xi * Math.sin(Math.sqrt(Math.abs(xi)));
        }
        return sum * getSign();
    }
    
    
}
