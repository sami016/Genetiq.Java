package uk.co.samholder.genetiq.population;

import java.util.List;

/**
 * Samples individuals from the population using some selection strategy.
 * @author Sam Holder
 */
public interface PopulationSampler<I> {
    
    public List<I> sample(int numberOfIndividuals);
    
}
