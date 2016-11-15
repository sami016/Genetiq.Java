/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.selection;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import uk.co.samholder.genetiq.population.IndividualFitness;
import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.util.ListUtil;

/**
 * A selector where probability of an individual being selected is proportional
 * to its transformed fitness. A Boltzman scaling operation is applied to each
 * fitness.
 *
 * @author Sam Holder
 */
public class BoltzmanSelector<I> extends ScaledSelectorBase<I> implements Selector<I> {

    private final double sFactor;
    private final boolean useNormalisation;

    public BoltzmanSelector(Random random, boolean useNormalization, double sFactor) {
        super(random);
        this.useNormalisation = useNormalization;
        this.sFactor = sFactor;
    }

    /**
     * Scales a fitness value.
     *
     * @param fitness original fitness
     * @param standardDeviation population fitness standard deviation
     * @return scaled fitness
     */
    protected double applyScaling(double fitness, double standardDeviation) {
        return Math.exp(fitness * sFactor / standardDeviation);
    }

    /**
     * Adjusts the fitness, performing normalisation if required, before scaling
     * the fitness.
     *
     * @param fitness fitness value
     * @param minFitness the minimum population fitness
     * @param standardDeviation the population's fitness standard deviation
     * @return adjusted fitness.
     */
    protected double adjustFitness(double fitness, double minFitness, double standardDeviation) {
        if (useNormalisation) {
            return applyScaling(fitness - minFitness, standardDeviation);
        } else {
            return applyScaling(fitness, standardDeviation);
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
    List<IndividualFitness<I>> scaleFitness(double minFitness, double standardDeviation, List<IndividualFitness<I>> populationIndividualFitness) {
        return populationIndividualFitness.stream()
                .map(x -> new IndividualFitness<>(x.getIndividual(), adjustFitness(x.getFitness(), minFitness, standardDeviation)))
                .collect(Collectors.toList());
    }

    @Override
    public List<I> select(Population<I> population, int numToSelect) {

        // Minimum fitness required for normalisation.
        double minFitness = population.getWorstIndividual().getFitness();
        //
        List<IndividualFitness<I>> originalFitness = population.getIndividualFitnesses();
        double standardDeviation = ListUtil.getStandardDeviation(
                originalFitness.stream().map(x -> x.getFitness()).collect(Collectors.toList())
        );
        // Scale the fitness by performing normalization and a scaling operation as configured.
        List<IndividualFitness<I>> scaledFitness = scaleFitness(minFitness, standardDeviation, originalFitness);
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
