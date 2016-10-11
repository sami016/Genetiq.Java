/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.mutator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uk.co.samholder.genetiq.mutator.structure.Structure;

/**
 * A class for applying mutations across a range of structured data
 * representations.
 */
public class MutationEngine<I> implements Mutator<I> {

    private Map<Structure<I, ?>, List<Mutator<?>>> structureMutators;

    public MutationEngine() {
        structureMutators = new HashMap<>();
    }

    /**
     * Adds a type of substructure, and the ways in which it can be mutated.
     *
     * @param structure the structure.
     * @param mutators the mutators.
     */
    public void structureMutation(Structure<I, ?> structure, Mutator<?>... mutators) {
        structureMutators.put(structure, Arrays.asList(mutators));
    }

    // Note: modifies the individual passed in.
    @Override
    public I mutate(I individual) {
        // Iterate through structure types.
        for (Structure<I, ?> structureType : structureMutators.keySet()) {
            List<?> units = structureType.get(individual);
            // Mutate all units for the structure type.
            for (Mutator mutator : structureMutators.get(structureType)) {
                for (Object a : units) {
                    mutator.mutate(a);
                }
            }
        }
        return individual;
    }

}
