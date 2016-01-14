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
public class OutType<TYPE> {

    private final String name;

    public OutType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
