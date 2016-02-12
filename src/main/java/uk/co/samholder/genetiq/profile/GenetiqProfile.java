/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.samholder.genetiq.profile;

/**
 * A profile for the algorithms that run, including options like parallel
 * utilisation.
 *
 * @author sam
 */
public class GenetiqProfile {

    private boolean useParallel = false;
    private int maxThreads = 30;

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public void setUseParallel(boolean useParallel) {
        this.useParallel = useParallel;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public boolean useParallel() {
        return useParallel;
    }

}
