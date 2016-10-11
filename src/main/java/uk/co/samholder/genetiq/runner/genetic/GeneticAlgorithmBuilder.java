/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.runner.genetic;

import uk.co.samholder.genetiq.control.TerminationConditionBuilder;
import uk.co.samholder.genetiq.interactor.InteractorsBuilder;
import uk.co.samholder.genetiq.population.PopulationModelBuilder;
import uk.co.samholder.genetiq.populator.PopulatorBuilder;
import uk.co.samholder.genetiq.round.RoundStrategyBuilder;

/**
 * Genetic algorithm builder context.
 *
 * @author sam
 */
public class GeneticAlgorithmBuilder<I extends Object> {

    private final PopulationModelBuilder<I> populationModel = new PopulationModelBuilder<>();
    private final PopulatorBuilder<I> populator = new PopulatorBuilder<>();
    private final RoundStrategyBuilder<I> roundStrategy = new RoundStrategyBuilder<>();
    private final TerminationConditionBuilder<I> terminationCondition = new TerminationConditionBuilder<>();
    private final InteractorsBuilder<I> interactors = new InteractorsBuilder<>();

    /**
     * Gets the populator builder context.
     *
     * @return populator builder context.
     */
    public PopulationModelBuilder<I> populationModel() {
        return populationModel;
    }

    /**
     * Gets the population model builder context.
     *
     * @return population model builder context.
     */
    public PopulatorBuilder<I> populator() {
        return populator;
    }

    /**
     * Gets the round strategy builder context.
     *
     * @return round strategy builder context.
     */
    public RoundStrategyBuilder<I> roundStrategy() {
        return roundStrategy;
    }

    /**
     * Gets the termination condition builder context.
     *
     * @return termination condition builder context.
     */
    public TerminationConditionBuilder<I> terminationCondition() {
        return terminationCondition;
    }

    /**
     * Gets the interactors builder context.
     *
     * @return interactors builder context.
     */
    public InteractorsBuilder<I> interactors() {
        return interactors;
    }

    /**
     * Builds and validates the genetic algorithm context.
     *
     * @return the genetic algorithm context.
     */
    public GeneticAlgorithm<I> build() {
        return new GeneticAlgorithm<>(
                roundStrategy.build(),
                populationModel.build(),
                populator.build(),
                terminationCondition.build(),
                interactors.build()
        );
    }

}
