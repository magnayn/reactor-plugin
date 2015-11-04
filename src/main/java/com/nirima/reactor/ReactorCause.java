package com.nirima.reactor;

import org.jenkinsci.plugins.scriptsecurity.sandbox.whitelists.Whitelisted;

import java.io.Serializable;

import hudson.model.Cause;

/**
 * Created by magnayn on 02/11/15.
 */
public class ReactorCause extends Cause implements Serializable {

  @Whitelisted
  public final ReactorEvent event;

  public ReactorCause(ReactorEvent event) {
    this.event = event;
  }

  @Override
  public String getShortDescription() {
    return "Triggered by Reactor";
  }
}
