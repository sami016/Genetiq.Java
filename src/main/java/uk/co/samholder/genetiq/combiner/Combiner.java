/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.combiner;

import java.util.List;

/**
 *
 * @author sam
 */
public interface Combiner<I extends Object> {

    int getNumberPerCrossover();

    I combine(List<I> individuals);

}
