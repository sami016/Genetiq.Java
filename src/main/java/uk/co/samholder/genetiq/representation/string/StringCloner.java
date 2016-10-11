/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.representation.string;

import uk.co.samholder.genetiq.mutator.Cloner;

/**
 * Clones String individuals.
 *
 * @author Sam Holder
 */
public class StringCloner implements Cloner<String> {

    @Override
    public String mutate(String individual) {
        return new String(individual);
    }

}
