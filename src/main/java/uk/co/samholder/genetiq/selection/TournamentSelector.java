/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.selection;

import java.util.ArrayList;
import java.util.List;
import uk.co.samholder.genetiq.individuals.IndividualFitness;
import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.util.ListUtil;

/**
 * Tournament Selector
 *
 * To select an individual, n individuals are selected to play in a
 * "tournament". The winning player, that with the highest fitness is selected.
 * This process is repeated until all individuals are selected.
 *
 * @author Sam Holder
 */
public class TournamentSelector<I> implements Selector<I> {

    private final int tournamentSize;

    /**
     * @param tournamentSize number of individuals competing per tournament
     */
    public TournamentSelector(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    /**
     * Samples n individuals from the population at random, and selects the
     * best.
     *
     * @param individualFitness list of individual fitness values in the
     * population
     * @return round winner individual
     */
    private I roundWinner(List<IndividualFitness<I>> individualFitness) {
        IndividualFitness<I> bestPlayer = ListUtil.selectRandom(individualFitness);
        for (int i = 0; i < tournamentSize - 1; i++) {
            IndividualFitness<I> contender = ListUtil.selectRandom(individualFitness);
            if (contender.getFitness() > bestPlayer.getFitness()) {
                bestPlayer = contender;
            }
        }
        return bestPlayer.getIndividual();
    }

    @Override
    public List<I> select(Population<I> population, int numToSelect) {
        List<IndividualFitness<I>> individualFitness = population.getIndividualFitnesses();
        List<I> selected = new ArrayList<>();
        for (int i = 0; i < numToSelect; i++) {
            selected.add(roundWinner(individualFitness));
        }
        return selected;
    }

}
