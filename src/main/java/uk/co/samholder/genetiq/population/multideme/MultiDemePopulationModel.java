/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.population.multideme;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import uk.co.samholder.genetiq.control.TerminationCondition;
import uk.co.samholder.genetiq.data.RunData;
import uk.co.samholder.genetiq.fitness.FitnessFunction;
import uk.co.samholder.genetiq.individuals.IndividualFitness;
import uk.co.samholder.genetiq.migration.MigrationModel;
import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.population.PopulationModel;
import uk.co.samholder.genetiq.round.RoundStrategy;

/**
 *
 * @author sam
 */
public class MultiDemePopulationModel<I extends Object> implements PopulationModel<I> {

    private final MigrationModel<I> migrationModel;
    private final MultiDemePopulationModelOutputs<I> outputs = new MultiDemePopulationModelOutputs<>();
    private int numDemes;
    private List<Population<I>> populationPool = new ArrayList<>();

    private final Random random;

    public MultiDemePopulationModel(MigrationModel<I> migrationModel, FitnessFunction<I> fitnessFunction, int populationSize, int numDemes, Random random) {
        this.migrationModel = migrationModel;

        this.numDemes = numDemes;
        for (int i = 0; i < numDemes; i++) {
            populationPool.add(new Population<>(fitnessFunction, populationSize));
        }
        this.random = random;
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
        runData.set(outputs.getPopulations(), populationPool);
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
        runData.set(outputs.getBestIndividuals(), bestIndividuals);
        runData.set(outputs.getBestIndividual(), bestIndividual);
    }

    public MultiDemePopulationModelOutputs<I> getOutputs() {
        return outputs;
    }

}
