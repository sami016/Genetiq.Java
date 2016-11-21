package uk.co.samholder.genetiq.util;

import java.util.Random;

/**
 * Generation utility for generating floating point numbers within a range.
 * @author Sam Holder
 */
public class GenerationRange {
    
    private Float upperBound;
    private Float lowerBound;

    /**
     * @param lowerBound lower bound
     * @param upperBound upper bound
     */
    public GenerationRange(float lowerBound, float upperBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }

    public GenerationRange() {
    }

    /**
     * Gets the lower bound value.
     * @return lower bound value
     */
    public Float getLowerBound() {
        if (lowerBound == null) {
            return Float.MIN_VALUE;
        }
        return lowerBound;
    }

    /**
     * Gets the upper bound value.
     * @return upper bound value
     */
    public Float getUpperBound() {
        if (upperBound == null) {
            return Float.MAX_VALUE;
        }
        return upperBound;
    }

    /**
     * Generates a random number within the range.
     * @param random pseudo-random number generator
     * @return value within range
     */
    public float Generate(Random random) {
        return random.nextFloat() * (getUpperBound() - getLowerBound()) + getLowerBound();
    }
}