package uk.co.samholder.genetiq.benchmark;

import uk.co.samholder.genetiq.fitness.FitnessFunction;
import uk.co.samholder.genetiq.representation.vector.Vector;

/**
 * @author Sam Holder
 */
public abstract class AbstractBenchmark implements FitnessFunction<Vector> {

    private final boolean negative;

    /**
     * @param negative flip the landscape such that minima become maxima
     */
    public AbstractBenchmark(boolean negative) {
        this.negative = negative;
    }
    
    public double getSign() {
        return negative ? -1f : 1f;
    }
    
    
    
}
