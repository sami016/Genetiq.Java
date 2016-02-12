/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.runner.genetic.builder;

import uk.co.samholder.genetiq.combiner.Combiner;
import uk.co.samholder.genetiq.mutator.Mutator;
import uk.co.samholder.genetiq.round.GenerationalRoundStrategy;
import uk.co.samholder.genetiq.round.RoundStrategy;
import uk.co.samholder.genetiq.round.SteadyStateRoundStrategy;
import uk.co.samholder.genetiq.selection.Selector;

/**
 * Builds round strategies.
 *
 * @author sam
 */
public class RoundStrategyBuilder<I extends Object> {

    private RoundStrategy<I> roundStrategy;

    /**
     * Use an arbitrary round strategy.
     *
     * @param roundStrategy the round strategy.
     */
    public void use(RoundStrategy<I> roundStrategy) {
        this.roundStrategy = roundStrategy;
    }

    /**
     * Use a generational round strategy.
     *
     * @param selectionStrategy The selection strategy for how to select
     * individuals for reproduction.
     * @param mutatorOperator The mutation operator for how to modify
     * individuals.
     * @param combinerOperator The combiner operator for combining several
     * individuals. (e.g. crossover)
     */
    public void useGenerational(Selector<I> selectionStrategy, Mutator<I> mutatorOperator, Combiner<I> combinerOperator) {
        use(new GenerationalRoundStrategy<>(
                selectionStrategy,
                mutatorOperator,
                combinerOperator
        ));
    }

    /**
     * Use a generational round strategy.
     *
     * @param selectionStrategy The selection strategy for how to select
     * individuals for reproduction.
     * @param mutatorOperator The mutation operator for how to modify
     * individuals.
     */
    public void useGenerational(Selector<I> selectionStrategy, Mutator<I> mutatorOperator) {
        RoundStrategyBuilder.this.useGenerational(selectionStrategy, mutatorOperator, null);
    }

    /**
     * Use a generational round strategy.
     *
     * @param selectionStrategy The selection strategy for how to select
     * individuals for reproduction.
     * @param replacementStrategy The replacement strategy for how to select
     * individuals that are replaced when a new child is born.
     * @param mutatorOperator The mutation operator for how to modify
     * individuals.
     * @param combinerOperator The combiner operator for combining several
     * individuals. (e.g. crossover)
     */
    public void useSteadyState(Selector<I> selectionStrategy, Selector<I> replacementStrategy, Mutator<I> mutatorOperator, Combiner<I> combinerOperator) {
        use(new SteadyStateRoundStrategy<>(
                selectionStrategy,
                replacementStrategy,
                mutatorOperator,
                combinerOperator
        ));
    }

    /**
     * Use a generational round strategy.
     *
     * @param selectionStrategy The selection strategy for how to select
     * individuals for reproduction.
     * @param replacementStrategy The replacement strategy for how to select
     * individuals that are replaced when a new child is born.
     * @param mutatorOperator The mutation operator for how to modify
     * individuals.
     */
    public void useSteadyState(Selector<I> selectionStrategy, Selector<I> replacementStrategy, Mutator<I> mutatorOperator) {
        useSteadyState(selectionStrategy, replacementStrategy, mutatorOperator, null);
    }

    /**
     * Builds and validates the round strategy.
     *
     * @return the round strategy.
     */
    public RoundStrategy<I> build() {
        if (roundStrategy == null) {
            throw new IllegalStateException("Round strategy has not been initialised");
        }
        return roundStrategy;
    }

}
