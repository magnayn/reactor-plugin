package com.nirima.reactor;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import org.codehaus.groovy.control.CompilerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import hudson.FilePath;
import hudson.Util;
import hudson.model.Hudson;
import hudson.plugins.groovy.AbstractGroovy;
import hudson.plugins.groovy.Groovy;
import hudson.plugins.groovy.ScriptSource;
import hudson.plugins.groovy.StringScriptSource;

/**
 * Created by magnayn on 02/11/15.
 */
public class ReactorGroovy extends AbstractGroovy {

  private static final Logger logger = LoggerFactory.getLogger(ReactorGroovy.class);

  Object output;
  String bindings;

  public ReactorGroovy(ScriptSource scriptSource) {
    super(scriptSource);
  }

  public boolean perform(ReactorEvent event) throws IOException, InterruptedException {
    //String classpath = null;

    CompilerConfiguration compilerConfig = new CompilerConfiguration();
  //  if(this.classpath != null) {
  //    compilerConfig.setClasspath(this.classpath);
  //  }

    ClassLoader cl = Hudson.getInstance().getPluginManager().uberClassLoader;
    if(cl == null) {
      cl = Thread.currentThread().getContextClassLoader();
    }

    Binding map = new Binding(parseProperties(this.bindings));
    map.setProperty("event", event);


    GroovyShell
        shell = new GroovyShell(cl, map, compilerConfig);

    shell.setVariable("out", System.out);

    this.output = shell.evaluate(this.getScriptSource().getScriptStream(new FilePath(new File(""))));

    if(this.output instanceof Boolean) {
      return ((Boolean)this.output).booleanValue();
    } else {
      if(this.output != null) {
        logger.debug("Script returned: " + this.output);
      }

      return this.output instanceof Number?((Number)this.output).intValue() == 0:true;
    }
  }
}
