/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.selection;

import java.util.function.Function;

/**
 *
 * @author sam
 */
public class ScalingOperations {

    /**
     * Returns a quadratic scaling function.
     *
     * @return
     */
    public static Function<Double, Double> quadratic() {
        return (input) -> {
            return Math.pow(2, input);
        };
    }

    /**
     * Returns an exponential scaling operation.
     *
     * output = outer * base ^ ( inner * input )
     *
     * @param base exponent base
     * @param innerFactor inner factor
     * @param outerFactor outer factor
     * @return a scaling operation that performs exponential scaling on the
     * input
     */
    public static Function<Double, Double> exponential(double base, double innerFactor, double outerFactor) {
        return (input) -> {
            return outerFactor * Math.pow(base, innerFactor * input);
        };
    }

    /**
     * Returns the sum of several other scaling operations.
     *
     * @param functions scaling functions
     * @return a scaling operation that sums the output of other scaling
     * operations
     */
    public static Function<Double, Double> sum(Function<Double, Double>... functions) {
        return (input) -> {
            double sumTotal = 0.0;
            for (Function<Double, Double> function : functions) {
                sumTotal += function.apply(input);
            }
            return sumTotal;
        };
    }

}
