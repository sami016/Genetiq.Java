/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.population.multideme;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import uk.co.samholder.genetiq.control.TerminationCondition;
import uk.co.samholder.genetiq.data.RunData;
import uk.co.samholder.genetiq.fitness.FitnessFunction;
import uk.co.samholder.genetiq.individuals.IndividualFitness;
import uk.co.samholder.genetiq.migration.MigrationModel;
import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.population.PopulationModel;
import uk.co.samholder.genetiq.round.RoundStrategy;

/**
 * A Multi-deme population model.
 *
 * Several populations are evolved in isolation from each other. A migration
 * strategy is used to move individuals between these demes.
 *
 * Data Keys:
 *
 * KEY_POPULATIONS :=
 *
 * KEY_OPTIMUM :=
 *
 * KEY_OPTIMA := set
 *
 * @author Sam Holder
 */
public class MultiDemePopulationModel<I extends Object> implements PopulationModel<I> {

    public static final String KEY_POPULATIONS = "MultiDemePopulationModel_populations";
    public static final String KEY_OPTIMUM = "MultiDemePopulationModel_optimum";
    public static final String KEY_OPTIMA = "MultiDemePopulationModel_optima";

    private final MigrationModel<I> migrationModel;
    private final List<Population<I>> populationPool = new ArrayList<>();
    private final int numDemes;
    private int populationUnitSize;

    public MultiDemePopulationModel(MigrationModel<I> migrationModel, FitnessFunction<I> fitnessFunction, int populationSize, int numDemes) {
        this.migrationModel = migrationModel;
        this.populationUnitSize = populationSize;
        this.numDemes = numDemes;
        for (int i = 0; i < numDemes; i++) {
            populationPool.add(new Population<>(fitnessFunction, populationSize));
        }
    }

    @Override
    public int getPopulationUnitSize() {
        return populationUnitSize;
    }

    @Override
    public void doPerformRound(RoundStrategy roundStrategy, RunData runData) {
        for (Population<I> population : populationPool) {
            roundStrategy.performRound(population);
        }
        migrationModel.performMigration(this);
    }

    @Override
    public boolean isConditionMet(TerminationCondition<I> termintionCondition, int iteration) {
        boolean b = false;
        for (Population<I> population : populationPool) {
            b = b || termintionCondition.conditionMet(population, iteration);
        }
        return b;
    }

    @Override
    public Iterator<Population<I>> iterator() {
        return populationPool.iterator();
    }

    @Override
    public void writeData(RunData runData) {
        runData.set(KEY_POPULATIONS, populationPool);
        // Get the best individuals.
        List<IndividualFitness<I>> bestIndividuals = new ArrayList<>();
        IndividualFitness<I> bestIndividual = null;
        double bestScore = Double.MIN_VALUE;
        for (Population<I> population : populationPool) {
            IndividualFitness<I> individualFitness = population.getBestIndividual();
            bestIndividuals.add(individualFitness);
            if (individualFitness.getFitness() > bestScore) {
                bestScore = individualFitness.getFitness();
                bestIndividual = individualFitness;
            }
        }
        runData.set(KEY_OPTIMA, bestIndividuals);
        runData.set(KEY_OPTIMUM, bestIndividual);
    }

}
