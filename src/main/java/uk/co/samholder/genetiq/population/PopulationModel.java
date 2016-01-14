/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.population;

import uk.co.samholder.genetiq.control.TerminationCondition;
import uk.co.samholder.genetiq.data.RunData;
import uk.co.samholder.genetiq.round.RoundStrategy;

/**
 *
 * @author sam
 */
public interface PopulationModel<I extends Object> extends Iterable<Population<I>> {

    int getPopulationUnitSize();

    void writeData(RunData runData);

    void doPerformRound(RoundStrategy roundStrategy, RunData runData);

    boolean isConditionMet(TerminationCondition<I> termintionCondition, int iteration);

}
