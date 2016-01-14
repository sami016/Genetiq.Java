/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.runner.genetic;

import java.util.List;
import uk.co.samholder.genetiq.control.TerminationCondition;
import uk.co.samholder.genetiq.data.RunData;
import uk.co.samholder.genetiq.fitness.FitnessFunction;
import uk.co.samholder.genetiq.individuals.Populator;
import uk.co.samholder.genetiq.output.Interactor;
import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.population.PopulationModel;
import uk.co.samholder.genetiq.round.RoundStrategy;

/**
 * A genetic algorithm wrapper. Not thread safe. Should only be run once at a
 * time.
 *
 * @author sam
 * @param <I>
 */
public class GeneticAlgorithm<I extends Object> {

    private GeneticAlgorithmOutputs<I> outputs = new GeneticAlgorithmOutputs<>();

    private final FitnessFunction<I> fitnessFunction;
    private final RoundStrategy roundStrategy;
    private final TerminationCondition termination;
    private final PopulationModel<I> populationModel;
    private final Populator<I> populator;
    private final int numIndividuals;

    private final List<Interactor> interactors;

    public GeneticAlgorithm(RoundStrategy roundStrategy, FitnessFunction<I> fitnessFunction, PopulationModel<I> populationModel, Populator<I> populator, TerminationCondition stopCondition, int numIndividuals, List<Interactor> interactors) {
        this.roundStrategy = roundStrategy;
        this.fitnessFunction = fitnessFunction;
        this.populationModel = populationModel;
        this.populator = populator;
        this.termination = stopCondition;
        this.numIndividuals = numIndividuals;

        this.interactors = interactors;
    }

    private void generatePopulation() {
        for (Population<I> pop : populationModel) {
            for (int i = 0; i < numIndividuals; i++) {
                pop.insertIndividual(populator.getIndividual());
            }
        }
    }

    private void doInteractions(RunData runData) {
        for (Interactor interactor : interactors) {
            interactor.interact(runData);
        }
    }

    public RunData run() {
        // Create the run data.
        RunData data = new RunData();
        data.set(outputs.getPeriodType(), roundStrategy.getPeriodType());

        // Generate the initial population.
        generatePopulation();
        populationModel.writeData(data);

        int iteration = 0;
        // Run the loop until termination condition is met.
        while (!populationModel.isConditionMet(termination, iteration)) {
            // For the population, perform the round.
            populationModel.doPerformRound(roundStrategy, data);
            populationModel.writeData(data);
            // Set and increase the iteration.
            iteration++;
            data.set(outputs.getPeriodNumber(), iteration);
            // Prompt external interactions.
            doInteractions(data);
        }
        return data;
    }

}
