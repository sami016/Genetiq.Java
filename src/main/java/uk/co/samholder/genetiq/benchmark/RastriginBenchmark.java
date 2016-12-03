package uk.co.samholder.genetiq.benchmark;

import uk.co.samholder.genetiq.representation.vector.Vector;

/**
 * @author Sam Holder
 */
public class RastriginBenchmark extends AbstractBenchmark {

    /**
     * @param negative flip the landscape such that (0, 0, ...) will be the global maxima.
     */
    public RastriginBenchmark(boolean negative) {
        super(negative);
    }
    
    @Override
    public double calculateFitness(Vector individual) {
        double sum = 10 * individual.getDimensions();
        for (int i =0; i<individual.getDimensions()-1; i++) {
            double xi = individual.getValue(i);
            sum += Math.pow(xi, 2) - 10 * Math.cos(2 * Math.PI * xi);
        }
        return sum * getSign();
    }
    
    
}
