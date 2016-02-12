/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.runner.genetic.builder;

import uk.co.samholder.genetiq.populator.Populator;

/**
 *
 * @author sam
 */
public class PopulatorBuilder<I extends Object> {

    private Populator<I> populator;

    public void use(Populator<I> populator) {
        this.populator = populator;
    }

    /**
     * Builds and validates the population.
     *
     * @return the populator.
     */
    public Populator<I> build() {
        if (populator == null) {
            throw new IllegalStateException("Populator has not been initialised");
        }
        return populator;
    }
}
