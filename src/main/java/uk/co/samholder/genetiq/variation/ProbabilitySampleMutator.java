package uk.co.samholder.genetiq.variation;

import java.util.List;
import java.util.Random;

/**
 * Decorator for selectively sample mutating an individual with a given probability.
 * @author Sam Holder
 */
public class ProbabilitySampleMutator<I> implements SampleMutator<I>{

    private SampleMutator<I> sampleMutator;
    private float probability;
    private Random random;

    /**
     * @param sampleMutator sample mutator to wrap
     * @param probability probability of being used to change individual
     * @param random pseudo-random number generator
     */
    public ProbabilitySampleMutator(SampleMutator<I> sampleMutator, float probability, Random random) {
        this.sampleMutator = sampleMutator;
        this.probability = probability;
        this.random = random;
    }
    
    @Override
    public int getNumberToSample() {
        return sampleMutator.getNumberToSample();
    }

    @Override
    public I mutate(I individual, List<I> sampled) {
        if (probability < random.nextFloat()) {
            return sampleMutator.mutate(individual, sampled);
        }
        return individual;
    }
    
    
}
