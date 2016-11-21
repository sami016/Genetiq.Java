package uk.co.samholder.genetiq.representation.vector;

import uk.co.samholder.genetiq.util.GenerationRange;
import java.util.Random;
import uk.co.samholder.genetiq.population.Populator;

/**
 * A random vector populator.
 * @author Sam Holder
 */
public class RandomVectorPopulator implements Populator<Vector> 
{
    private Random random;
    private GenerationRange[] generationRanges;
    
    public RandomVectorPopulator(Random random, int dimensionality) {
        generationRanges = new GenerationRange[dimensionality];
        this.random = random;
    }
    
    public RandomVectorPopulator(Random random, int dimensionality, float minGeneratedValue, float maxGeneratedValue) {
        this(random, dimensionality);
        for (int i=0; i<dimensionality; i++) {
            generationRanges[i] = new GenerationRange(minGeneratedValue, maxGeneratedValue);
        }
    }

    public RandomVectorPopulator(Random random, GenerationRange ... ranges) {
        this(random, ranges.length);
        generationRanges = ranges;
    }
    
    @Override
    public Vector getIndividual() {
        Vector vector = new Vector(generationRanges.length);
        for (int i=0; i<generationRanges.length; i++) {
            vector.setValue(i, generationRanges[i].Generate(random));
        }
        return vector;
    }
}

