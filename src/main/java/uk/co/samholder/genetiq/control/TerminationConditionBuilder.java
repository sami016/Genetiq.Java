/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.control;

import uk.co.samholder.genetiq.control.FitnessThresholdTerminationCondition;
import uk.co.samholder.genetiq.control.IterationCountTerminationCondition;
import uk.co.samholder.genetiq.control.TerminationCondition;

/**
 * Builds termination conditions.
 *
 * @author sam
 */
public class TerminationConditionBuilder<I extends Object> {

    private TerminationCondition<I> terminationCondition;

    /**
     * Use an arbitrary termination condition.
     *
     * @param terminationCondition the termination condition.
     */
    public void use(TerminationCondition<I> terminationCondition) {
        this.terminationCondition = terminationCondition;
    }

    /**
     * Terminate execution after a predefined number of iterations.
     *
     * @param n The number of iterations to run for.
     */
    public void useNIterations(int n) {
        this.terminationCondition = new IterationCountTerminationCondition<>(n);
    }

    /**
     * Terminate after a fitness threshold has been reached by some evolved
     * individual.
     *
     * @param threshold The fitness threshold.
     */
    public void useFitnessThreshold(double threshold) {
        this.terminationCondition = new FitnessThresholdTerminationCondition<>(threshold);
    }

    /**
     * Builds and validates the termination condition.
     *
     * @return the termination condition.
     */
    public TerminationCondition<I> build() {
        if (terminationCondition == null) {
            throw new IllegalStateException("Termination condition has not been initialised");
        }
        return terminationCondition;
    }

}
