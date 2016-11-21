package uk.co.samholder.genetiq.variation;

import java.util.ArrayList;
import java.util.List;

/**
 * A pipeline of variation operators including mutators and combiners.
 * 
 * @author Sam Holder
 */
public class VariationPipeline<I> {
    
    private final List<SampleMutator<I>> sampleMutators = new ArrayList<>();
    private final List<Mutator<I>> mutators = new ArrayList<>();
    private Combiner<I> combiner;

    /**
     * Gets the combiner operator used or creating new individuals.
     * @return combiner
     */
    public Combiner<I> getCombiner() {
        return combiner;
    }

    /**
     * Sets the combiner operator used for creating new individuals.
     * @param combiner combiner
     */
    public void setCombiner(Combiner combiner) {
        this.combiner = combiner;
    }

    /**
     * Gets the mutable list of mutators applicable when creating child individuals.
     * @return mutator list
     */
    public List<Mutator<I>> getMutators() {
        return mutators;
    }

    /**
     * Gets the mutable list of sample mutators applicable when creating child individuals.
     * @return sample mutator list
     */
    public List<SampleMutator<I>> getSampleMutators() {
        return sampleMutators;
    }
    
    /**
     * Adds a mutator to the pipeline.
     * @param mutator mutator
     */
    public void addMutator(Mutator mutator) {
        mutators.add(mutator);
    }
    
    /**
     * Adds a sample mutator to the pipeline.
     * @param sampleMutator mutator
     */
    public void addSampleMutator(SampleMutator sampleMutator) {
        sampleMutators.add(sampleMutator);
    }

}
