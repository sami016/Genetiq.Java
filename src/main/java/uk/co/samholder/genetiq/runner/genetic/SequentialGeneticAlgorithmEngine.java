/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.runner.genetic;

import java.util.List;
import java.util.function.Supplier;
import uk.co.samholder.genetiq.data.ResultState;
import uk.co.samholder.genetiq.fitness.FitnessFunction;
import uk.co.samholder.genetiq.interactor.Interactor;
import uk.co.samholder.genetiq.population.ListPopulation;
import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.population.PopulationModel;
import uk.co.samholder.genetiq.population.Populator;
import uk.co.samholder.genetiq.round.RoundStrategy;
import uk.co.samholder.genetiq.selection.Selector;
import uk.co.samholder.genetiq.termination.TerminationCondition;
import uk.co.samholder.genetiq.variation.StandardVariationEngine;
import uk.co.samholder.genetiq.variation.VariationEngine;

/**
 * Simple sequential genetic algorithm engine implementation. 
 *
 * @author Sam Holder
 * @param <I> individual type
 */
public class SequentialGeneticAlgorithmEngine<I> implements GeneticAlgorithmEngine<I> {
    
    /**
     * Performs the round run using the given round strategy for each population
     * @param populationModel population model
     * @param roundStrategy round strategy
     * @param variationEngine variation engine
     */
    protected void PerformRound(PopulationModel<I> populationModel, RoundStrategy<I> roundStrategy, VariationEngine<I> variationEngine, Selector<I> selector) {
        // Within each of the model's populations, perform the round.
        for (Population<I> population : populationModel) {
            roundStrategy.performRound(population, variationEngine, selector);
        }
    }
    
    @Override
    public ResultState executePipeline(GeneticAlgorithmConfiguration<I> pipeline) {
        RoundStrategy<I> roundStrategy = pipeline.roundStrategy();
        PopulationModel<I> populationModel = pipeline.populationModel();
        VariationEngine<I> variationEngine = new StandardVariationEngine<>(pipeline.variationPipeline());
        TerminationCondition<I> terminationCondition = pipeline.terminationCondition();
        Populator<I> populator = pipeline.populator();
        List<Interactor> interactors = pipeline.interactors();
        FitnessFunction<I> fitnessFunction = pipeline.fitnessFunction();
        Selector<I> selector = pipeline.selector();
        int inidividualsPerPopulation = pipeline.populationSize();
        // Create the run data.
        ResultState data = new ResultState();
        data.setPeriod(roundStrategy.getPeriodType());
        
        // Generate the initial population.
        Supplier<Population<I>> populationSource = () -> new ListPopulation<>(inidividualsPerPopulation, fitnessFunction, 0);
        populationModel.Initialise(
            populationSource,
            populator
        );
        populationModel.writeData(data);

        int iteration = 0;
        // Run the loop until termination condition is met.
        while (!populationModel.isConditionMet(terminationCondition, iteration)) {
            populationModel.preRound(data);
            PerformRound(populationModel, roundStrategy, variationEngine, selector);
            populationModel.postRound(data);
            populationModel.writeData(data);
            // Set and increase the iteration.
            iteration++;
            data.setRound(iteration);
            // Prompt external interactions.
            doInteractions(data, interactors);
        }
        return data;
    }
    
    /**
     * Run all interactors.
     * @param runData run data
     * @param interactors interactions list
     */
    private void doInteractions(ResultState runData, List<Interactor> interactors) {
        for (Interactor interactor : interactors) {
            interactor.interact(runData);
        }
    }

}
