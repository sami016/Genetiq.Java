package uk.co.samholder.genetiq.population;

/**
 * An abstract population model for implementing shared behaviour.
 * @author Sam Holder
 */
public abstract class AbstractPopulationModel<I> implements PopulationModel<I> {

    /**
     * Seeds the populations within the population model.
     * @param populator populator.
     */
    protected void SeedPopulations(Populator<I> populator) {
        for (Population<I> pop : this) {
            for (int i = 0; i < pop.size(); i++) {
                pop.insertIndividual(populator.getIndividual());
            }
        }
    }
    
}
