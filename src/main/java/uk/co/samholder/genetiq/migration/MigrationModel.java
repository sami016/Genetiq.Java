/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.migration;

import uk.co.samholder.genetiq.population.PopulationModel;

/**
 *
 * @author sam
 */
public interface MigrationModel<I extends Object> {

    void performMigration(PopulationModel<I> populationModel);

}
