/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.interactor;

import java.util.ArrayList;
import java.util.List;
import uk.co.samholder.genetiq.interactor.Interactor;

/**
 *
 * @author sam
 */
public class InteractorsBuilder<I extends Object> {

    private List<Interactor> interactors = new ArrayList<>();

    /**
     * Add an interactor.
     *
     * @param interactor the interactor.
     */
    public void add(Interactor interactor) {
        this.interactors.add(interactor);
    }

    /**
     * Builds and validates the interactors.
     *
     * @return the interactors list.
     */
    public List<Interactor> build() {
        return interactors;
    }

}
