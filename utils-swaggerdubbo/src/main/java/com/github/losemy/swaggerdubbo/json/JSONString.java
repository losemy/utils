package com.github.losemy.swaggerdubbo.json;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;

public class JSONString {
    private final String value;

    public JSONString(String value) {
      this.value = value;
    }

    @JsonValue
    @JsonRawValue
    public String value() {
      return value;
    }

    public String getValue() {
        return value;
    }
    
    
  }