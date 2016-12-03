package uk.co.samholder.genetiq.benchmark;

import uk.co.samholder.genetiq.representation.vector.Vector;

/**
 * @author Sam Holder
 */
public class GriewagnkBenchmark extends AbstractBenchmark {

    /**
     * @param negative flip the landscape such that (0, 0, ...) will be the global maxima.
     */
    public GriewagnkBenchmark(boolean negative) {
        super(negative);
    }
    
    @Override
    public double calculateFitness(Vector individual) {
        double sum = 0;
        double product = 1;
        for (int i =0; i<individual.getDimensions()-1; i++) {
            double xi = individual.getValue(i);
            sum += Math.pow(xi, 2) / 4000.0;
            product *= Math.cos(xi / Math.sqrt(i));
        }
        double calculated = 1 + sum - product;
        return calculated * getSign();
    }
    
    
}
