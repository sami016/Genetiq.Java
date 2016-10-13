/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.representation.string;

import java.util.Random;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author sam
 */
public class InsertionStringMutatorTest {

    /**
     * Test of mutate method, of class InsertionStringMutator.
     */
    @Test
    public void testMutate() {
        System.out.println("mutate");
        InsertionStringMutator instance = null;
        String individual;
        String result;
        Random random = new Random();

        instance = new InsertionStringMutator(1.0, false, random);
        individual = "abcde";
        result = instance.mutate(individual);
        assertEquals(6, result.length());
    }

}
