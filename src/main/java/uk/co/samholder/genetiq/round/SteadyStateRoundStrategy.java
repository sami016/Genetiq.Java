/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.round;

import java.util.List;
import uk.co.samholder.genetiq.combiner.Combiner;
import uk.co.samholder.genetiq.data.Period;
import uk.co.samholder.genetiq.mutator.Mutator;
import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.selection.Selector;

/**
 *
 * @author sam
 */
public class SteadyStateRoundStrategy<I extends Object> implements RoundStrategy<I> {

    private final Selector<I> selectionStrategy;
    private final Selector<I> replaceSelectionStrategy;
    private final Mutator<I> mutator;
    private final Combiner<I> combiner;

    public SteadyStateRoundStrategy(Selector<I> selectionStrategy, Selector<I> replaceSelectionStrategy, Mutator<I> mutator, Combiner<I> combiner) {
        this.selectionStrategy = selectionStrategy;
        this.replaceSelectionStrategy = replaceSelectionStrategy;
        this.mutator = mutator;
        this.combiner = combiner;
    }

    @Override
    public void performRound(Population<I> population) {
        // Select N individuals.
        List<I> selection = selectionStrategy.select(
                population,
                combiner.getNumberPerCrossover());
        // Combine them with crossover.
        I crossed = combiner.combine(selection);
        // mutate the result.
        I mutant = mutator.mutate(crossed);
        // Select the individual to replace.
        List<I> replacements = replaceSelectionStrategy.select(
                population,
                1
        );

//        System.out.println("Adding mutant " + mutant);
        // Do the replacement.
        population.replaceIndividual(replacements.get(0), mutant);
    }

    @Override
    public Period getPeriodType() {
        return Period.ROUNDS;
    }

}
