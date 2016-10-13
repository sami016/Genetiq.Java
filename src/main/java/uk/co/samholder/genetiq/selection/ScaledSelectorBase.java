/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.selection;

import java.util.List;
import java.util.Random;
import uk.co.samholder.genetiq.individuals.IndividualFitness;

/**
 * An abstract base class for selectors to extend that require common selection
 * logic.
 *
 * @author sam
 */
public abstract class ScaledSelectorBase<I> {

    protected final Random random;

    public ScaledSelectorBase(Random random) {
        this.random = random;
    }

    /**
     * Selects a single individual from the population at a given position in
     * the cumulative fitness space.
     *
     * @param selectPosition the position to select for in cumulative fitness
     * space
     * @param individualFitnesses list of individual fitness values
     * @return selected individual
     */
    protected I selectOneAtPos(double selectPosition, List<IndividualFitness<I>> individualFitnesses) {
        // Select the individual when the cumulative fitness >= the random value.
        double count = 0;
        for (IndividualFitness<I> individualFitness : individualFitnesses) {
            count += individualFitness.getFitness();
            if (count >= selectPosition) {
                return individualFitness.getIndividual();
            }
        }
        // Should never get to here.
        throw new IllegalStateException("Error occured while performing fitness proportionate selection!");
    }

    /**
     * Selects a single individual from the population.
     *
     * @param total The fitness sum of the population.
     * @param individualFitnesses list of individual fitness values.
     * @return selected individual.
     */
    protected I selectOne(double total, List<IndividualFitness<I>> individualFitnesses) {
        // Get a random number within the total.
        double randomNum = random.nextDouble() * total;
        return selectOneAtPos(randomNum, individualFitnesses);
    }
}
