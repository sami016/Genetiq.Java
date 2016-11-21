package uk.co.samholder.genetiq.representation.vector;

import java.util.Arrays;

/**
 * Vector representation - useful for cases where a fixed length vector of floats is required.
 * @author Sam Holder
 */
public class Vector {
    
    private final float[] values;
    
    public Vector(int dimensions) {
        values = new float[dimensions];
    }
    
    public Vector(Vector copyFrom) {
        values = Arrays.copyOf(copyFrom.values, copyFrom.getDimensions());
    }

    /**
     * Gets the value at a given loci index.
     * @param index loci index
     * @return current value
     */
    public float getValue(int index) {
        if (index < 0 || index >= values.length) {
            throw new IllegalArgumentException("index out of range");
        }
        return values[index];
    }

    /**
     * Sets the value at a given loci index.
     * @param index loci index
     * @param value new value
     */
    public void setValue(int index, float value) {
        if (index < 0 || index >= values.length) {
            throw new IllegalArgumentException("index out of range");
        }
        values[index] = value;
    }   

    /**
     * Adds an offset to the value at a given index.
     * @param index loci index
     * @param value offset amount
     */
    public void offsetValue(int index, float value) {
        if (index < 0 || index >= values.length) {
            throw new IllegalArgumentException("index out of range");
        }
        values[index] += value;
    }   
    
    /**
     * Gets the number of dimensions.
     * @return number of dimensions
     */
    public int getDimensions() {
        return values.length;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Arrays.hashCode(this.values);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vector other = (Vector) obj;
        if (!Arrays.equals(this.values, other.values)) {
            return false;
        }
        return true;
    }
    
}
