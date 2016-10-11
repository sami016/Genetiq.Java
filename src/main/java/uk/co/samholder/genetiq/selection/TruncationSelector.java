/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import uk.co.samholder.genetiq.individuals.IndividualFitness;
import uk.co.samholder.genetiq.population.Population;

/**
 * Truncation Selector
 *
 * The population is sorted by fitness and the top (1/p) of individuals are
 * taken. These then reproduce p times to produce the next population. Not often
 * used in practice.
 *
 * @author Sam Holder
 */
public class TruncationSelector<I> implements Selector<I> {

    private final int p;

    public TruncationSelector(int pFactor) {
        this.p = pFactor;
    }

    /**
     * Truncate a list of individual fitness values, taking the first n.
     *
     * @param list individual fitness list
     * @return truncated list of individuals
     */
    public List<I> truncate(List<IndividualFitness<I>> list) {
        // Sort the list by fitness.
        Collections.sort(list);
        // Select the n fittest individuals.
        List<I> truncated = new ArrayList<>();
        int numberInTruncated = (int) (Math.floor(1 / (double) p));
        for (int i = 0; i < numberInTruncated; i++) {
            truncated.add(list.get(i).getIndividual());
        }
        return truncated;
    }

    @Override
    public List<I> select(Population<I> population, int numToSelect) {
        List<I> truncated = truncate(population.getIndividualFitnesses());
        List<I> selected = new ArrayList<>();
        // Select from the truncated list, with wrapping.
        for (int i = 0; i < numToSelect; i++) {
            selected.add(truncated.get(i % truncated.size()));
        }
        return selected;
    }

}
