/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.fitness;

import uk.co.samholder.genetiq.population.Population;

/**
 * Calculates the fitness of an individual.
 *
 * @author Sam Holder
 */
@FunctionalInterface
public interface FitnessFunction<I extends Object> {

    /**
     * Calculates an individuals score within an environment.
     *
     * @param individual The individual being assessed.
     * @return the fitness score for the individual.
     */
    double calculateFitness(I individual, Population<I> population);

}
