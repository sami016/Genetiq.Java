/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.representation.ann;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sam
 */
public class OutputNode extends Node {

    private double beta = 0.5;

    public OutputNode() {
    }

    public OutputNode(Map<Integer, Double> connections) {
        super(connections);
    }

    public OutputNode(double beta) {
        this.beta = beta;
    }

    public double activationFunction(double x) {
        return 1.0 / (1.0 + Math.exp(-beta * x));
    }

    @Override
    public double getValue() {
        return activationFunction(getInputsSum());
    }

    @Override
    public Node clone() {
        Map<Integer, Double> connections = new HashMap<>(getConnections());
        OutputNode clone = new OutputNode(connections);
        return clone;
    }
}
