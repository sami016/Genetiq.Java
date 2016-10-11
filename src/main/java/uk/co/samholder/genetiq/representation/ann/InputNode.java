/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.representation.ann;

/**
 * Input node, found only within the first layer.
 */
public class InputNode extends Node {

    private double value;

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Node clone() {
        return new InputNode();
    }

}
