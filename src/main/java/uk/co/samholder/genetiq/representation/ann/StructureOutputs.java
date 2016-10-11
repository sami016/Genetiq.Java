/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.representation.ann;

import java.util.List;
import uk.co.samholder.genetiq.mutator.structure.Structure;

/**
 *
 * @author sam
 */
public class StructureOutputs implements Structure<SimpleNN, Node> {

    @Override
    public List<Node> get(SimpleNN object) {
        return object.getOutputNodes();
    }

}
