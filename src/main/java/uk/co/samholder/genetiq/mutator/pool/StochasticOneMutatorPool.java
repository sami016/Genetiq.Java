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
 * Exactly one mutator is applied per individual.
 *
 * @author sam
 */
public class StochasticOneMutatorPool implements MutatorPool {

    private Map<Mutator, Double> mutatorOdds = new HashMap<>();
    private final Random random;

    public StochasticOneMutatorPool() {
        random = new Random();
    }

    public StochasticOneMutatorPool(Random random) {
        this.random = random;
    }

    public StochasticOneMutatorPool use(Mutator mutator, double relativeOdds) {
        mutatorOdds.put(mutator, relativeOdds);
        return this;
    }

    @Override
    public Object mutate(Object individual) {
        double total = mutatorOdds.values().stream()
                .reduce(0.0, (a, b) -> a + b);
        double randomPos = random.nextDouble() * total;

        double count = 0.0;
        for (Mutator mutator : mutatorOdds.keySet()) {
            double odds = mutatorOdds.get(mutator);
            count += odds;
            if (count >= randomPos) {
                return mutator.mutate(individual);
            }
        }
        throw new IllegalStateException("This should never happen");
    }

}
