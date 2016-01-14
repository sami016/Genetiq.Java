/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.combiner.string;

import java.util.List;
import java.util.Random;
import uk.co.samholder.genetiq.combiner.Combiner;

/**
 *
 * @author sam
 */
public class StringUniformCrossover implements Combiner<String> {

    private final Random random;

    public StringUniformCrossover(Random random) {
        this.random = random;
    }

    @Override
    public int getNumberPerCrossover() {
        return 2;
    }

    @Override
    public String combine(List<String> individuals) {
        String parentA = individuals.get(0);
        String parentB = individuals.get(1);
        if (parentA.length() != parentB.length()) {
            throw new IllegalStateException("Cannot crossover strings of differing length");
        }

        StringBuilder childBuilder = new StringBuilder();
        for (int i = 0; i < parentA.length(); i++) {
            if (random.nextBoolean()) {
                childBuilder.append(parentA.substring(i, i + 1));
            } else {
                childBuilder.append(parentB.substring(i, i + 1));
            }
        }
        return childBuilder.toString();
    }

}
