/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.mutator;

/**
 *
 * @author sam
 * @param <I>
 */
public interface Mutator<I extends Object> {

    public I mutate(I individual);

}
