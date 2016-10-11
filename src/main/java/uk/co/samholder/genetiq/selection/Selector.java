package uk.co.samholder.genetiq.selection;

import java.util.List;
import uk.co.samholder.genetiq.population.Population;

/**
 * A selector component used for sampling a subset from the population.
 *
 * These may be varied to control the strength and scaling of selective
 * pressures.
 */
public interface Selector<I extends Object> {

    public List<I> select(Population<I> population, int numToSelect);

}
