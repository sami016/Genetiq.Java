/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.population;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import uk.co.samholder.genetiq.data.ResultState;
import uk.co.samholder.genetiq.termination.TerminationCondition;

/**
 * A population model for a single population.
 *
 * @author Sam Holder
 */
public class SinglePopulationModel<I> extends AbstractPopulationModel<I> {

    // The population at the end of the last round.
    public static final String KEY_POPULATION = "SinglePopulationModel_population";
    // The optimum individual fitness combination at the end of the last round.
    public static final String KEY_OPTIMUM = "SinglePopulationModel_optimum";

    private Population<I> population;

    @Override
    public void Initialise(Supplier<Population<I>> populationSupplier, Populator<I> populator) {
        population = populationSupplier.get();
        SeedPopulations(populator);
    }

    @Override
    public Iterator<Population<I>> iterator() {
        final List<Population<I>> list = new ArrayList<>(1);
        list.add(population);
        return list.iterator();
    }

    @Override
    public void writeData(ResultState<I> runData) {
        List<Population<I>> populationList = new ArrayList<>();
        populationList.add(population);
        runData.setPopulations(populationList);
        runData.setBestIndividual(population.getBestIndividual());
    }

    @Override
    public boolean isConditionMet(TerminationCondition<I> termintionCondition, int iteration) {
        return termintionCondition.conditionMet(population, iteration);
    }

    @Override
    public void preRound(ResultState runData) {
    }
    

    @Override
    public void postRound(ResultState runData) {
    }
    
}
