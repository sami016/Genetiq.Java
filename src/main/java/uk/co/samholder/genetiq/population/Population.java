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
import uk.co.samholder.genetiq.individuals.IndividualFitness;

/**
 *
 * @author sam
 */
public class Population<I extends Object> implements Iterable<IndividualFitness<I>> {

    private int populationIndex;
    private FitnessFunction<I> fitnessFunction;
    private final List<I> individuals;
    private final Map<I, Double> fitnessMapping;

    // State for postponed fitness evaluation.
    private final Set<I> uncalculated;
    private boolean initialPhase;

    public Population(FitnessFunction<I> fitnessFunction, int populationIndex) {
        this.fitnessFunction = fitnessFunction;
        this.populationIndex = populationIndex;
        this.individuals = new ArrayList<>();
        this.fitnessMapping = new HashMap<>();
        this.uncalculated = new HashSet<>();
        initialPhase = false;
    }

    public Population(FitnessFunction<I> fitnessFunction) {
        this(fitnessFunction, 0);
    }

    public int size() {
        return individuals.size();
    }

    public int getPopulationIndex() {
        return populationIndex;
    }

    public void clear() {
        individuals.clear();
        fitnessMapping.clear();
    }

    public void removeIndividual(I individual) {
        individuals.remove(individual);
        // Remove momoized value if no duplicates remain in population.
        if (!individuals.contains(individual)) {
            fitnessMapping.remove(individual);
        }
    }

    public void insertIndividual(I individual) {
        individuals.add(individual);
        fitnessMapping.put(individual, fitnessFunction.calculateFitness(individual, this));
    }

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

    public boolean isInitialPhase() {
        return initialPhase;
    }

    public void insertIndividuals(List<I> individuals, boolean postponeFitnessCalculation) {
        for (I individual : individuals) {
            insertIndividual(individual, postponeFitnessCalculation);
        }
    }

    public void replaceIndividual(I original, I novel) {
        removeIndividual(original);
        insertIndividual(novel);
    }

    public void evaluatePostponedFitness() {
        for (I individual : uncalculated) {
            fitnessMapping.put(individual, fitnessFunction.calculateFitness(individual, this));
        }
        initialPhase = false;
    }

    private void assertNoPostponedFitnessEval() {
        if (initialPhase) {
            throw new IllegalStateException("Population has pending fitness evaluations that must be calculated before use.");
        }
    }

    public IndividualFitness<I> getIndividualAtIndex(int id) {
        assertNoPostponedFitnessEval();

        I individual = individuals.get(id);
        return new IndividualFitness<>(individual, fitnessMapping.get(individual));
    }

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

    public I getRandomIndividual(Random random) {
        return individuals.get(random.nextInt(individuals.size()));
    }
}
