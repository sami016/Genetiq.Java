package uk.co.samholder.genetiq.round;

import uk.co.samholder.genetiq.data.Period;
import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.selection.Selector;
import uk.co.samholder.genetiq.variation.VariationEngine;

/**
 * Determines how each round of the genetic algorithm is run, an integral part
 * to the genetic algorithm.
 *
 * @author Sam Holder
 */
public interface RoundStrategy<I extends Object> {

    /**
     * Performs the round.
     * @param population 
     */
    public void performRound(Population<I> population, VariationEngine<I> variationEngine, Selector<I> selector);

    /**
     * Gets the period type of the round.
     * @return period type
     */
    public Period getPeriodType();

}
