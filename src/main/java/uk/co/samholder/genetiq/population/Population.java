/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.population;

import java.util.List;
import java.util.Random;
import uk.co.samholder.genetiq.selection.Selector;

/**
 * Encapsulates a fixed sized population of candidate individuals.
 *
 * @author Sam Holder
 */
public interface Population<I> extends Iterable<IndividualFitness<I>> {

    /**
     * Gets the capacity of the population.
     *
     * @return population size
     */
    public int size();

    /**
     * Gets the population index. Used by algorithms with multiple populations.s
     *
     * @return population index.
     */
    public int getPopulationIndex();
    
    /**
     * Clears the population, deleting all existing individuals.
     */
    public void clear();

    /**
     * Removes a specific individual from the population.
     *
     * @param individual individual to remove
     */
    public void removeIndividual(I individual);

    /**
     * Inserts an individual into the population.
     *
     * @param individual individual to insert
     */
    public void insertIndividual(I individual);

    /**
     * Inserts an individual into the population, with the option to postpone
     * fitness evaluation. This may be useful in cases where fitness is
     * calculated with respect to other individuals in the population.
     *
     * @param individual individual to insert
     * @param postponeFitnessCalculation flag to postpone fitness evaluation
     */
    public void insertIndividual(I individual, boolean postponeFitnessCalculation);

    /**
     * Gets whether population in initial phase - i.e. fitness values are yet to
     * be calculated.
     *
     * @return is initial phase
     */
    public boolean isInitialPhase();

    /**
     * Inserts a list of individuals into the population, with the option to
     * postpone fitness evaluation. This may be useful in cases where fitness is
     * calculated with respect to other individuals in the population.
     *
     * @param individuals individuals to insert
     * @param postponeFitnessCalculation flag to postpone fitness evaluation
     */
    public void insertIndividuals(List<I> individuals, boolean postponeFitnessCalculation);

    /**
     * Replaces an individual within the population.
     *
     * @param original individual to remove
     * @param novel individual to insert
     */
    public void replaceIndividual(I original, I novel);

    /**
     * Evaluates the fitness for all unprocessed individuals within the
     * population.
     */
    public void evaluatePostponedFitness();
    
    /**
     * Gets an individual at a specific index within the list.
     *
     * @param id index in list
     * @return individual at index
     */
    public IndividualFitness<I> getIndividualAtIndex(int id);

    /**
     * Gets the individual fitness combination for the individual with lowest
     * fitness.
     *
     * @return individual fitness combination for worst individual
     */
    public IndividualFitness<I> getWorstIndividual();

    /**
     * Gets the individual fitness combination for the individual with highest
     * fitness.
     *
     * @return individual fitness combination for best individual
     */
    public IndividualFitness<I> getBestIndividual();

    /**
     * Gets the list of individual fitness combinations for the population.
     *
     * @return list of individual fitness combinations
     */
    public List<IndividualFitness<I>> getIndividualFitnesses();


    /**
     * Gets a random individual from the population.
     *
     * @param random random number generator
     * @return randomly selected individual
     */
    public I getRandomIndividual(Random random);

    
    /**
     * Creates a sampler from the population using a given selector.
     * 
     * @param selector selector to use for sampling
     * @return population sampler using selector
     */
    public PopulationSampler<I> createSampler(Selector<I> selector);
}
