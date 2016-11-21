package uk.co.samholder.genetiq.representation.vector;

import java.util.List;
import java.util.Random;
import uk.co.samholder.genetiq.variation.Combiner;

/**
 *
 * @author Sam Holder
 */
public class UniformVectorCrossover implements Combiner<Vector> {

    private final Random random;

    public UniformVectorCrossover(Random random) {
        this.random = random;
    }
    
    @Override
    public int getNumberToCombine() {
        return 2;
    }

    private float selectAtLoci(List<Vector> individuals, int loci) {
        return random.nextBoolean() 
                ? individuals.get(0).getValue(loci)
                : individuals.get(1).getValue(loci);
    }
    
    @Override
    public Vector combine(List<Vector> individuals) {
        Vector vec = new Vector(individuals.get(0).getDimensions());
        for (int i=0; i<vec.getDimensions(); i++) {
            vec.setValue(i, selectAtLoci(individuals, i));
        }
        return vec;
    }
    
}
