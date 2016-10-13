/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.representation.string;

import java.util.Random;
import uk.co.samholder.genetiq.mutator.Mutator;

/**
 * Basic string mutator which mutates each loci independently with a given
 * probability.
 *
 * @author Sam Holder
 */
public class PerLociStringMutator implements Mutator<String> {

    private final Random random;
    private final double lociMutationRate;

    public PerLociStringMutator(double lociMutationRate, Random random) {
        this.random = random;
        this.lociMutationRate = lociMutationRate;
    }

    @Override
    public String mutate(String individual) {
        final double ratePerLoci = 1.0 / individual.length();
        StringBuilder mutantBuilder = new StringBuilder();
        for (int i = 0; i < individual.length(); i++) {
            if (random.nextDouble() < ratePerLoci * lociMutationRate) {
                mutantBuilder.append((char) random.nextInt(128));
            } else {
                mutantBuilder.append(individual.substring(i, i + 1));
            }
        }
        return mutantBuilder.toString();
    }

}
