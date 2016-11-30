
package uk.co.samholder.genetiq.runner.generic;

import uk.co.samholder.genetiq.data.ResultState;

/**
 * Engine for executing pipeline for general algorithms.
 * @author Sam Holder
 */
public interface ExecutionEngine<TPipeline extends AlgorithmConfiguration> {
    
    public ResultState executePipeline(TPipeline pipeline);
    
}
