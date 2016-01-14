/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.selection.tournament;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import uk.co.samholder.genetiq.individuals.IndividualFitness;
import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.selection.Selector;

/**
 *
 * @author sam
 */
public class TournamentSelector<I extends Object> implements Selector<I> {

    private final Random random;

    public TournamentSelector(Random random) {
        this.random = random;
    }

    private IndividualFitness<I> randomIndividual(Population<I> population) {
        return population.getIndividualAtIndex(random.nextInt(population.size()));
    }

    @Override
    public List<I> select(Population<I> population, int numToSelect) {
        List<I> victors = new ArrayList<>();
        for (int i = 0; i < numToSelect; i++) {
            // Select two random individuals.
            IndividualFitness<I> existingA = randomIndividual(population);
            IndividualFitness<I> existingB = randomIndividual(population);
            // Add the best to the victor list.
            if (existingA.getFitness()
                    > existingB.getFitness()) {
                victors.add(existingA.getIndividual());
            } else {
                victors.add(existingB.getIndividual());
            }
        }
        return victors;
    }

}
