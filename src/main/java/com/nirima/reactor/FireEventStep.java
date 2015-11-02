package com.nirima.reactor;

import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import jenkins.tasks.SimpleBuildStep;

/**
 * Register a script.
 */
public class FireEventStep extends Builder implements Serializable, SimpleBuildStep {

  public String eventName;
  public String properties;

  @DataBoundConstructor
  public FireEventStep(String eventName, String properties) {
    this.eventName = eventName;
    this.properties = properties;
  }

  private Map<String, String> makeProperties(String properties) {
    Map<String,String> p = new HashMap<>();
    for(String s : properties.split("\n") ) {
      try {
        String[] s2 = s.split("=");

        p.put(s2[0], s2[1]);
      }
      catch(Exception ex) {
        ex.printStackTrace();
      }
    }
    return p;
  }

  @Override
  public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener)
      throws InterruptedException, IOException {

    ReactorEvent event = new ReactorEvent();
    event.eventName = eventName;
    event.buildNumber = run.getNumber();
    event.jobName = run.getParent().getName();
    event.jobFullName = run.getParent().getFullName();
    event.eventProperties = makeProperties(properties);

    PluginImpl.getInstance().fireEvent(run, event);
  }

  @Override
  public DescriptorImpl getDescriptor() {
    return (DescriptorImpl) super.getDescriptor();
  }

  @Extension
  public static class DescriptorImpl extends BuildStepDescriptor<Builder> {

    @Override
    public boolean isApplicable(Class<? extends AbstractProject> jobType) {
      return true;
    }

    @Override
    public String getDisplayName() {
      return "Fire Reactor Event";
    }

  }
}
