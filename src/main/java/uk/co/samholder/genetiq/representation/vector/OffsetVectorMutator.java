package uk.co.samholder.genetiq.representation.vector;

import java.util.Random;
import uk.co.samholder.genetiq.variation.Mutator;

/**
 * A random offset vector mutator.
 * @author Sam Holder
 */
public class OffsetVectorMutator implements Mutator<Vector> {
    
    private final Random random;
    private final float lociOffsetProbablity;
    private final float[] offsets;
    
    public OffsetVectorMutator(Random random, float lociOffsetProbability, int dimensions, float offset) {
        this.random = random;
        this.lociOffsetProbablity = lociOffsetProbability;
        offsets = new float[dimensions];
        for (int i=0; i<dimensions; i++) {
            offsets[i] = offset;
        }
    }
    
    public OffsetVectorMutator(Random random, float lociOffsetProbability, float ... offsets) {
        this.random = random;
        this.lociOffsetProbablity = lociOffsetProbability;
        this.offsets = offsets;
    }

    protected float getMaximumOffset(int index) {
        return offsets[index];
    }
    
    @Override
    public Vector mutate(Vector individual) {
        Vector mutant = new Vector(individual);
        for (int i=0; i<mutant.getDimensions(); i++) {
            if (random.nextFloat() < lociOffsetProbablity) {
                mutant.setValue(i, mutant.getValue(i) + getMaximumOffset(i) * random.nextFloat());
            }
        }
        return mutant;
    }
    
}
