/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sam
 */
public class RunData {

    private Map<String, Object> map = new HashMap<>();

    public <T> void set(String key, T value) {
        map.put(key, value);
    }

    public <T> T get(String key) {
        return (T) map.get(key);
    }

    @Override
    public String toString() {
        return Arrays.toString(map.keySet().toArray());
    }

}
