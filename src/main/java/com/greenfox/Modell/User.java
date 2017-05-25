package com.greenfox.Modell;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Adam on 2017. 05. 17..
 */

@Entity
@Table(name = "Users")
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String name;

  public User() {
  }

  public User(String name) {
    this.name = name;
  }

  public String toString() {
    return name;
  }

}
