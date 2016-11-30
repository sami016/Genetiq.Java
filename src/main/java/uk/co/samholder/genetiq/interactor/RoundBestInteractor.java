/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.interactor;

import java.io.PrintStream;
import uk.co.samholder.genetiq.data.ResultState;
import uk.co.samholder.genetiq.population.IndividualFitness;

/**
 *
 * @author sam
 */
public class RoundBestInteractor implements Interactor {

    private final int roundFactorFilter;
    private final PrintStream out;

    public RoundBestInteractor() {
        this(1);
    }

    public RoundBestInteractor(int roundFactorFilter) {
        this(roundFactorFilter, System.out);
    }

    public RoundBestInteractor(int roundFactorFilter, PrintStream out) {
        this.out = out;
        this.roundFactorFilter = roundFactorFilter;
    }

    @Override
    public void interact(ResultState observed) {
        long roundNumber = observed.getRound();
        if (roundNumber % roundFactorFilter != 0) {
            return;
        }

        IndividualFitness<?> individualFitness = observed.getBestIndividual();
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
