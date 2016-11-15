package uk.co.samholder.genetiq.population;

import uk.co.samholder.genetiq.termination.TerminationCondition;
import uk.co.samholder.genetiq.data.RunData;
import uk.co.samholder.genetiq.round.RoundStrategy;

/**
 * A model representing a population or set of populations within an algorithm.
 *
 * @author Sam Holder
 */
public interface PopulationModel<I extends Object> extends Iterable<Population<I>> {

    /**
     * Gets the size of each population unit.
     *
     * @return population unit size
     */
    int getPopulationUnitSize();

    /**
     * Writes out relevant data to the run data context.
     *
     * @param runData run data context
     */
    void writeData(RunData runData);

    /**
     * Performs the round for the given population(s) given a round strategy.
     *
     * @param roundStrategy round strategy
     * @param runData run data context
     */
    void doPerformRound(RoundStrategy roundStrategy, RunData runData);

    /**
     * Checks whether a termination condition is met at a given iteration.
     *
     * @param termintionCondition the termination condition
     * @param iteration the iteration number
     * @return is termination condition satisfied
     */
    boolean isConditionMet(TerminationCondition<I> termintionCondition, int iteration);

}
