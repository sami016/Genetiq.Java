/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.representation.string;

import java.util.Random;
import uk.co.samholder.genetiq.mutator.Mutator;

/**
 * Simple insertion string mutator that inserts characters into a string with a
 * given probability.
 *
 * @author Sam Holder
 */
public class InsertionStringMutator implements Mutator<String> {

    private final Random random;
    private final boolean allowMultiple;
    private final double insertionProbability;

    public InsertionStringMutator(double insertionProbability, boolean allowMultiple, Random random) {
        this.random = random;
        this.allowMultiple = allowMultiple;
        this.insertionProbability = insertionProbability;
    }

    private String insert(String individual) {
        char insertChar = (char) random.nextInt(128);
        int insertPos = random.nextInt(individual.length() + 1);
        // Append if at end.
        if (insertPos == individual.length()) {
            return individual + insertChar;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(individual.substring(0, insertPos));
        builder.append(insertChar);
        builder.append(individual.substring(insertPos, individual.length()));
        return builder.toString();
    }

    @Override
    public String mutate(String individual) {
        while (random.nextDouble() < insertionProbability) {
            individual = insert(individual);
            if (!allowMultiple) {
                return individual;
            }
        }
        return individual;
    }

}
