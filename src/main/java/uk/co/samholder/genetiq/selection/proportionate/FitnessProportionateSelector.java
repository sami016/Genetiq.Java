/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.selection.proportionate;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import uk.co.samholder.genetiq.individuals.IndividualFitness;
import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.selection.Selector;

/**
 *
 * @author sam
 */
public class FitnessProportionateSelector<I extends Object> implements Selector<I> {

    private final Random random;
    private final boolean useNormalisation;

    public FitnessProportionateSelector(Random random, boolean useNormalisation) {
        this.random = random;
        this.useNormalisation = useNormalisation;
    }

    private double adjustFitness(double basicFitness, double minFitness) {
        if (useNormalisation) {
            return basicFitness - minFitness;
        } else {
            return basicFitness;
        }
    }

    private I selectOne(double total, double minFitness, List<IndividualFitness<I>> individualFitnesses) {
        // Get a random number within the total.
        double randomNum = random.nextDouble() * total;

        double count = 0;
        for (IndividualFitness<I> individualFitness : individualFitnesses) {
            count += adjustFitness(individualFitness.getFitness(), minFitness);
            if (count >= randomNum) {
                return individualFitness.getIndividual();
            }
        }
        // Should never get to here.
        throw new IllegalStateException("Error occured while performing fitness proportionate selection!");
    }

    @Override
    public List<I> select(Population<I> population, int numToSelect) {
        double minFitness = population.getWorstIndividual().getFitness();
        // Construct position map.
        double total = 0;

        List<IndividualFitness<I>> individualFitnesses = population.getIndividualFitnesses();
        for (IndividualFitness<I> individualFitness : individualFitnesses) {
            total += adjustFitness(individualFitness.getFitness(), minFitness);
        }
        // Do the selection into a list.
        List<I> list = new LinkedList<I>();
        for (int i = 0; i < numToSelect; i++) {
            list.add(selectOne(total, minFitness, individualFitnesses));
        }
        return list;
    }

}
