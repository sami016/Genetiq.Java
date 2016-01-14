/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import uk.co.samholder.genetiq.fitness.FitnessFunction;
import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.population.single.SinglePopulationModel;
import uk.co.samholder.genetiq.runner.genetic.GeneticAlgorithm;
import uk.co.samholder.genetiq.runner.genetic.GeneticAlgorithmBuilder;

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
    }

    public static void main(String[] args) {
        GeneticAlgorithmBuilder<String> geneticAlgorithmBuilder = new GeneticAlgorithmBuilder<String>();
        geneticAlgorithmBuilder.setFitnessFunction(stringScore);
        geneticAlgorithmBuilder.setNumIndividuals(1000);
        geneticAlgorithmBuilder.setPopulationModel(new SinglePopulationModel<String>(stringScore, 1000));

        GeneticAlgorithm<String> ga = geneticAlgorithmBuilder.build();
    }

}
