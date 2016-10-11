/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.mutator.structure;

import java.util.List;

/**
 * Extracts units of an individual for mutation.
 */
public interface Structure<I, J> {

    public List<J> get(I object);

}
