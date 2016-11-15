/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.interactor;

import uk.co.samholder.genetiq.data.RunData;
import uk.co.samholder.genetiq.population.IndividualFitness;

/**
 *
 * @author sam
 */
public class MemoriseBestInteractor<I> implements Interactor {

    public static final String BEST_INDIVIDUAL_FITNESS = "MemoriseBestInteractor_bestIndividualFitness";

    private final String bestIndividualKey;
    private Double bestFitness = null;

    public MemoriseBestInteractor(String bestIndividualKey) {
        this.bestIndividualKey = bestIndividualKey;
    }

    @Override
    public void interact(RunData observed) {
        IndividualFitness<I> individualFitness = observed.get(bestIndividualKey);
        if (bestFitness == null
                || individualFitness.getFitness() > bestFitness) {
            bestFitness = individualFitness.getFitness();
            observed.set(BEST_INDIVIDUAL_FITNESS, individualFitness);
        }
    }

}
