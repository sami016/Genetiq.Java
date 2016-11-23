
package uk.co.samholder.genetiq.runner.generic;

import uk.co.samholder.genetiq.data.RunData;

/**
 * Engine for executing pipeline for general algorithms.
 * @author Sam Holder
 */
public interface ExecutionEngine<TPipeline extends AlgorithmConfiguration> {
    
    public RunData executePipeline(TPipeline pipeline);
    
}
