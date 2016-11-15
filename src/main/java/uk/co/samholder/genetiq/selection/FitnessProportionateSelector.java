/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.selection;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import uk.co.samholder.genetiq.population.IndividualFitness;
import uk.co.samholder.genetiq.population.Population;

/**
 * Fitness Proportionate Selector
 *
 * Individuals are selected stochastically, each with probability scaling
 * linearly with their fitness. i.e. Probability of selection is proportional to
 * individual fitness.
 *
 * @author Sam Holder
 * @param <I> Individual Object.
 */
public class FitnessProportionateSelector<I> extends ScaledSelectorBase<I> implements Selector<I> {

    protected final boolean useNormalisation;
    protected final Function<Double, Double> scalingOperation;

    /**
     * @param random pseudo-random number generator
     * @param useNormalisation use normalisation, individuals scored
     * proportional to relative offset with worst individual.
     */
    public FitnessProportionateSelector(Random random, boolean useNormalisation) {
        this(random, useNormalisation, null);
    }

    /**
     * @param random pseudo-random number generator
     * @param useNormalisation use normalisation, individuals scored
     * proportional to relative offset with worst individual.
     * @param scalingOperation Scaling operation to apply to each fitness value.
     * Taken after normalisation if enabled.
     */
    public FitnessProportionateSelector(Random random, boolean useNormalisation, Function<Double, Double> scalingOperation) {
        super(random);
        this.useNormalisation = useNormalisation;
        this.scalingOperation = scalingOperation;
    }

    /**
     * Applies the scaling operation to a fitness value. Will return the input
     * if operation is null.
     *
     * @param fitness input fitness.
     * @return output fitness.
     */
    protected double applyScaling(double fitness) {
        if (scalingOperation == null) {
            return fitness;
        }
        return scalingOperation.apply(fitness);
    }

    /**
     * Adjust the fitness to become a relative offset from the minimum
     * population fitness.
     *
     * @param fitness input fitness
     * @param minFitness minimum population fitness
     * @return output fitness
     */
    protected double adjustFitness(double fitness, double minFitness) {
        if (useNormalisation) {
            return applyScaling(fitness - minFitness);
        } else {
            return applyScaling(fitness);
        }
    }

    /**
     * Scales the fitness of the population.
     *
     * @param minFitness The minimum population fitness.
     * @param populationIndividualFitness the population's individual fitness
     * values.
     * @return list of scaled individual fitness values.
     */
    List<IndividualFitness<I>> scaleFitness(double minFitness, List<IndividualFitness<I>> populationIndividualFitness) {
        return populationIndividualFitness.stream()
                .map(x -> new IndividualFitness<>(x.getIndividual(), adjustFitness(x.getFitness(), minFitness)))
                .collect(Collectors.toList());
    }

    @Override
    public List<I> select(Population<I> population, int numToSelect) {
        // Minimum fitness required for normalisation.
        double minFitness = population.getWorstIndividual().getFitness();
        // Scale the fitness by performing normalization and a scaling operation as configured.
        List<IndividualFitness<I>> scaledFitness = scaleFitness(minFitness, population.getIndividualFitnesses());
        // Calculate the fitness total of the population.
        double total = scaledFitness.stream()
                .map(x -> x.getFitness())
                .reduce(0.0, (x, y) -> x + y);
        // Select n individuals.
        List<I> list = new LinkedList<>();
        for (int i = 0; i < numToSelect; i++) {
            list.add(selectOne(total, scaledFitness));
        }
        return list;
    }

}
