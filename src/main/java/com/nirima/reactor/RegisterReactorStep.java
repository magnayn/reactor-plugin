package com.nirima.reactor;

import com.google.common.base.Strings;

import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;
import java.io.Serializable;

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
public class RegisterReactorStep  extends Builder implements Serializable, SimpleBuildStep {

  public final String scriptData;
  public final String scriptPath;

  @DataBoundConstructor
  public RegisterReactorStep(String scriptData, String scriptPath) {
    this.scriptData = scriptData;
    this.scriptPath = scriptPath;
  }
  @Override
  public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener)
      throws InterruptedException, IOException {

    ReactorJobProperty property = new ReactorJobProperty();
    property.reactorScript = scriptData;

    if( !Strings.isNullOrEmpty(scriptPath) ) {
      property.reactorScript = new FilePath(workspace, scriptPath).readToString();
    }

    run.getParent().addProperty(property);
    run.getParent().save();

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
      return "Register Reactor Script";
    }

  }
}
