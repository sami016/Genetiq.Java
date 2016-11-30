/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.interactor;

import uk.co.samholder.genetiq.data.ResultState;

/**
 * Interactor interface for interacting with the system whilst running.
 *
 * @author sam
 */
public interface Interactor<I> {

    /**
     * Runs once every iteration, depending on the round strategy that is used.
     *
     * @param observed The current run data set.
     */
    public void interact(ResultState<I> observed);

}
