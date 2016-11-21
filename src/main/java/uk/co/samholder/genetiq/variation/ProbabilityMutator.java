package uk.co.samholder.genetiq.variation;

import java.util.Random;

/**
 * Decorator for selectively mutating an individual with a given probability.
 * @author Sam Holder
 */
public class ProbabilityMutator<I> implements Mutator<I> {
    
    private final Mutator<I> mutator;
    private final float probability;
    private final Random random;

    /**
     * @param mutator mutator to wrap
     * @param probability probability of being used to change individual
     * @param random pseudo-random number generator
     */
    public ProbabilityMutator(Mutator<I> mutator, float probability, Random random) {
        this.mutator = mutator;
        this.probability = probability;
        this.random = random;
    }

    @Override
    public I mutate(I individual) {
        if (random.nextFloat() < probability) {
            return mutator.mutate(individual);
        }
        return individual;
    }
    
    
}
