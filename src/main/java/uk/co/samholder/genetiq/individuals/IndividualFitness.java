/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.individuals;

/**
 *
 * @author sam
 */
public class IndividualFitness<I extends Object> {

    private final I individual;
    private final double fitness;

    public IndividualFitness(I individual, double fitness) {
        this.individual = individual;
        this.fitness = fitness;
    }

    public double getFitness() {
        return fitness;
    }

    public I getIndividual() {
        return individual;
    }

    @Override
    public String toString() {
        return fitness + " ~ " + individual.toString();
    }

}