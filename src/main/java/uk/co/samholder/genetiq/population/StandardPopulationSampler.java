package uk.co.samholder.genetiq.population;

import java.util.List;
import uk.co.samholder.genetiq.selection.Selector;

/**
 * Standard population sampler.
 * @author Sam Holder
 */
public class StandardPopulationSampler<I> implements PopulationSampler<I> {

    private final Population<I> population;
    private final Selector<I> selector;

    public StandardPopulationSampler(Population<I> population, Selector<I> selector) {
        this.population = population;
        this.selector = selector;
    }
    
    @Override
    public List<I> sample(int numberOfIndividuals) {
        return selector.select(population, numberOfIndividuals);
    }
    
}
