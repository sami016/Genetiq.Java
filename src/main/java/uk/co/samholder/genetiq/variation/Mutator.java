package uk.co.samholder.genetiq.variation;

/**
 * Mutates a child individual.
 * 
 * @author Sam Holder
 * @param <I> individual type
 */
public interface Mutator<I extends Object> {

    /**
     * Mutates the child individual.
     * 
     * If no modifications are made, the original individual may be returned.
     * 
     * @param individual individual to be modified
     * @return modified individual
     */
    I mutate(I individual);

}
