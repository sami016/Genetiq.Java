/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.representation.ann;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sam
 */
public class SimpleNN {

    private int layerHeightLimit = 4;
    private List<Layer> layers = new ArrayList<>();

    public List<Node> getInputNodes() {
        return layers.get(0).getNodes();
    }

    public List<Node> getOutputNodes() {
        return layers.get(layers.size() - 1).getNodes();
    }

}
