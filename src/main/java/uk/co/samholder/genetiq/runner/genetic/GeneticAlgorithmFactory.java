/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.runner.genetic;

import java.util.List;
import uk.co.samholder.genetiq.control.TerminationCondition;
import uk.co.samholder.genetiq.interactor.Interactor;
import uk.co.samholder.genetiq.population.PopulationModel;
import uk.co.samholder.genetiq.populator.Populator;
import uk.co.samholder.genetiq.round.RoundStrategy;

/**
 * An abstract class to be used for setting up genetic algorithms in a clear and
 * readable way.
 *
 * @author Samp Holder
 */
public abstract class GeneticAlgorithmFactory<I> {

    /**
     * Implements the round strategy, which defines how rounds are handled.
     * Usually set to a generational or steady state strategy.
     *
     * @return round strategy
     */
    protected abstract RoundStrategy roundStrategy();

    /**
     * Implements the population model, which defines how the population is
     * structured. The simplest example of this is the single population model.
     *
     * @return population model.
     */
    protected abstract PopulationModel<I> populationModel();

    /**
     * Implements the populator, an object to seed individuals at the start of
     * the algorithm. This typically generates random individuals.
     *
     * @return populator
     */
    protected abstract Populator<I> populator();

    /**
     * Implements the termination condition. Once this condition is satisfied
     * the algorithm will terminate.
     *
     * @return termination condition
     */
    protected abstract TerminationCondition terminationCondition();

    /**
     * Implements the interactors list, a list of objects used view the
     * algorithm state whilst running. This can be used to extract data for
     * graph plots, logging, or viewing the current best individual at a given
     * time.
     *
     *
     * @return interactor list
     */
    protected abstract List<Interactor> interactors();

    /**
     * Creates the genetic algorithm runner using the configuration specified.
     *
     * @return
     */
    public GeneticAlgorithm<I> create() {
        return new GeneticAlgorithm<>(roundStrategy(), populationModel(), populator(), terminationCondition(), interactors());
    }

}
