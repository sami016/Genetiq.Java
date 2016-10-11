/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.util;

import java.util.List;
import java.util.Random;

/**
 * List Util
 *
 * @author Sam Holder
 */
public class ListUtil {

    private static Random random = new Random();

    public static <I> I selectRandom(List<I> list) {
        return list.get(random.nextInt(list.size()));
    }

    public static double getMean(List<Double> list) {
        double sum = 0.0;
        for (double value : list) {
            sum += value;
        }
        return sum / list.size();
    }

    public static double getVariance(List<Double> list) {
        double mean = getMean(list);
        double accumulator = 0;
        for (double value : list) {
            accumulator += (value - mean) * (value - mean);
        }
        return accumulator / list.size();
    }

    public static double getStandardDeviation(List<Double> list) {
        return Math.sqrt(getVariance(list));
    }
}
