/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.runner.genetic;

import uk.co.samholder.genetiq.data.OutType;
import uk.co.samholder.genetiq.data.Period;

/**
 *
 * @author sam
 */
public class GeneticAlgorithmOutputs<I extends Object> {

    private final OutType<Integer> periodNumber = new OutType<>("periodNumber");
    private final OutType<Period> periodType = new OutType<>("periodType");

    public OutType<Integer> getPeriodNumber() {
        return periodNumber;
    }

    public OutType<Period> getPeriodType() {
        return periodType;
    }

}
