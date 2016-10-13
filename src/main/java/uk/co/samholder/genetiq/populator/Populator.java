/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.populator;

/**
 *
 * @author sam
 * @param <I>
 */
public interface Populator<I extends Object> {

    I getIndividual();

}
