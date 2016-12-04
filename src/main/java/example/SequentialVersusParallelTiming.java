package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import uk.co.samholder.genetiq.benchmark.SphereBenchmark;
import uk.co.samholder.genetiq.fitness.FitnessFunction;
import uk.co.samholder.genetiq.interactor.Interactor;
import uk.co.samholder.genetiq.migration.UniformMigrationModel;
import uk.co.samholder.genetiq.population.MultiDemePopulationModel;
import uk.co.samholder.genetiq.population.PopulationModel;
import uk.co.samholder.genetiq.population.Populator;
import uk.co.samholder.genetiq.representation.vector.OffsetVectorMutator;
import uk.co.samholder.genetiq.representation.vector.RandomVectorPopulator;
import uk.co.samholder.genetiq.representation.vector.UniformVectorCrossover;
import uk.co.samholder.genetiq.representation.vector.Vector;
import uk.co.samholder.genetiq.round.GenerationalRoundStrategy;
import uk.co.samholder.genetiq.round.RoundStrategy;
import uk.co.samholder.genetiq.runner.genetic.GeneticAlgorithmConfiguration;
import uk.co.samholder.genetiq.runner.genetic.GeneticAlgorithmEngine;
import uk.co.samholder.genetiq.runner.genetic.SequentialGeneticAlgorithmEngine;
import uk.co.samholder.genetiq.runner.genetic.parallel.ParallelPopulationGeneticAlgorithmEngine;
import uk.co.samholder.genetiq.selection.FitnessProportionateSelector;
import uk.co.samholder.genetiq.selection.Selector;
import uk.co.samholder.genetiq.termination.IterationCountTerminationCondition;
import uk.co.samholder.genetiq.termination.TerminationCondition;
import uk.co.samholder.genetiq.variation.VariationPipeline;

/**
 * An example for running a benchmark fitness landscape problem with a genetic algorithm runner.
 * A parallel population execution engine is used in conjunction with a multi-deme model.
 * @author Sam Holder
 */
public class SequentialVersusParallelTiming extends GeneticAlgorithmConfiguration<Vector> {

    // Configuration.
    private static final int POPULATION_SIZE = 50;
    private static final int NUM_DEMES = 20;
    
    private static final int REPS = 100;
    
    private static final int DIMENSIONALITY = 5;
    
    private static final Random RANDOM = new Random();
    
    
    public static void main(String[] args) {
        // Run the algorithm.
        GeneticAlgorithmConfiguration<Vector> pipeline = new SequentialVersusParallelTiming();
        GeneticAlgorithmEngine<Vector> seq = new SequentialGeneticAlgorithmEngine<>();
        GeneticAlgorithmEngine<Vector> para = new ParallelPopulationGeneticAlgorithmEngine<>(16);
        
        long seqStartTime = System.currentTimeMillis();
        for (int i=0; i<REPS; i++) {
            seq.executePipeline(pipeline);
        }
        long seqEndTime = System.currentTimeMillis();
              
        long paraStartTime = System.currentTimeMillis();
        for (int i=0; i<REPS; i++) {
            para.executePipeline(pipeline);
        }
        long paraEndTime = System.currentTimeMillis();
        
        float seqRunTime = (float)(seqEndTime - seqStartTime) / (float)REPS / 1000f;
        float paraRunTime = (float)(paraEndTime - paraStartTime) / (float)REPS / 1000f;
        
        System.out.println("sequential time: "+seqRunTime);
        System.out.println("parallel time: "+paraRunTime);
    }
    
    @Override
    protected RoundStrategy roundStrategy() {
        return new GenerationalRoundStrategy();
    }

    @Override
    protected PopulationModel<Vector> populationModel() {
        return new MultiDemePopulationModel<>
        (
            new UniformMigrationModel(0.01, RANDOM),
            NUM_DEMES
        );
    }

    @Override
    protected TerminationCondition terminationCondition() {
        return new IterationCountTerminationCondition(1000);
    }

    @Override
    protected VariationPipeline<Vector> variationPipeline() {
        VariationPipeline<Vector> variationPipeline = new VariationPipeline<>(RANDOM);
        variationPipeline.setCombiner(new UniformVectorCrossover(RANDOM));
        variationPipeline.addMutator(new OffsetVectorMutator(RANDOM, 1f / (float)DIMENSIONALITY, DIMENSIONALITY, 0.01f));
        return variationPipeline;
    }

    @Override
    protected List<Interactor> interactors() {
        List<Interactor> interactors = new ArrayList<>();
        return interactors;
    }

    @Override
    public int populationSize() {
        return POPULATION_SIZE;
    }

    @Override
    protected Selector<Vector> selector() {
        return new FitnessProportionateSelector(RANDOM, true);
    }

    @Override
    protected FitnessFunction<Vector> fitnessFunction() {
        return new SphereBenchmark(true);
    }

    @Override
    protected Populator<Vector> populator() {
        return new RandomVectorPopulator(RANDOM, DIMENSIONALITY, 100f, 1000f);
    }
    
}
