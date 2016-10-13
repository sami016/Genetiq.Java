/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import uk.co.samholder.genetiq.control.IterationCountTerminationCondition;
import uk.co.samholder.genetiq.control.TerminationCondition;
import uk.co.samholder.genetiq.data.RunData;
import uk.co.samholder.genetiq.fitness.FitnessFunction;
import uk.co.samholder.genetiq.individuals.IndividualFitness;
import uk.co.samholder.genetiq.interactor.Interactor;
import uk.co.samholder.genetiq.interactor.MemoriseBestInteractor;
import uk.co.samholder.genetiq.interactor.RoundBestInteractor;
import uk.co.samholder.genetiq.mutator.Mutator;
import uk.co.samholder.genetiq.mutator.pool.StochasticAllMutatorPool;
import uk.co.samholder.genetiq.population.PopulationModel;
import uk.co.samholder.genetiq.population.single.SinglePopulationModel;
import uk.co.samholder.genetiq.populator.Populator;
import uk.co.samholder.genetiq.representation.string.InsertionStringMutator;
import uk.co.samholder.genetiq.representation.string.PerLociStringMutator;
import uk.co.samholder.genetiq.representation.string.RemovalStringMutator;
import uk.co.samholder.genetiq.representation.string.StringPopulator;
import uk.co.samholder.genetiq.round.GenerationalRoundStrategy;
import uk.co.samholder.genetiq.round.RoundStrategy;
import uk.co.samholder.genetiq.runner.genetic.GeneticAlgorithmFactory;
import uk.co.samholder.genetiq.selection.FitnessProportionateSelector;

/**
 * A simple example of evolving a string of 'a's. Uses a simple string
 * populator, and loci mutator.
 *
 * @author Sam Holder
 */
public class StringRuleExample extends GeneticAlgorithmFactory<String> {

    private final int stringLength = 40;
    private final int populationSize = 8;
    private final Random random = new Random();

    private static final FitnessFunction<String> matchFitness = (individual, population) -> {
        double score = 0;

        score += individual.chars().filter(value -> value == (int) 'a').count();
        score -= 0.1 * Math.pow(individual.length(), 2);

        return score;
    };

    @Override
    protected RoundStrategy<String> roundStrategy() {
        Mutator<String> mutator = new StochasticAllMutatorPool(random)
                .use(new PerLociStringMutator(1.0 / (double) stringLength, random), 1.0)
                .use(new InsertionStringMutator(0.1, true, random), 1.0)
                .use(new RemovalStringMutator(0.1, true, random), 1.0);

        return new GenerationalRoundStrategy(
                new FitnessProportionateSelector<String>(random, true, (a) -> Math.pow(a, 2)),
                mutator,
                null// new StringUniformCrossover(random)
        );
    }

    @Override
    protected PopulationModel<String> populationModel() {
        return new SinglePopulationModel<>(matchFitness, populationSize);
    }

    @Override
    protected Populator<String> populator() {
        return new StringPopulator(random, 0);
    }

    @Override
    protected TerminationCondition terminationCondition() {
        return new IterationCountTerminationCondition(10000000);
    }

    @Override
    protected List<Interactor> interactors() {
        List<Interactor> interactors = new ArrayList<>();
        interactors.add(new RoundBestInteractor(SinglePopulationModel.KEY_OPTIMUM, 100));
        interactors.add(new MemoriseBestInteractor(SinglePopulationModel.KEY_OPTIMUM));
        return interactors;
    }

    public static void main(String[] args) {
//        System.out.println(matchFitness.calculateFitness("aaaaaa", null));
        RunData data = new StringRuleExample().create().run();
        IndividualFitness<String> ind = data.get(MemoriseBestInteractor.BEST_INDIVIDUAL_FITNESS);
        System.out.println("best: " + ind.getFitness() + " individual: " + ind.getIndividual());
    }
}
