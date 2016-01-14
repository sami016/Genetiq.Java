package uk.co.samholder.genetiq.selection;

import java.util.List;
import uk.co.samholder.genetiq.population.Population;

/**
 *
 * @author sam
 */
public interface Selector<I extends Object> {

    public List<I> select(Population<I> population, int numToSelect);

}
