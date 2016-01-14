/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.combiner;

import java.util.Map;

/**
 *
 * @author sam
 */
public interface CoevolutionCombiner<I1 extends Object, I2 extends Object> {

    I2 combine(Map<Integer, I1> individuals);

}
