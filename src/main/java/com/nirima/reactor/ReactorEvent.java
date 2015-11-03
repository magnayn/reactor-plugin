package com.nirima.reactor;

import com.google.common.base.Objects;

import org.jenkinsci.plugins.scriptsecurity.sandbox.whitelists.Whitelisted;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by magnayn on 02/11/15.
 */
public class ReactorEvent implements Serializable {

  @Whitelisted
  public String eventName;

  @Whitelisted
  public String jobName;

  @Whitelisted
  public String jobFullName;

  @Whitelisted
  public Integer buildNumber;

  @Whitelisted
  public Map<String, String> eventProperties = new HashMap<>();

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
        .add("eventName", eventName)
        .add("jobName", jobName)
        .add("jobFullName", jobFullName)
        .add("buildNumber", buildNumber)
        .add("eventProperties", eventProperties)
        .toString();
  }
}
