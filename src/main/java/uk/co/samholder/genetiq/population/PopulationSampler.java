package uk.co.samholder.genetiq.population;

import java.util.List;

/**
 * Samples individuals from the population using some selection strategy.
 * @author Sam Holder
 */
public interface PopulationSampler<I> {
    
    /**
     * Samples an individual from a population.
     * @param numberOfIndividuals number of individuals to sample
     * @return sampled list of individuals
     */
    public List<I> sample(int numberOfIndividuals);
    
}
