/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.population.single;

import uk.co.samholder.genetiq.data.OutType;
import uk.co.samholder.genetiq.individuals.IndividualFitness;
import uk.co.samholder.genetiq.population.Population;

/**
 *
 * @author sam
 */
public class SinglePopulationModelOutputs<I extends Object> {

    private final OutType<Population<I>> population = new OutType<>("population");
    private final OutType<IndividualFitness<I>> bestIndividual = new OutType<>("bestIndividual");

    public OutType<Population<I>> getPopulation() {
        return population;
    }

    public OutType<IndividualFitness<I>> getBestIndividual() {
        return bestIndividual;
    }

}
