/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.populator.string;

import java.util.Random;
import uk.co.samholder.genetiq.populator.Populator;

/**
 *
 * @author sam
 */
public class StringPopulator implements Populator<String> {

    private Random random;
    private int length;

    public StringPopulator(Random random, int length) {
        this.random = random;
        this.length = length;
    }

    @Override
    public String getIndividual() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append((char) (random.nextInt(128)));
        }
        return stringBuilder.toString();
    }

}
