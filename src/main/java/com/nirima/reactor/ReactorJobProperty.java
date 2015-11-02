package com.nirima.reactor;

import net.sf.json.JSONObject;

import org.kohsuke.stapler.StaplerRequest;

import java.io.Serializable;

import hudson.Extension;
import hudson.model.Action;
import hudson.model.Job;
import hudson.model.JobProperty;
import hudson.model.JobPropertyDescriptor;

/**
 * Created by magnayn on 02/11/15.
 */
public class ReactorJobProperty extends JobProperty<Job<?, ?>>
    implements Action, Serializable {

  public String reactorScript;

  @Override
  public String getIconFileName() {

    return null;
  }

  @Override
  public String getDisplayName() {
    return null;
  }

  @Override
  public String getUrlName() {
    return null;
  }

  @Extension
  public static class DescriptorImpl extends JobPropertyDescriptor {
    @Override
    public boolean isApplicable(Class<? extends Job> jobType) {
      return true;
    }

    @Override
    public JobProperty<?> newInstance(StaplerRequest req,
                                      JSONObject formData) throws FormException {
      ReactorJobProperty r = new ReactorJobProperty();

      return r;
    }

    @Override
    public String getDisplayName() {
      return "Reactor Job Property";
    }
  }
}
