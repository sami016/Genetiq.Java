package uk.co.samholder.genetiq.variation;

import java.util.List;
import uk.co.samholder.genetiq.population.PopulationSampler;

/**
 * Standard implementation of the variation engine.
 * @author Sam Holder
 */
public class StandardVariationEngine<I> implements VariationEngine<I>{

    private final VariationPipeline<I> pipeline;

    public StandardVariationEngine(VariationPipeline<I> pipeline) {
        this.pipeline = pipeline;
    }
    
    
    @Override
    public I createChild(PopulationSampler<I> sampler) {
        return applyMutations(
            applySampleMutations(
                    combineOrDefault(sampler), 
                    sampler
            )
        );
    }
 
    private I combineOrDefault(PopulationSampler<I> sampler) {
        if (pipeline.getCombiner() == null) {
            return sampler.sample(1).get(0);
        }
        return combine(sampler);
    }
    
    /**
     * Combines a set of sampled individuals to create a new child.
     * @param child child to mutate
     * @return mutated child
     */
    private I combine(PopulationSampler<I> sampler) {
        List<I> sampled = sampler.sample(pipeline.getCombiner().getNumberToCombine());
        return pipeline.getCombiner().combine(sampled);
    }
    
    /**
     * Applies sample mutations to a child.
     * @param child child to mutate
     * @return mutated child
     */
    private I applySampleMutations(I child, PopulationSampler<I> sampler) {
        for (SampleMutator<I> sampleMutator : pipeline.getSampleMutators()) {
            List<I> sampled = sampler.sample(sampleMutator.getNumberToSample());
            child = sampleMutator.mutate(child, sampled);
        }
        return child;
    }
    
    /**
     * Applies mutations to a child.
     * @param child child to mutate
     * @return mutated child
     */
    protected I applyMutations(I child) {
        for (Mutator<I> mutator : pipeline.getMutators()) {
            child = mutator.mutate(child);
        }
        return child;
    }
}
