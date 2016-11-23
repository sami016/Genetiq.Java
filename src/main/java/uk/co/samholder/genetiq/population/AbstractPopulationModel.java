package uk.co.samholder.genetiq.population;

/**
 * An abstract population model for implementing shared behaviour.
 * @author Sam Holder
 */
public abstract class AbstractPopulationModel<I> implements PopulationModel<I> {

    @Override
    public void seedIndividuals() {
        for (Population<I> pop : this) {
            for (int i = 0; i < getPopulationUnitSize(); i++) {
                pop.insertIndividual(getPopulator().getIndividual());
            }
        }
    }
    
    
}
