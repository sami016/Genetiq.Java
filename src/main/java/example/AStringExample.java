/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import uk.co.samholder.genetiq.control.FitnessThresholdTerminationCondition;
import uk.co.samholder.genetiq.control.TerminationCondition;
import uk.co.samholder.genetiq.data.RunData;
import uk.co.samholder.genetiq.fitness.FitnessFunction;
import uk.co.samholder.genetiq.interactor.Interactor;
import uk.co.samholder.genetiq.interactor.RoundBestInteractor;
import uk.co.samholder.genetiq.population.PopulationModel;
import uk.co.samholder.genetiq.population.single.SinglePopulationModel;
import uk.co.samholder.genetiq.populator.Populator;
import uk.co.samholder.genetiq.representation.string.PerLociStringMutator;
import uk.co.samholder.genetiq.representation.string.StringPopulator;
import uk.co.samholder.genetiq.representation.string.StringUniformCrossover;
import uk.co.samholder.genetiq.round.GenerationalRoundStrategy;
import uk.co.samholder.genetiq.round.RoundStrategy;
import uk.co.samholder.genetiq.runner.genetic.GeneticAlgorithmFactory;
import uk.co.samholder.genetiq.selection.TournamentSelector;

/**
 * A simple example of evolving a string of 'a's. Uses a simple string
 * populator, and loci mutator.
 *
 * @author Sam Holder
 */
public class AStringExample extends GeneticAlgorithmFactory<String> {

    private final int stringLength = 20;
    private final int populationSize = 8;
    private final Random random = new Random();

    private final FitnessFunction<String> matchFitness = (individual, population) -> {
        return individual.chars().filter(value -> value == (int) 'a').count();
    };

    @Override
    protected RoundStrategy<String> roundStrategy() {
        return new GenerationalRoundStrategy(
                new TournamentSelector<String>(2),
                new PerLociStringMutator(1.0 / (double) stringLength, random),
                new StringUniformCrossover(random)
        );
    }

    @Override
    protected PopulationModel<String> populationModel() {
        return new SinglePopulationModel<>(matchFitness, populationSize);
    }

    @Override
    protected Populator<String> populator() {
        return new StringPopulator(random, stringLength);
    }

    @Override
    protected TerminationCondition terminationCondition() {
        return new FitnessThresholdTerminationCondition(stringLength);
    }

    @Override
    protected List<Interactor> interactors() {
        List<Interactor> interactors = new ArrayList<>();
        interactors.add(new RoundBestInteractor(SinglePopulationModel.KEY_OPTIMUM, 100));
        return interactors;
    }

    public static void main(String[] args) {
        RunData data = new AStringExample().create().run();
    }
}
