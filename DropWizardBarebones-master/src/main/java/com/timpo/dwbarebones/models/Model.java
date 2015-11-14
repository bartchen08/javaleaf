package com.timpo.dwbarebones.models;

public class Model {

  private final String name;
  private final Integer attribute;

  public Model(String name, Integer attribute) {
    this.name = name;
    this.attribute = attribute;
  }

  public Integer getAttribute() {
    return attribute;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "POJO{" + "name=" + name + ", attribute=" + attribute + '}';
  }
}
