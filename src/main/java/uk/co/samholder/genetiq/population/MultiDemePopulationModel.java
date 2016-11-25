/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.population;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import uk.co.samholder.genetiq.data.RunData;
import uk.co.samholder.genetiq.migration.MigrationModel;
import uk.co.samholder.genetiq.termination.TerminationCondition;

/**
 * A Multi-deme population model where several populations are evolved in
 * isolation from each other. A migration strategy is used to move individuals
 * between these demes.
 *
 * @author Sam Holder
 */
public class MultiDemePopulationModel<I> extends AbstractPopulationModel<I> {

    // The populations at the end of the last round.
    public static final String KEY_POPULATIONS = "MultiDemePopulationModel_populations";
    // The optimum individual fitness combination of any population at the end of the last round.
    public static final String KEY_OPTIMUM = "MultiDemePopulationModel_optimum";
    // The optimum individual fitness combination for each population at the end of the last round.
    public static final String KEY_OPTIMA = "MultiDemePopulationModel_optima";

    private final MigrationModel<I> migrationModel;
    private final List<Population<I>> populationPool = new ArrayList<>();
    private final int numDemes;
    
    /**
     *
     * @param migrationModel migration model
     * @param fitnessFunction fitness function
     * @param selector primary selector
     * @param populationSize population size
     * @param numDemes number of demes
     * @param populator populator
     */
    public MultiDemePopulationModel(MigrationModel<I> migrationModel, int numDemes) {
        this.migrationModel = migrationModel;
        this.numDemes = numDemes;
    }

    @Override
    public void Initialise(Supplier<Population<I>> populationSupplier, Populator<I> populator) {
        for (int i = 0; i < numDemes; i++) {
            populationPool.add(populationSupplier.get());
        }
        SeedPopulations(populator);
    }

    @Override
    public void preRound(RunData runData) {
        // No behaviour.
    }

    @Override
    public void postRound(RunData runData) {
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
