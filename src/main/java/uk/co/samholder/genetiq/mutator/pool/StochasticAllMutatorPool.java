/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.mutator.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import uk.co.samholder.genetiq.mutator.Mutator;

/**
 * A mutator pool that applies mutators with given probabilities.
 *
 * Multiple mutators may be applied to one child.
 */
public class StochasticAllMutatorPool implements MutatorPool {

    private Map<Mutator, Double> mutatorProbabilities = new HashMap<>();
    private final Random random;

    public StochasticAllMutatorPool() {
        random = new Random();
    }

    public StochasticAllMutatorPool(Random random) {
        this.random = random;
    }

    public StochasticAllMutatorPool use(Mutator mutator, double probability) {
        mutatorProbabilities.put(mutator, probability);
        return this;
    }

    @Override
    public Object mutate(Object individual) {
        for (Mutator mutator : mutatorProbabilities.keySet()) {
            double probability = mutatorProbabilities.get(mutator);
            if (random.nextDouble() <= probability) {
                individual = mutator.mutate(individual);
            }
        }
        return individual;
    }

}
