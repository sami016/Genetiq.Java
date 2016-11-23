package uk.co.samholder.genetiq.runner.genetic.parallel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * A parallel genetic algorithm execution engine intended to utilise multiple threads.
 * 
 * 
 * @author Sam Holder
 */
public class ParallelGeneticAlgorithmEngine {
    
    private Executor executor;

    public ParallelGeneticAlgorithmEngine(int numThreads) {
        executor = Executors.newFixedThreadPool(numThreads);
    }
    
    
    
}
