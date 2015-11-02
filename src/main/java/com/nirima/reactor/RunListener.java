package com.nirima.reactor;

import hudson.model.Run;
import hudson.model.TaskListener;

/**
 * Listen for runs completing.
 */
public class RunListener extends hudson.model.listeners.RunListener<Run<?,?>> {

  @Override
  public void onCompleted(Run<?, ?> run, TaskListener listener) {
    super.onCompleted(run, listener);
    // A Build has completed.
  }
}
