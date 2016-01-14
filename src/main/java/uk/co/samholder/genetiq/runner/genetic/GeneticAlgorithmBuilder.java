/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.runner.genetic;

import java.util.ArrayList;
import java.util.List;
import uk.co.samholder.genetiq.control.TerminationCondition;
import uk.co.samholder.genetiq.fitness.FitnessFunction;
import uk.co.samholder.genetiq.populator.Populator;
import uk.co.samholder.genetiq.output.Interactor;
import uk.co.samholder.genetiq.population.PopulationModel;
import uk.co.samholder.genetiq.population.single.SinglePopulationModel;
import uk.co.samholder.genetiq.round.RoundStrategy;

/**
 *
 * @author sam
 */
public class GeneticAlgorithmBuilder<I extends Object> {

    private RoundStrategy<I> roundStrategy;
    private PopulationModel<I> populationModel;
    private Populator<I> populator;
    private TerminationCondition<I> terminationCondition;
    private final List<Interactor> interactors = new ArrayList<>();

    public GeneticAlgorithmBuilder<I> setPopulationModel(PopulationModel<I> populationModel) {
        this.populationModel = populationModel;
        return this;
    }

    public void useSinglePopulationModel(FitnessFunction<I> fitnessFunction, int populationSize) {
        setPopulationModel(new SinglePopulationModel<I>(fitnessFunction, populationSize));
    }

    public GeneticAlgorithmBuilder<I> setPopulator(Populator<I> populator) {
        this.populator = populator;
        return this;
    }

    public GeneticAlgorithmBuilder<I> setRoundStrategy(RoundStrategy<I> roundStrategy) {
        this.roundStrategy = roundStrategy;
        return this;
    }

    public GeneticAlgorithmBuilder<I> setTerminationCondition(TerminationCondition<I> terminationCondition) {
        this.terminationCondition = terminationCondition;
        return this;
    }

    public GeneticAlgorithmBuilder<I> addInterator(Interactor interactor) {
        this.interactors.add(interactor);
        return this;
    }

    public GeneticAlgorithm<I> build() {
        if (roundStrategy == null) {
            throw new IllegalStateException("No round strategy set!");
        }
        if (populationModel == null) {
            throw new IllegalStateException("No population model set!");
        }
        if (populator == null) {
            throw new IllegalStateException("No population set!");
        }
        if (terminationCondition == null) {
            throw new IllegalStateException("No termination condition set!");
        }

        return new GeneticAlgorithm<>(roundStrategy, populationModel, populator, terminationCondition, interactors);
    }

}
