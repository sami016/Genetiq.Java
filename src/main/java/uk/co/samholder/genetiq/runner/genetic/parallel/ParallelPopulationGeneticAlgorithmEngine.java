package uk.co.samholder.genetiq.runner.genetic.parallel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.population.PopulationModel;
import uk.co.samholder.genetiq.round.RoundStrategy;
import uk.co.samholder.genetiq.runner.genetic.SequentialGeneticAlgorithmEngine;
import uk.co.samholder.genetiq.selection.Selector;
import uk.co.samholder.genetiq.variation.VariationEngine;

/**
 * A parallel genetic algorithm execution engine intended to utilise multiple threads.
 * Tasks are performed on a population level - use multi-deme population to gain benefit.
 * 
 * @author Sam Holder
 */
public class ParallelPopulationGeneticAlgorithmEngine<I> extends SequentialGeneticAlgorithmEngine<I> {
    
    private ExecutorService executor;

    public ParallelPopulationGeneticAlgorithmEngine(int numThreads) {
        executor = Executors.newFixedThreadPool(numThreads);
    }

    @Override
    protected void PerformRound(PopulationModel<I> populationModel, RoundStrategy<I> roundStrategy, VariationEngine<I> variationEngine, Selector<I> selector) {
        MultiplexLock lock = new MultiplexLock();
        for (Population<I> pop : populationModel) {
            startPopulationTask(pop, roundStrategy, variationEngine, selector, lock);
        }
        lock.awaitCompletion();
    }
    
    /**
     * Starts a task to process all individuals within a population.
     * @param population population
     * @param roundStrategy round strategy
     * @param variationEngine variation engine
     * @param lock multiplex lock
     */
    private void startPopulationTask(Population<I> population, RoundStrategy<I> roundStrategy, VariationEngine<I> variationEngine, Selector<I> selector, MultiplexLock lock) {
        lock.beginTask();
        executor.execute(() -> {
            roundStrategy.performRound(population, variationEngine, selector);
            lock.completeTask();
        });
    }

    @Override
    protected void onFinish() {
        executor.shutdown();
    }
    
    
}
