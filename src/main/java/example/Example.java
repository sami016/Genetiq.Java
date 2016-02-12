/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import java.util.Random;
import uk.co.samholder.genetiq.combiner.Combiner;
import uk.co.samholder.genetiq.combiner.string.StringUniformCrossover;
import uk.co.samholder.genetiq.data.RunData;
import uk.co.samholder.genetiq.fitness.FitnessFunction;
import uk.co.samholder.genetiq.mutator.Mutator;
import uk.co.samholder.genetiq.mutator.string.StringMutator;
import uk.co.samholder.genetiq.output.Interactor;
import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.populator.string.StringPopulator;
import uk.co.samholder.genetiq.runner.genetic.GeneticAlgorithm;
import uk.co.samholder.genetiq.runner.genetic.builder.GeneticAlgorithmBuilder;
import uk.co.samholder.genetiq.selection.Selector;
import uk.co.samholder.genetiq.selection.proportionate.FitnessProportionateSelector;

/**
 *
 * @author sam
 */
public class Example {

    // A fitness based on the 'a' count of a string.
    private static FitnessFunction<String> stringScore = new FitnessFunction<String>() {
        @Override
        public double calculateFitness(String individual, Population<String> population) {
            double score = 0;
            for (int i = 0; i < individual.length(); i++) {
                if (individual.substring(i, i + 1).equals("a")) {
                    score++;
                }
            }
            return score;
        }
    };

    public static void main(String[] args) {
        // The length of the strings we evolve.
        final int stringLength = 10;

        // Create a random number generator.
        Random random = new Random();
        // Sets the round strategy. In this case, a generational strategy is used.
        Selector<String> selection = new FitnessProportionateSelector<>(random, true);
        Mutator<String> mutation = new StringMutator(random, 1.0);
        Combiner<String> combination = new StringUniformCrossover(random);

        // Create the genetic algorithm builder that configures the GA.
        GeneticAlgorithmBuilder<String> builder = new GeneticAlgorithmBuilder<String>();

        // Set the population strategy. We use a single population of 1000 agents for this model.
        builder.populatorModel().useSinglePopulationModel(stringScore, 1000);

        builder.roundStrategy().useGenerational(selection, mutation, combination);

        // Creates the populator for generating the initial population.
        builder.populator().use(
                new StringPopulator(random, stringLength)
        );

        // Creates the termination condition, in this case termination when fitness target is reached.
        builder.terminationCondition().useFitnessThreshold(stringLength);

        // Create a interactor for showing us the best individual as the run progresses.
        builder.interactors().add(new Interactor() {
            @Override
            public void interact(RunData observed) {
                //IndividualFitness<String> best = observed.get(population.outputs().getBestIndividual());
                //System.out.println(best.getFitness() + " ~ " + best.getIndividual());
            }
        });

        // Finally create the GA object.
        GeneticAlgorithm<String> ga = builder.build();

        // Run the GA, and collect the results.
        RunData runData = ga.run();

        // Extract data fromt he results.
//        int iterations = runData.get(ga.outputs().getPeriodNumber());
//        Period periodType = runData.get(ga.outputs().getPeriodType());
//        IndividualFitness<String> best = runData.get(population.outputs().getBestIndividual());
//
//        System.out.println("GA terminated.");
//        System.out.println("GA ran for " + iterations + " " + periodType + "s");
//        System.out.println("Best found individual '" + best.getIndividual() + "' with fitness " + best.getFitness());
    }

}
