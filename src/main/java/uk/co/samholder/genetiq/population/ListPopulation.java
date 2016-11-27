/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.population;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import uk.co.samholder.genetiq.fitness.FitnessFunction;
import uk.co.samholder.genetiq.selection.Selector;

/**
 * Encapsulates a population of individuals.
 *
 * @author Sam Holder
 */
public class ListPopulation<I> implements Population<I> {

    // The capacity of the population.
    private final int capacity;
    // The population index for cases with multiple populations.
    private int populationIndex;
    // The fitness function used to assess individuals.
    private FitnessFunction<I> fitnessFunction;
    // The list of individuals.
    private final List<I> individuals;
    // The current fitness of each individual.
    private final Map<I, Double> fitnessMapping;

    // State for postponed fitness evaluation.
    private final Set<I> uncalculated;
    // Tracks whether fitnesses have yet to be calculated.
    private boolean initialPhase;

    /**
     * Creates an empty population.
     *
     * @param capacity capacity
     * @param fitnessFunction fitness function
     * @param populationIndex an index used to identify the population
     */
    public ListPopulation(int capacity, FitnessFunction<I> fitnessFunction, int populationIndex) {
        this.capacity = capacity;
        this.fitnessFunction = fitnessFunction;
        this.populationIndex = populationIndex;
        this.individuals = new ArrayList<>();
        this.fitnessMapping = new HashMap<>();
        this.uncalculated = new HashSet<>();
        initialPhase = false;
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public int getPopulationIndex() {
        return populationIndex;
    }

    @Override
    public void clear() {
        individuals.clear();
        fitnessMapping.clear();
    }

    @Override
    public void removeIndividual(I individual) {
        individuals.remove(individual);
        // Remove momoized value if no duplicates remain in population.
        if (!individuals.contains(individual)) {
            fitnessMapping.remove(individual);
        }
    }

    @Override
    public void insertIndividual(I individual) {
        individuals.add(individual);
        fitnessMapping.put(individual, fitnessFunction.calculateFitness(individual, this));
    }

    @Override
    public void insertIndividual(I individual, boolean postponeFitnessCalculation) {
        if (postponeFitnessCalculation) {
            individuals.add(individual);
            fitnessMapping.put(individual, null);
            uncalculated.add(individual);
            initialPhase = true;
        } else {
            insertIndividual(individual);
        }
    }

    @Override
    public boolean isInitialPhase() {
        return initialPhase;
    }

    @Override
    public void insertIndividuals(List<I> individuals, boolean postponeFitnessCalculation) {
        for (I individual : individuals) {
            insertIndividual(individual, postponeFitnessCalculation);
        }
    }

    @Override
    public void replaceIndividual(I original, I novel) {
        removeIndividual(original);
        insertIndividual(novel);
    }

    @Override
    public void evaluatePostponedFitness() {
        for (I individual : uncalculated) {
            fitnessMapping.put(individual, fitnessFunction.calculateFitness(individual, this));
        }
        initialPhase = false;
    }

    /**
     * Throws illegal state exception if fitness values are accessed while in
     * the initial phase.
     */
    private void assertNoPostponedFitnessEval() {
        if (initialPhase) {
            throw new IllegalStateException("Population has pending fitness evaluations that must be calculated before use.");
        }
    }

    @Override
    public IndividualFitness<I> getIndividualAtIndex(int id) {
        assertNoPostponedFitnessEval();

        I individual = individuals.get(id);
        return new IndividualFitness<>(individual, fitnessMapping.get(individual));
    }

    @Override
    public IndividualFitness<I> getWorstIndividual() {
        assertNoPostponedFitnessEval();

        Double worstFitness = null;
        I worstIndividual = null;
        for (I individual : individuals) {
            double currentFitness = fitnessMapping.get(individual);
            if (worstFitness == null || currentFitness < worstFitness) {
                worstIndividual = individual;
                worstFitness = currentFitness;
            }
        }
        return new IndividualFitness<>(worstIndividual, worstFitness);
    }

    @Override
    public IndividualFitness<I> getBestIndividual() {
        assertNoPostponedFitnessEval();

        Double bestFitness = null;
        I bestIndividual = null;
        for (I individual : individuals) {
            double currentFitness = fitnessMapping.get(individual);
            if (bestFitness == null || currentFitness > bestFitness) {
                bestIndividual = individual;
                bestFitness = currentFitness;
            }
        }
        return new IndividualFitness<>(bestIndividual, bestFitness);
    }

    @Override
    public List<IndividualFitness<I>> getIndividualFitnesses() {
        assertNoPostponedFitnessEval();

        List<IndividualFitness<I>> individualFitnesses = new ArrayList<>();
        for (Map.Entry<I, Double> fitnessEntry : fitnessMapping.entrySet()) {
            individualFitnesses.add(new IndividualFitness<>(fitnessEntry.getKey(), fitnessEntry.getValue()));
        }
        return individualFitnesses;
    }

    @Override
    public Iterator<IndividualFitness<I>> iterator() {
        return getIndividualFitnesses().iterator();
    }

    @Override
    public I getRandomIndividual(Random random) {
        return individuals.get(random.nextInt(individuals.size()));
    }
    
    @Override
    public PopulationSampler<I> createSampler(Selector<I> selector) {
        return new StandardPopulationSampler<>(this, selector);
    }
}
