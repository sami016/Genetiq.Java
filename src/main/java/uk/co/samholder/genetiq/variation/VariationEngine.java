package uk.co.samholder.genetiq.variation;

import uk.co.samholder.genetiq.population.PopulationSampler;

/**
 * Performs the variation based on a variation profile.
 * @author Sam Holder
 */
public interface VariationEngine<I> {

    public I createChild(PopulationSampler<I> sampler);
    
}
