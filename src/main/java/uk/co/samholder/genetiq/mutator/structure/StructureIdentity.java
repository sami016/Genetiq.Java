/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.mutator.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * Maps an object to itself.
 */
public class StructureIdentity<I> implements Structure<I, I> {

    @Override
    public List<I> get(I object) {
        List<I> list = new ArrayList<I>();
        list.add(object);
        return list;
    }

}
