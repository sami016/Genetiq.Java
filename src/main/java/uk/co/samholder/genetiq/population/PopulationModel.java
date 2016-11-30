package uk.co.samholder.genetiq.population;

import java.util.function.Supplier;
import uk.co.samholder.genetiq.data.ResultState;
import uk.co.samholder.genetiq.termination.TerminationCondition;

/**
 * A model representing a population or set of populations within an algorithm.
 *
 * @author Sam Holder
 */
public interface PopulationModel<I extends Object> extends Iterable<Population<I>> {

    /**
     * Checks whether a termination condition is met at a given iteration.
     *
     * @param termintionCondition the termination condition
     * @param iteration the iteration number
     * @return is termination condition satisfied
     */
    boolean isConditionMet(TerminationCondition<I> termintionCondition, int iteration);
    
    /**
     * Seeds individuals within populations upon initialisation.
     * @param populationSupplier population supplier
     * @param fitnessFunction fitness function
     * @param selector selector
     * @param populator populator
     */
    void Initialise(Supplier<Population<I>> populationSupplier, Populator<I> populator);
    
    /**
     * Runs before each round of the algorithm.
     * @param runData run data
     */
    void preRound(ResultState<I> runData);
    
    /**
     * Runs after each round of the algorithm.
     * @param runData run data
     */
    void postRound(ResultState<I> runData);

    /**
     * Writes out observation data to the run data context.
     * Is run after each round of the algorithm just after postRound is invoked.
     *
     * @param runData run data context
     */
    void writeData(ResultState<I> runData);
    
}
