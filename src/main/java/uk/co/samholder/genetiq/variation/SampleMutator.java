package uk.co.samholder.genetiq.variation;

import java.util.List;

/**
 * Mutates a child individual using information from n sampled individuals.
 * 
 * @author Sam Holder
 */
public interface SampleMutator<I> {
    
    /**
     * Get the number of individuals to sample.
     * 
     * @return number to sample
     */
    public int getNumberToSample();
    
    
    /**
     * Mutates the child individual.
     * 
     * If no modifications are made, the original individual may be returned.
     * 
     * @param individual individual to be modified
     * @param sampled list of sampled individuals
     * @return modified individual
     */
    public I mutate(I individual, List<I> sampled);
}
