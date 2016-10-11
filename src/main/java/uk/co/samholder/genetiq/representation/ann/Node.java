/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.representation.ann;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author sam
 */
public abstract class Node {

    private Map<Integer, Double> connections;

    /**
     * Default constructor: create a node with no children.
     */
    public Node() {
        connections = new HashMap<>();
    }

    public Node(Map<Integer, Double> connections) {
        this.connections = connections;
    }

    /**
     * Get the connections mapping.
     *
     * @return connection mapping.
     */
    public Map<Integer, Double> getConnections() {
        return connections;
    }

    /**
     * Get the connection strength from a given child node.
     *
     * @param node the node index.
     * @return the connection strength with the node.
     */
    public double getConnectionStrength(Integer node) {
        return connections.get(node);
    }

    /**
     * Get the connected nodes in the previous layer.
     *
     * @return set of connected node indices.
     */
    public Set<Integer> getConnectedNodes() {
        return connections.keySet();
    }

    /**
     * Returns the sum of the node's children.
     *
     * @return sum of children.
     */
    public double getInputsSum() {
        double accumulator = 0;
        for (Integer nodeNum : getConnectedNodes()) {
            accumulator += 0; // todo //node.getValue() * getConnectionStrength(node);
        }
        return accumulator;
    }

    public abstract double getValue();

    public abstract Node clone();

}
