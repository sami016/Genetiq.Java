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
public class Output<T extends Object> {

    String getKey() {
        return getClass().getCanonicalName();
    }

    T convert(Object object) {
        return (T) object;
    }
}
