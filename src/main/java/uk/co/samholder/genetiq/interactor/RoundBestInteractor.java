/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.interactor;

import java.io.PrintStream;
import uk.co.samholder.genetiq.data.RunData;
import uk.co.samholder.genetiq.individuals.IndividualFitness;
import uk.co.samholder.genetiq.runner.genetic.GeneticAlgorithm;

/**
 *
 * @author sam
 */
public class RoundBestInteractor implements Interactor {

    private final String bestIndividualKey;
    private final int roundFactorFilter;
    private final PrintStream out;

    public RoundBestInteractor(String bestIndividualKey) {
        this(bestIndividualKey, 1);
    }

    public RoundBestInteractor(String bestIndividualKey, int roundFactorFilter) {
        this(bestIndividualKey, roundFactorFilter, System.out);
    }

    public RoundBestInteractor(String bestIndividualKey, int roundFactorFilter, PrintStream out) {
        this.bestIndividualKey = bestIndividualKey;
        this.out = out;
        this.roundFactorFilter = roundFactorFilter;
    }

    @Override
    public void interact(RunData observed) {
        int roundNumber = observed.get(GeneticAlgorithm.KEY_PERIOD_NUMBER);
        if (roundNumber % roundFactorFilter != 0) {
            return;
        }

        IndividualFitness<?> individualFitness = observed.get(bestIndividualKey);
        StringBuilder builder = new StringBuilder();
        builder.append("round ");
        builder.append(roundNumber);
        builder.append(": fitness = ");
        builder.append(individualFitness.getFitness());
        builder.append(" for individual ");
        builder.append(individualFitness.getIndividual().toString());
        out.println(builder.toString());
    }

}
