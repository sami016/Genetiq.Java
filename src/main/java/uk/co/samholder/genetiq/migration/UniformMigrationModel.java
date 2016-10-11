/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.migration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import uk.co.samholder.genetiq.population.Population;
import uk.co.samholder.genetiq.population.PopulationModel;

/**
 *
 * @author sam
 */
public class UniformMigrationModel<I extends Object> implements MigrationModel<I> {

    private final Random random;
    private final double migrationProbability;

    public UniformMigrationModel(double migrationProbability, Random random) {
        this.migrationProbability = migrationProbability;
        this.random = random;
    }

    @Override
    public void performMigration(PopulationModel<I> populationModel) {
        boolean performMigration = new Random().nextDouble() < migrationProbability;
        if (!performMigration) {
            return;
        }

        List<I> migrants = new ArrayList<>();
        for (Population<I> population : populationModel) {
            // Extract a migrant from each population.
            I migrant = population.getRandomIndividual(random);
            population.removeIndividual(migrant);
            migrants.add(migrant);
        }

        // Shuffle, and add one of each to a population.
        Collections.shuffle(migrants, random);
        int i = 0;
        for (Population<I> population : populationModel) {
            population.insertIndividual(migrants.get(i));
            i++;
        }
    }

}
