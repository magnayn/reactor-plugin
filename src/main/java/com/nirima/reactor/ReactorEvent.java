package com.nirima.reactor;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by magnayn on 02/11/15.
 */
public class ReactorEvent implements Serializable {
  public String eventName;

  public String jobName;
  public String jobFullName;
  public Integer buildNumber;
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
