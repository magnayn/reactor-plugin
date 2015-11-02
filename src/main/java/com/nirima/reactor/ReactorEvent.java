package com.nirima.reactor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by magnayn on 02/11/15.
 */
public class ReactorEvent implements Serializable {
  public String eventName;

  public String jobName;
  public Integer buildNumber;
  public Map<String, String> eventProperties = new HashMap<>();
}
