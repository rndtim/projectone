package com.projectone.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="users") // https://stackoverflow.com/questions/43191294/hibernate-error-executing-ddl-via-jdbc-statement
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long userId;

  private String name;

  private String email;
}