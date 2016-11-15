/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.termination;

import uk.co.samholder.genetiq.population.Population;

/**
 *
 * @author sam
 */
public class FitnessThresholdTerminationCondition<I extends Object> implements TerminationCondition<I> {

    private final double threshold;

    public FitnessThresholdTerminationCondition(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean conditionMet(Population<I> population, int iteration) {
        return population.getBestIndividual().getFitness() >= threshold;
    }

}
