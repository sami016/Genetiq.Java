package uk.co.samholder.genetiq.benchmark;

import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.representation.vector.Vector;

/**
 * @author Sam Holder
 */
public class SchwefelsBenchmark extends AbstractBenchmark {

    /**
     * @param negative flip the landscape such that minima become maxima
     */
    public SchwefelsBenchmark(boolean negative) {
        super(negative);
    }
    
    @Override
    public double calculateFitness(Vector individual, Population<Vector> population) {
        double sum = 0;
        for (int i =0; i<individual.getDimensions(); i++) {
            double innerSum = 0;
            for (int j =0; j<=i; j++) {
                innerSum += individual.getValue(j);
            }
            sum += Math.pow(innerSum, 2);
        }
        return sum * getSign();
    }
    
    
}
