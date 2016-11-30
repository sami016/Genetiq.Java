package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import uk.co.samholder.genetiq.benchmark.SphereBenchmark;
import uk.co.samholder.genetiq.data.ResultState;
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
public class BenchmarkExample extends GeneticAlgorithmConfiguration<Vector> {

    // Configuration.
    private static final int POPULATION_SIZE = 50;
    private static final int NUM_DEMES = 20;
    
    private static final int DIMENSIONALITY = 5;
    
    private static final Random RANDOM = new Random();
    
    
    public static void main(String[] args) {
        // Run the algorithm.
        GeneticAlgorithmConfiguration<Vector> pipeline = new BenchmarkExample();
                //new BenchmarkExample();
        GeneticAlgorithmEngine<Vector> engine = new SequentialGeneticAlgorithmEngine<>();
        ResultState data = engine.executePipeline(pipeline);
        // Get the best all time result from the algorithm.
        //IndividualFitness<Vector> ind = data.get(MemoriseBestInteractor.BEST_INDIVIDUAL_FITNESS);
        // Check whether palidrome property holds. Print details.
        //Vector result = ind.getIndividual();
        //System.out.println("best fitness: " + ind.getFitness() + " individual: " + result );
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
        return new IterationCountTerminationCondition(100000);
    }

    @Override
    protected VariationPipeline<Vector> variationPipeline() {
        VariationPipeline<Vector> variationPipeline = new VariationPipeline<Vector>(RANDOM);
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
        return new RandomVectorPopulator(RANDOM, DIMENSIONALITY, -10f, 10f);
    }
    
}
