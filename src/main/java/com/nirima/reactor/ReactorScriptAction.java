package com.nirima.reactor;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.export.ExportedBean;

import java.io.Serializable;

import hudson.Extension;
import hudson.model.Action;
import hudson.model.Describable;
import hudson.model.Descriptor;
import jenkins.model.Jenkins;

/**
 * Created by magnayn on 10/01/2014.
 */
@ExportedBean
public class ReactorScriptAction implements Action, Serializable, Cloneable,
                                          Describable<ReactorScriptAction> {

  public final String containerHost;
  public final String containerId;

  public final String taggedId;

  public final String remoteFsMapping;

  @DataBoundConstructor
  public ReactorScriptAction(String containerHost, String containerId, String taggedId, String remoteFsMapping) {
    this.containerHost = containerHost;
    this.containerId = containerId;
    this.taggedId = taggedId;
    this.remoteFsMapping = remoteFsMapping;
  }

  public String getIconFileName() {
    return "/plugin/reactor-plugin/images/24x24/reactor.png";
  }

  public String getDisplayName() {
    return "Reactor";
  }

  public String getUrlName() {
    return "docker";
  }

  public DescriptorImpl getDescriptor() {
    return (DescriptorImpl) Jenkins.getInstance().getDescriptorOrDie(getClass());
  }

  /**
   * Just for assisting form related stuff.
   */
  @Extension
  public static class DescriptorImpl extends Descriptor<ReactorScriptAction> {
    public String getDisplayName() {
      return "Reactor Script";
    }
  }
}
