/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.population.coevolution;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import uk.co.samholder.genetiq.combiner.CoevolutionCombiner;
import uk.co.samholder.genetiq.fitness.FitnessFunction;
import uk.co.samholder.genetiq.individuals.Individual;
import uk.co.samholder.genetiq.individuals.IndividualFitness;
import uk.co.samholder.genetiq.individuals.Populator;
import uk.co.samholder.genetiq.population.Population;

/**
 *
 * @author sam
 */
public class PopulationSet<I extends Object> implements Iterable<Population<I>> {

    private Map<Integer, Population<I>> populations = new TreeMap<>();

    public void init(FitnessFunction<I> fitnessFunction, Populator<I> populator, int defaultSize, int populationSize, boolean postponeFitnessEvaluation) {
        // Generate all individuals.
        for (int i = 0; i < defaultSize; i++) {
            Population<I> population = new Population<>(fitnessFunction, i);
            for (int n = 0; n < populationSize; n++) {
                population.insertIndividual(populator.getIndividual(), postponeFitnessEvaluation);
            }
            populations.put(i, population);
        }
        // Calculate the fitnesses.
        for (int i = 0; i < defaultSize; i++) {
            populations.get(i).evaluatePostponedFitness();
        }

    }

    public void evaluatePostponedFitness() {
        for (Population<I> pop : populations.values()) {
            pop.evaluatePostponedFitness();
        }
    }

    public Population<I> getPopulation(int n) {
        return populations.get(n);
    }

    public Set<Integer> getPopulationIndexes() {
        return populations.keySet();
    }

    public <AGGREGATE extends Individual> IndividualFitness<AGGREGATE> getBestCombined(FitnessFunction<AGGREGATE> fitnessFunction, CoevolutionCombiner<I, AGGREGATE> combiner) {
        Map<Integer, I> map = new HashMap<>();
        for (Population<I> pop : populations.values()) {
            map.put(pop.getPopulationIndex(), pop.getBestIndividual().getIndividual());
        }
        AGGREGATE aggregate = combiner.combine(map);
        return new IndividualFitness(aggregate, fitnessFunction.calculateFitness(aggregate, null));
    }

    public int numPopulations() {
        return populations.keySet().size();
    }

    @Override
    public Iterator<Population<I>> iterator() {
        return populations.values().iterator();
    }

}
