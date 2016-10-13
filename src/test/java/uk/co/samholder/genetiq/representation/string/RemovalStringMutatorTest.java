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
public class RemovalStringMutatorTest {

    /**
     * Test of mutate method, of class RemovalStringMutator.
     */
    @Test
    public void testMutate() {
        System.out.println("mutate");
        RemovalStringMutator instance;
        String result;
        String individual;
        Random random = new Random();

        instance = new RemovalStringMutator(1.0, false, random);
        individual = "abcde";
        result = instance.mutate(individual);
        System.out.println(individual + "->" + result);
        assertEquals(4, result.length());

        instance = new RemovalStringMutator(0.0, false, random);
        individual = "abcde";
        result = instance.mutate(individual);
        System.out.println(individual + "->" + result);
        assertEquals(5, result.length());

        instance = new RemovalStringMutator(1.0, false, random);
        individual = "abcde";
        result = instance.mutate(individual);
        result = instance.mutate(result);
        result = instance.mutate(result);
        result = instance.mutate(result);
        result = instance.mutate(result);
        System.out.println(individual + "->" + result);
        assertEquals(0, result.length());
    }

}
