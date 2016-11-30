/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import uk.co.samholder.genetiq.data.ResultState;
import uk.co.samholder.genetiq.fitness.FitnessFunction;
import uk.co.samholder.genetiq.interactor.Interactor;
import uk.co.samholder.genetiq.interactor.RoundBestInteractor;
import uk.co.samholder.genetiq.population.IndividualFitness;
import uk.co.samholder.genetiq.population.PopulationModel;
import uk.co.samholder.genetiq.population.Populator;
import uk.co.samholder.genetiq.population.SinglePopulationModel;
import uk.co.samholder.genetiq.representation.string.InsertionStringMutator;
import uk.co.samholder.genetiq.representation.string.PerLociStringMutator;
import uk.co.samholder.genetiq.representation.string.RandomStringPopulator;
import uk.co.samholder.genetiq.representation.string.RemovalStringMutator;
import uk.co.samholder.genetiq.round.GenerationalRoundStrategy;
import uk.co.samholder.genetiq.round.RoundStrategy;
import uk.co.samholder.genetiq.runner.genetic.GeneticAlgorithmConfiguration;
import uk.co.samholder.genetiq.runner.genetic.GeneticAlgorithmEngine;
import uk.co.samholder.genetiq.runner.genetic.SequentialGeneticAlgorithmEngine;
import uk.co.samholder.genetiq.selection.FitnessProportionateSelector;
import uk.co.samholder.genetiq.selection.Selector;
import uk.co.samholder.genetiq.termination.IterationCountTerminationCondition;
import uk.co.samholder.genetiq.termination.TerminationCondition;
import uk.co.samholder.genetiq.variation.VariationPipeline;

/**
 * A simple example of evolving a palindrome string. Uses a simple string populator, and loci mutator.
 *
 * @author Sam Holder
 */
public class PalindromeExample extends GeneticAlgorithmConfiguration<String> {

    // Configuration.
    private static final int POPULATION_SIZE = 50;
    private static final int ROUND_LIMIT = 10000;
    private static final int INITIAL_LENGTH = 5;
    private static final int MAX_LENGTH = 15;
    
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        // Run the algorithm.
        GeneticAlgorithmConfiguration<String> pipeline = new PalindromeExample();
        GeneticAlgorithmEngine<String> engine = new SequentialGeneticAlgorithmEngine<>();
        ResultState data = engine.executePipeline(pipeline);
        // Get the best all time result from the algorithm.
        IndividualFitness<String> ind = data.getBestIndividual();
        // Check whether palidrome property holds. Print details.
        String result = ind.getIndividual();
        String reverse = reverseString(result);
        System.out.println("best fitness: " + ind.getFitness() + " individual: " + result + " length: " + result.length());
        System.out.println("Palindrome check passed: " + result.equals(reverse));
    }

    @Override
    protected VariationPipeline<String> variationPipeline() {
        VariationPipeline<String> variationPipeline = new VariationPipeline<>(RANDOM);
        variationPipeline.setCombiner(null);
        variationPipeline.addMutator(new PerLociStringMutator(1.0 / 10.0, RANDOM));
        variationPipeline.addMutator(new InsertionStringMutator(0.01, true, RANDOM));
        variationPipeline.addMutator(new RemovalStringMutator(0.01, true, RANDOM));
        return variationPipeline;
    }

    @Override
    protected FitnessFunction<String> fitnessFunction() {
        return (individual, population) -> {
            double score = 0;
            int length = individual.length();
            // Iterate over each character.
            for (int i = 0; i < length; i++) {
                char atPos = individual.charAt(i);
                // Favour letters to speed up the search process.
                if ((atPos >= 'a' && atPos <= 'z')) {
                    score += 0.5;
                }
                // Point per matching pair per letter.
                if (atPos == individual.charAt(length - 1 - i)) {
                    if ((atPos >= 'a' && atPos <= 'z')) {
                        score += 1.0;
                    }
                }
            }
            // Limit inidividual length to 20, else fitness drops off.
            if (length > MAX_LENGTH) {
                score = 0;
            }
            return score;
        };
    }

    @Override
    protected Selector<String> selector() {
        return new FitnessProportionateSelector<String>(RANDOM, true, (a) -> Math.pow(a, 2));
    }

    @Override
    protected Populator<String> populator() {
        return new RandomStringPopulator(RANDOM, INITIAL_LENGTH);
    }

    @Override
    public int populationSize() {
        return POPULATION_SIZE;
    }

    @Override
    protected RoundStrategy<String> roundStrategy() {
        return new GenerationalRoundStrategy();
    }

    @Override
    protected PopulationModel<String> populationModel() {
        return new SinglePopulationModel<>();
    }

    @Override
    protected TerminationCondition terminationCondition() {
        return new IterationCountTerminationCondition(ROUND_LIMIT);
    }

    @Override
    protected List<Interactor> interactors() {
        List<Interactor> interactors = new ArrayList<>();
        interactors.add(new RoundBestInteractor(SinglePopulationModel.KEY_OPTIMUM, 100));
        return interactors;
    }

    private static String reverseString(String str) {
        StringBuilder builder = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            builder.append(str.charAt(i));
        }
        return builder.toString();
    }
}
