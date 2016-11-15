/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.co.samholder.genetiq.population.IndividualFitness;

/**
 *
 * @author sam
 */
public class ScaledSelectorBaseTest {

    public ScaledSelectorBaseTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of selectOne method, of class ScaledSelectorBase.
     */
    @Test
    public void testSelectOneAtPos() {
        System.out.println("selectOne");
        ScaledSelectorBase instance = new ScaledSelectorBaseImpl();

        List<IndividualFitness<String>> fitnessValues = new ArrayList<>();
        fitnessValues.add(new IndividualFitness<>("a", 10));
        fitnessValues.add(new IndividualFitness<>("b", 5));
        fitnessValues.add(new IndividualFitness<>("c", 10));

        Object expResult, result;

        // Test boundary cases to see if behaviour is as expected.
        expResult = "a";
        result = instance.selectOneAtPos(0.0, fitnessValues);
        assertEquals(expResult, result);

        expResult = "a";
        result = instance.selectOneAtPos(10.0, fitnessValues);
        assertEquals(expResult, result);

        expResult = "b";
        result = instance.selectOneAtPos(10.001, fitnessValues);
        assertEquals(expResult, result);

        expResult = "b";
        result = instance.selectOneAtPos(15.0, fitnessValues);
        assertEquals(expResult, result);

        expResult = "c";
        result = instance.selectOneAtPos(15.001, fitnessValues);
        assertEquals(expResult, result);

        expResult = "c";
        result = instance.selectOneAtPos(25.0, fitnessValues);
        assertEquals(expResult, result);
    }

    public class ScaledSelectorBaseImpl extends ScaledSelectorBase {

        public ScaledSelectorBaseImpl() {
            super(new Random());
        }
    }

}
