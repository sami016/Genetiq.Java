
package uk.co.samholder.genetiq.runner.genetic;

import java.util.List;
import uk.co.samholder.genetiq.interactor.Interactor;
import uk.co.samholder.genetiq.population.PopulationModel;
import uk.co.samholder.genetiq.round.RoundStrategy;
import uk.co.samholder.genetiq.runner.generic.Pipeline;
import uk.co.samholder.genetiq.termination.TerminationCondition;
import uk.co.samholder.genetiq.variation.VariationPipeline;

/**
 * Stores contextual information for a algorithm configuration.
 * @author Sam Holder
 */
public abstract class GeneticAlgorithmConfiguration<I> implements Pipeline {

    /**
     * Implements the round strategy, which defines how rounds are handled.
     * Usually set to a generational or steady state strategy.
     *
     * @return round strategy
     */
    protected abstract RoundStrategy roundStrategy();

    /**
     * Implements the population model, which defines how the population is
     * structured. The simplest example of this is the single population model.
     *
     * @return population model.
     */
    protected abstract PopulationModel<I> populationModel();


    /**
     * Implements the termination condition. Once this condition is satisfied
     * the algorithm will terminate.
     *
     * @return termination condition
     */
    protected abstract TerminationCondition terminationCondition();

    
    /**
     * Implements the variation pipeline. This encapsulates variation operators including
     * mutation and crossover.
     * @return variation engine
     */
    protected abstract VariationPipeline<I> variationPipeline();
    
    /**
     * Implements the interactors list, a list of objects used view the
     * algorithm state whilst running. This can be used to extract data for
     * graph plots, logging, or viewing the current best individual at a given
     * time.
     *
     *
     * @return interactor list
     */
    protected abstract List<Interactor> interactors();

    @Override
    public boolean validate() {
        return roundStrategy() != null
                && terminationCondition() != null
                && populationModel() != null
                && interactors() != null;
    }
    
}
