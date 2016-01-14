/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.population.multideme;

import java.util.List;
import uk.co.samholder.genetiq.data.OutType;
import uk.co.samholder.genetiq.individuals.IndividualFitness;
import uk.co.samholder.genetiq.population.Population;

/**
 *
 * @author sam
 */
public class MultiDemePopulationModelOutputs<I extends Object> {

    private final OutType<List<Population<I>>> populations = new OutType<>("populations");
    private final OutType<List<IndividualFitness<I>>> bestIndividuals = new OutType<>("bestIndividuals");
    private final OutType<IndividualFitness<I>> bestIndividual = new OutType<>("bestIndividual");

    public OutType<IndividualFitness<I>> getBestIndividual() {
        return bestIndividual;
    }

    public OutType<List<IndividualFitness<I>>> getBestIndividuals() {
        return bestIndividuals;
    }

    public OutType<List<Population<I>>> getPopulations() {
        return populations;
    }

}
