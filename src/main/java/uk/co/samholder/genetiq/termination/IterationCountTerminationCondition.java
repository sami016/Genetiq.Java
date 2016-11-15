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
public class IterationCountTerminationCondition<I extends Object> implements TerminationCondition<I> {

    private int count;

    public IterationCountTerminationCondition(int count) {
        this.count = count;
    }

    @Override
    public boolean conditionMet(Population<I> population, int iteration) {
        return iteration > count;
    }

}
