
package uk.co.samholder.genetiq.population;

/**
 * Creates initial seed individuals within the population at initialisation.
 * @author Sam Holder
 * @param <I> individual
 */
public interface Populator<I> {

    I getIndividual();

}
