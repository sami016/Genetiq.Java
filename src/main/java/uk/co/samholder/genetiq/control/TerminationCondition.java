/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.control;

import uk.co.samholder.genetiq.population.Population;

/**
 *
 * @author sam
 */
@FunctionalInterface
public interface TerminationCondition<I extends Object> {

    public boolean conditionMet(Population<I> population, int iteration);

}
