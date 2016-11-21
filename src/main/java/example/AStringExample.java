/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import uk.co.samholder.genetiq.data.RunData;
import uk.co.samholder.genetiq.fitness.FitnessFunction;
import uk.co.samholder.genetiq.interactor.Interactor;
import uk.co.samholder.genetiq.interactor.MemoriseBestInteractor;
import uk.co.samholder.genetiq.interactor.RoundBestInteractor;
import uk.co.samholder.genetiq.population.IndividualFitness;
import uk.co.samholder.genetiq.population.PopulationModel;
import uk.co.samholder.genetiq.population.Populator;
import uk.co.samholder.genetiq.population.SinglePopulationModel;
import uk.co.samholder.genetiq.representation.string.PerLociStringMutator;
import uk.co.samholder.genetiq.representation.string.RandomStringPopulator;
import uk.co.samholder.genetiq.representation.string.UniformStringCrossover;
import uk.co.samholder.genetiq.round.GenerationalRoundStrategy;
import uk.co.samholder.genetiq.round.RoundStrategy;
import uk.co.samholder.genetiq.runner.genetic.GeneticAlgorithmEngine;
import uk.co.samholder.genetiq.runner.genetic.GeneticAlgorithmPipeline;
import uk.co.samholder.genetiq.runner.genetic.SequentialGeneticAlgorithmEngine;
import uk.co.samholder.genetiq.selection.TournamentSelector;
import uk.co.samholder.genetiq.termination.FitnessThresholdTerminationCondition;
import uk.co.samholder.genetiq.termination.TerminationCondition;

/**
 * A simple example of evolving a string of 'a's. Uses a simple string
 * populator, and loci mutator.
 *
 * @author Sam Holder
 */
public class AStringExample extends GeneticAlgorithmPipeline<String> {

    private final int stringLength = 20;
    private final int populationSize = 400;
    private final Random random = new Random();

    private final FitnessFunction<String> matchFitness = (individual, population) -> {
        return individual.chars().filter(value -> value == (int) 'a').count();
    };

    @Override
    protected RoundStrategy<String> roundStrategy() {
        return new GenerationalRoundStrategy(
            new TournamentSelector<>(2),
            new PerLociStringMutator(1.0 / (double) stringLength, random),
            new UniformStringCrossover(random)
        );
    }

    @Override
    protected PopulationModel<String> populationModel() {
        return new SinglePopulationModel<>(matchFitness, populationSize);
    }

    @Override
    protected Populator<String> populator() {
        return new RandomStringPopulator(random, stringLength);
    }

    @Override
    protected TerminationCondition terminationCondition() {
        return new FitnessThresholdTerminationCondition(stringLength);
    }

    @Override
    protected List<Interactor> interactors() {
        List<Interactor> interactors = new ArrayList<>();
        interactors.add(new RoundBestInteractor(SinglePopulationModel.KEY_OPTIMUM, 100));
        interactors.add(new MemoriseBestInteractor(SinglePopulationModel.KEY_OPTIMUM));
        return interactors;
    }

    public static void main(String[] args) {
        GeneticAlgorithmPipeline<String> pipeline = new AStringExample();
        GeneticAlgorithmEngine<String> engine = new SequentialGeneticAlgorithmEngine<>();
        RunData data = engine.executePipeline(pipeline);
        // Get the best all time result from the algorithm.
        IndividualFitness<String> ind = data.get(MemoriseBestInteractor.BEST_INDIVIDUAL_FITNESS);
        // Check whether palidrome property holds. Print details.
        String result = ind.getIndividual();
        System.out.println("best fitness: " + ind.getFitness() + " individual: " + result + " length: " + result.length());
    }
}
