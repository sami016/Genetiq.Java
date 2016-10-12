/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.population.single;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import uk.co.samholder.genetiq.control.TerminationCondition;
import uk.co.samholder.genetiq.data.RunData;
import uk.co.samholder.genetiq.fitness.FitnessFunction;
import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.population.PopulationModel;
import uk.co.samholder.genetiq.round.RoundStrategy;

/**
 *
 * @author sam
 */
public class SinglePopulationModel<I extends Object> implements PopulationModel<I> {

    public static final String KEY_POPULATION = "SinglePopulationModel_population";
    public static final String KEY_OPTIMUM = "SinglePopulationModel_optimum";

    private final Population<I> population;
    private final int populationSize;

    public SinglePopulationModel(FitnessFunction<I> fitnessFunction, int popSize) {
        this.population = new Population(fitnessFunction, popSize);
        this.populationSize = popSize;
    }

    @Override
    public int getPopulationUnitSize() {
        return populationSize;
    }

    @Override
    public Iterator<Population<I>> iterator() {
        final List<Population<I>> list = new ArrayList<>(1);
        list.add(population);
        return list.iterator();
    }

    @Override
    public void writeData(RunData runData) {
        runData.set(KEY_POPULATION, population);
    }

    @Override
    public boolean isConditionMet(TerminationCondition<I> termintionCondition, int iteration) {
        return termintionCondition.conditionMet(population, iteration);
    }

    @Override
    public void doPerformRound(RoundStrategy roundStrategy, RunData runData) {
        roundStrategy.performRound(population);

        // Set the best individual.
        runData.set(KEY_OPTIMUM, population.getBestIndividual());
    }

}
