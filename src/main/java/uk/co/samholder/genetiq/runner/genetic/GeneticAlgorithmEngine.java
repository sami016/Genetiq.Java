/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.runner.genetic;

import uk.co.samholder.genetiq.runner.generic.ExecutionEngine;

/**
 * A standard genetic algorithm runner.
 * @author Sam Holder
 */
public interface GeneticAlgorithmEngine<I> extends ExecutionEngine<GeneticAlgorithmConfiguration<I>> {
    
    public static final String KEY_PERIOD_TYPE = "GeneticAlgorithm_periodType";
    public static final String KEY_PERIOD_NUMBER = "GeneticAlgorithm_periodNumber";
    
}
