/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.data;

/**
 *
 * @author sam
 */
public enum Period {

    GENERATIONS("generation"),
    ROUNDS("round");

    private String singular;

    private Period(String singular) {
        this.singular = singular;
    }

    public String getSingular() {
        return singular;
    }

}
