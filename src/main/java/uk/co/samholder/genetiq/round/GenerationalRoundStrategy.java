/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.round;

import java.util.ArrayList;
import java.util.List;
import uk.co.samholder.genetiq.combiner.Combiner;
import uk.co.samholder.genetiq.data.Period;
import uk.co.samholder.genetiq.individuals.IndividualFitness;
import uk.co.samholder.genetiq.mutator.Mutator;
import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.selection.Selector;

/**
 *
 * @author sam
 */
public class GenerationalRoundStrategy<I extends Object> implements RoundStrategy<I> {

    private final Selector<I> selectionStrategy;
    private final Mutator<I> mutator;
    private final Combiner<I> combiner;
    private final int elitismCount;

    public GenerationalRoundStrategy(Selector<I> selectionStrategy, Mutator<I> mutator, Combiner<I> combiner) {
        this(selectionStrategy, mutator, combiner, 0);
    }

    public GenerationalRoundStrategy(Selector<I> selectionStrategy, Mutator<I> mutator, Combiner<I> combiner, int elitismCount) {
        this.selectionStrategy = selectionStrategy;
        this.mutator = mutator;
        this.combiner = combiner;
        this.elitismCount = elitismCount;
    }

    @Override
    public void performRound(Population<I> population) {
        // Clone the population.
        List<I> newPopulation = new ArrayList<>();
        // Take most elite individuals first.
        for (int i = 0; i < elitismCount; i++) {
            IndividualFitness<I> best = population.getBestIndividual();
            newPopulation.add(best.getIndividual());
        }
        // Select individuals from the population.
        for (int i = 0; i < population.size() - elitismCount; i++) {
            I combined;
            if (combiner != null) {
                List<I> selection = selectionStrategy.select(
                        population,
                        combiner.getNumberPerCrossover());
                // Do crossover.
                combined = combiner.combine(selection);
            } else {
                combined = selectionStrategy.select(population, 1).get(0);
            }
            // Do mutation.
            I mutant = mutator.mutate(combined);

            // Add the mutant to the new pool.
            newPopulation.add(mutant);
        }
        // Set the population to the new one.
        population.clear();
        population.insertIndividuals(newPopulation, false);
    }

    @Override
    public Period getPeriodType() {
        return Period.GENERATIONS;
    }

}
