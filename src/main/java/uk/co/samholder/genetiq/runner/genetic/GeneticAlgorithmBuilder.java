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
import uk.co.samholder.genetiq.individuals.Populator;
import uk.co.samholder.genetiq.output.Interactor;
import uk.co.samholder.genetiq.population.PopulationModel;
import uk.co.samholder.genetiq.round.RoundStrategy;

/**
 *
 * @author sam
 */
public class GeneticAlgorithmBuilder<I extends Object> {

    private RoundStrategy<I> roundStrategy;
    private FitnessFunction<I> fitnessFunction;
    private PopulationModel<I> populationModel;
    private Populator<I> populator;
    private TerminationCondition<I> terminationCondition;
    private Integer numIndividuals;
    private final List<Interactor> interactors = new ArrayList<>();

    public GeneticAlgorithmBuilder<I> setFitnessFunction(FitnessFunction<I> fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
        return this;
    }

    public GeneticAlgorithmBuilder<I> setNumIndividuals(Integer numIndividuals) {
        this.numIndividuals = numIndividuals;
        return this;
    }

    public GeneticAlgorithmBuilder<I> setPopulationModel(PopulationModel<I> populationModel) {
        this.populationModel = populationModel;
        return this;
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
        if (fitnessFunction == null) {
            throw new IllegalStateException("No fitness function set!");
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
        if (numIndividuals == null) {
            throw new IllegalStateException("Number of individuals per population not set!");
        }

        return new GeneticAlgorithm<>(roundStrategy, fitnessFunction, populationModel, populator, terminationCondition, numIndividuals, interactors);
    }

}
