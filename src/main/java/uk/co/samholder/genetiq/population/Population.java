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

/**
 * Encapsulates a population of individuals.
 *
 * @author Sam Holder
 */
public class Population<I extends Object> implements Iterable<IndividualFitness<I>> {

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

    public Population(FitnessFunction<I> fitnessFunction, int populationIndex) {
        this.fitnessFunction = fitnessFunction;
        this.populationIndex = populationIndex;
        this.individuals = new ArrayList<>();
        this.fitnessMapping = new HashMap<>();
        this.uncalculated = new HashSet<>();
        initialPhase = false;
    }

    /**
     * Creates an empty population.
     *
     * @param fitnessFunction fitness function
     */
    public Population(FitnessFunction<I> fitnessFunction) {
        this(fitnessFunction, 0);
    }

    /**
     * Gets the number of individuals within the population.
     *
     * @return population size
     */
    public int size() {
        return individuals.size();
    }

    /**
     * Gets the population index. Used by algorithms with multiple populations.s
     *
     * @return population index.
     */
    public int getPopulationIndex() {
        return populationIndex;
    }

    /**
     * Clears the population, deleting all existing individuals.
     */
    public void clear() {
        individuals.clear();
        fitnessMapping.clear();
    }

    /**
     * Removes a specific individual from the population.
     *
     * @param individual individual to remove
     */
    public void removeIndividual(I individual) {
        individuals.remove(individual);
        // Remove momoized value if no duplicates remain in population.
        if (!individuals.contains(individual)) {
            fitnessMapping.remove(individual);
        }
    }

    /**
     * Inserts an individual into the population.
     *
     * @param individual individual to insert
     */
    public void insertIndividual(I individual) {
        individuals.add(individual);
        fitnessMapping.put(individual, fitnessFunction.calculateFitness(individual, this));
    }

    /**
     * Inserts an individual into the population, with the option to postpone
     * fitness evaluation. This may be useful in cases where fitness is
     * calculated with respect to other individuals in the population.
     *
     * @param individual individual to insert
     * @param postponeFitnessCalculation flag to postpone fitness evaluation
     */
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

    /**
     * Gets whether population in initial phase - i.e. fitness values are yet to
     * be calculated.
     *
     * @return is initial phase
     */
    public boolean isInitialPhase() {
        return initialPhase;
    }

    /**
     * Inserts a list of individuals into the population, with the option to
     * postpone fitness evaluation. This may be useful in cases where fitness is
     * calculated with respect to other individuals in the population.
     *
     * @param individuals individuals to insert
     * @param postponeFitnessCalculation flag to postpone fitness evaluation
     */
    public void insertIndividuals(List<I> individuals, boolean postponeFitnessCalculation) {
        for (I individual : individuals) {
            insertIndividual(individual, postponeFitnessCalculation);
        }
    }

    /**
     * Replaces an individual within the population.
     *
     * @param original individual to remove
     * @param novel individual to insert
     */
    public void replaceIndividual(I original, I novel) {
        removeIndividual(original);
        insertIndividual(novel);
    }

    /**
     * Evaluates the fitness for all unprocessed individuals within the
     * population.
     */
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

    /**
     * Gets an individual at a specific index within the list.
     *
     * @param id index in list
     * @return individual at index
     */
    public IndividualFitness<I> getIndividualAtIndex(int id) {
        assertNoPostponedFitnessEval();

        I individual = individuals.get(id);
        return new IndividualFitness<>(individual, fitnessMapping.get(individual));
    }

    /**
     * Gets the individual fitness combination for the individual with lowest
     * fitness.
     *
     * @return individual fitness combination for worst individual
     */
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

    /**
     * Gets the individual fitness combination for the individual with highest
     * fitness.
     *
     * @return individual fitness combination for best individual
     */
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

    /**
     * Gets the list of individual fitness combinations for the population.
     *
     * @return list of individual fitness combinations
     */
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

    /**
     * Gets a random individual from the population.
     *
     * @param random random number generator
     * @return randomly selected individual
     */
    public I getRandomIndividual(Random random) {
        return individuals.get(random.nextInt(individuals.size()));
    }
}
