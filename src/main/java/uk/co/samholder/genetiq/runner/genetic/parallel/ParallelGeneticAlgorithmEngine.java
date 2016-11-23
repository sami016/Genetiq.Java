package uk.co.samholder.genetiq.runner.genetic.parallel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import uk.co.samholder.genetiq.population.PopulationModel;
import uk.co.samholder.genetiq.round.RoundStrategy;
import uk.co.samholder.genetiq.runner.genetic.SequentialGeneticAlgorithmEngine;
import uk.co.samholder.genetiq.variation.VariationEngine;

/**
 * A parallel genetic algorithm execution engine intended to utilise multiple threads.
 * 
 * @author Sam Holder
 */
public class ParallelGeneticAlgorithmEngine<I> extends SequentialGeneticAlgorithmEngine<I> {
    
    private Executor executor;

    public ParallelGeneticAlgorithmEngine(int numThreads) {
        executor = Executors.newFixedThreadPool(numThreads);
    }

    @Override
    protected void PerformRound(PopulationModel<I> populationModel, RoundStrategy<I> roundStrategy, VariationEngine<I> variationEngine) {
        
    }
    
    
    
}
