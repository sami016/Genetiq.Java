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
public class Layer {

    private List<Node> nodes;

    public Layer() {
        nodes = new ArrayList<>();
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public int getNumNodes() {
        return nodes.size();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void removeNode(Node node) {
        nodes.remove(node);
    }

}
