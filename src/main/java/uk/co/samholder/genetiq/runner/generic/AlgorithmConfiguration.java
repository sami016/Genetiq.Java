/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.runner.generic;

/**
 * Contains the execution context for an algorithm.
 * @author Sam Holder
 */
public interface AlgorithmConfiguration {
    
    /**
     * Validates that the pipeline is valid.
     * @return valid
     */
    public boolean validate();
}
