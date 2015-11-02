package com.nirima.reactor;

import org.slf4j.Logger;

import hudson.Plugin;
import hudson.model.BuildableItem;
import hudson.model.Job;
import hudson.model.Project;
import hudson.model.Run;
import hudson.plugins.groovy.StringScriptSource;
import jenkins.model.Jenkins;

/**
 * Created by magnayn on 02/11/15.
 */
public class PluginImpl extends Plugin {
  private static final Logger logger = org.slf4j.LoggerFactory.getLogger(PluginImpl.class);


  /**
   * What to call this plug-in to humans.
   */
  public static final String DISPLAY_NAME = "Reactor Plugin";

  private static PluginImpl instance;

  /**
   * Constructor.
   */
  public PluginImpl() {
    instance = this;
  }

  /**
   * Returns this singleton instance.
   *
   * @return the singleton.
   */
  public static PluginImpl getInstance() {
    return instance;
  }

  public void fireEvent(Run<?,?> run, ReactorEvent event) {
    for (Job j : Jenkins.getInstance().getAllItems(Job.class)) {
      ReactorJobProperty rjp = (ReactorJobProperty) j.getProperty(ReactorJobProperty.class);
      if (rjp != null && !run.getParent().equals(j) ) {
        StringScriptSource scriptSource = new StringScriptSource(rjp.reactorScript);

        ReactorGroovy rg = new ReactorGroovy(scriptSource);

        try {
          if (rg.perform(event)) {

              ((BuildableItem)j).scheduleBuild(new ReactorCause(event));

          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }

    }
  }




}
