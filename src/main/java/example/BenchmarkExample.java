package example;

import java.util.List;
import uk.co.samholder.genetiq.interactor.Interactor;
import uk.co.samholder.genetiq.population.PopulationModel;
import uk.co.samholder.genetiq.representation.vector.Vector;
import uk.co.samholder.genetiq.round.RoundStrategy;
import uk.co.samholder.genetiq.runner.genetic.GeneticAlgorithmConfiguration;
import uk.co.samholder.genetiq.termination.TerminationCondition;
import uk.co.samholder.genetiq.variation.VariationPipeline;

/**
 * An example for running a benchmark fitness landscape problem with a genetic algorithm runner.
 * A parallel population execution engine is used in conjunction with a multi-deme model.
 * @author Sam Holder
 */
public class BenchmarkExample extends GeneticAlgorithmConfiguration<Vector> {

    @Override
    protected RoundStrategy roundStrategy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected PopulationModel<Vector> populationModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected TerminationCondition terminationCondition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected VariationPipeline<Vector> variationPipeline() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<Interactor> interactors() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
