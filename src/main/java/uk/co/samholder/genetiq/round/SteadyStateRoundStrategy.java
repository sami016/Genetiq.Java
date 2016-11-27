package uk.co.samholder.genetiq.round;

import uk.co.samholder.genetiq.data.Period;
import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.population.PopulationSampler;
import uk.co.samholder.genetiq.selection.Selector;
import uk.co.samholder.genetiq.variation.VariationEngine;

/**
 * Steady state round strategy where a round consists of a single individual
 * being removed, and a single individual being added.
 *
 * @author Sam Holder
 */
public class SteadyStateRoundStrategy<I> implements RoundStrategy<I> {

    private final Selector<I> replaceSelectionStrategy;

    /**
     * @param replaceSelectionStrategy selector used to select individuals for replacement
     */
    public SteadyStateRoundStrategy(Selector<I> replaceSelectionStrategy) {
        this.replaceSelectionStrategy = replaceSelectionStrategy;
    }

    @Override
    public void performRound(Population<I> population, VariationEngine<I> variationEngine, Selector<I> selector) {
        PopulationSampler<I> sampler = population.createSampler(selector);
        // mutate the result.
        I child = variationEngine.createChild(sampler);
        // Select the individual to replace.
        I replacement = replaceSelectionStrategy.select(
                population,
                1
        ).get(0);
        // Do the replacement.
        population.replaceIndividual(replacement, child);
    }

    @Override
    public Period getPeriodType() {
        return Period.ROUNDS;
    }

}
