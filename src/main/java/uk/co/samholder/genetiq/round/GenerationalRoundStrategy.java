/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.round;

import java.util.ArrayList;
import java.util.List;
import uk.co.samholder.genetiq.data.Period;
import uk.co.samholder.genetiq.population.IndividualFitness;
import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.population.PopulationSampler;
import uk.co.samholder.genetiq.selection.Selector;
import uk.co.samholder.genetiq.variation.VariationEngine;

/**
 * Generation round strategy, where each individual in the population is
 * replaced each round by a new set of offspring.
 *
 * @author Sam Holder
 */
public class GenerationalRoundStrategy<I> implements RoundStrategy<I> {

    private final int elitismCount;

    public GenerationalRoundStrategy() {
        this(0);
    }

    public GenerationalRoundStrategy(int elitismCount) {
        this.elitismCount = elitismCount;
    }

    @Override
    public void performRound(Population<I> population, VariationEngine<I> variationEngine, Selector<I> selector) {
        PopulationSampler<I> sampler = population.createSampler(selector);
        // Clone the population.
        List<I> newPopulation = new ArrayList<>();
        // Take most elite individuals first.
        for (int i = 0; i < elitismCount; i++) {
            IndividualFitness<I> best = population.getBestIndividual();
            newPopulation.add(best.getIndividual());
        }
        // Select individuals from the population.
        for (int i = 0; i < population.size() - elitismCount; i++) {
            // Add the mutant to the new pool.
            newPopulation.add(variationEngine.createChild(sampler));
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
