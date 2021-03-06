package uk.co.samholder.genetiq.population;

import uk.co.samholder.genetiq.data.RunData;
import uk.co.samholder.genetiq.termination.TerminationCondition;

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
     * Checks whether a termination condition is met at a given iteration.
     *
     * @param termintionCondition the termination condition
     * @param iteration the iteration number
     * @return is termination condition satisfied
     */
    boolean isConditionMet(TerminationCondition<I> termintionCondition, int iteration);

    /**
     * Gets the populator for seeding populations upon initialisation.
     * @return populator
     */
    Populator<I> getPopulator();
    
    /**
     * Seeds individuals within populations upon initialisation.
     */
    void seedIndividuals();
    
    /**
     * Runs before each round of the algorithm.
     * @param runData run data
     */
    void preRound(RunData runData);
    
    /**
     * Runs after each round of the algorithm.
     * @param runData run data
     */
    void postRound(RunData runData);

    /**
     * Writes out observation data to the run data context.
     * Is run after each round of the algorithm just after postRound is invoked.
     *
     * @param runData run data context
     */
    void writeData(RunData runData);
    
}
