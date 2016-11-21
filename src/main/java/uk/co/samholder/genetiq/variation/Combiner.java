package uk.co.samholder.genetiq.variation;

import java.util.List;

/**
 * Combines one or more individual to create a new child individual.
 * @author Sam Holder
 */
public interface Combiner<I> {

    /**
     * Get the number of individuals to combine.
     * 
     * @return number to combine
     * @param <I> individual type
     */
    int getNumberToCombine();

    /**
     * Combine the set of n individuals into a new child.
     * @param individuals list of individuals
     * @return child individual
     */
    I combine(List<I> individuals);

}
