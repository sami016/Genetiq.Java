/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.data;

import java.util.List;
import uk.co.samholder.genetiq.population.IndividualFitness;
import uk.co.samholder.genetiq.population.Population;

/**
 * Stores the result state.
 * @author Sam Holder
 */
public class ResultState<I> {

    // The current populations.
    private List<Population<I>> populations;
    // Best individual of all time.
    private IndividualFitness<I> bestIndividual;
    // The number of rounds run so far.
    private long round = 0;
    // The type of round used.
    private Period period;

    public ResultState() {
    }
    
    public List<Population<I>> getPopulations() {
        return populations;
    }

    public void setPopulations(List<Population<I>> populations) {
        this.populations = populations;
    }

    public IndividualFitness<I> getBestIndividual() {
        return bestIndividual;
    }

    public void setBestIndividual(IndividualFitness<I> bestIndividual) {
        this.bestIndividual = bestIndividual;
    }

    public long getRound() {
        return round;
    }

    public void setRound(long round) {
        this.round = round;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
    
    
    

}
