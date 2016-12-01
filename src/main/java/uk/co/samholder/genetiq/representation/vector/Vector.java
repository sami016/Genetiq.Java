package uk.co.samholder.genetiq.representation.vector;

import java.util.Arrays;

/**
 * Vector representation - useful for cases where a fixed length vector of floats is required.
 * @author Sam Holder
 */
public class Vector {
    
    private final float[] values;
    
    public Vector(int dimensions) {
        if (dimensions < 1){
            throw new IllegalArgumentException("dimensions < 1");
        }
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
    
    /**
     * Adds two vectors
     * @param otherVector other vector
     * @return sum of the two vectors
     */
    public Vector add(Vector otherVector) {
        if (otherVector.getDimensions() != getDimensions()) {
            throw new IllegalArgumentException("dimensions don't match");
        }
        Vector sum = new Vector(getDimensions());
        for (int i=0; i<getDimensions(); i++) {
            sum.setValue(i, getValue(i) + otherVector.getValue(i));
        }
        return sum;
    }
    
    /**
     * Subtracts a vector
     * @param otherVector other vector
     * @return subtraction of the input vector
     */
    public Vector subtract(Vector otherVector) {
        if (otherVector.getDimensions() != getDimensions()) {
            throw new IllegalArgumentException("dimensions don't match");
        }
        Vector sum = new Vector(getDimensions());
        for (int i=0; i<getDimensions(); i++) {
            sum.setValue(i, getValue(i) + otherVector.getValue(i));
        }
        return sum;
    }

    /**
     * Calculates the magnitude of the vector.
     * @return magnitude of the vector
     */
    public double magnitude() {
        double total = 0f;
        for (int i=0; i<getDimensions(); i++) {
            total += Math.pow(getValue(i), 2);
        }
        return Math.sqrt(total);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(values[0]);
        for (int i=1; i<values.length; i++) {
            sb.append(", ");
            sb.append(values[i]);
        }
        sb.append(")");
        
        return sb.toString();
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
