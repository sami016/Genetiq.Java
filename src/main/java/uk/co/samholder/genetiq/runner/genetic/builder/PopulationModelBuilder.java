/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.runner.genetic.builder;

import uk.co.samholder.genetiq.fitness.FitnessFunction;
import uk.co.samholder.genetiq.population.PopulationModel;
import uk.co.samholder.genetiq.population.single.SinglePopulationModel;

/**
 * A builder object for
 *
 * @author sam
 */
public class PopulationModelBuilder<I extends Object> {

    private PopulationModel<I> populationModel;

    public PopulationModelBuilder() {

    }

    /**
     * Use a given population model.
     *
     * @param populationModel the population model to use.
     */
    public void use(PopulationModel<I> populationModel) {
        this.populationModel = populationModel;
    }

    /**
     * Use a simple single population model where all individuals comprise one
     * population.
     *
     * @param fitnessFunction The fitness function.
     * @param populationSize The population size.
     */
    public void useSinglePopulationModel(FitnessFunction<I> fitnessFunction, int populationSize) {
        use(new SinglePopulationModel<I>(fitnessFunction, populationSize));
    }

    /**
     * Builds and validates the population model.
     *
     * @return the population model to use.
     */
    public PopulationModel<I> build() {
        if (populationModel == null) {
            throw new IllegalStateException("Population model has not been initialised");
        }
        return populationModel;
    }

}
