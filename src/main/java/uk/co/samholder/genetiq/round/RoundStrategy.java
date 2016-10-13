/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.round;

import uk.co.samholder.genetiq.data.Period;
import uk.co.samholder.genetiq.population.Population;

/**
 * Determines how each round of the genetic algorithm is run, an integral part
 * to the genetic algorithm.
 *
 * @author Sam Holder
 */
public interface RoundStrategy<I extends Object> {

    void performRound(Population<I> population);

    Period getPeriodType();

}
