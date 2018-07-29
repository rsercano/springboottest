package com.pribas.springboottestwithsuperpom.app.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;

/**
 * Post beans defined; <a href="https://github.com/typicode/jsonplaceholder#how-to">here</a>.
 * <p>
 * { id: 101, title: 'foo', body: 'bar', userId: 1 }
 * </p>
 */

@Builder
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {

  private int id;
  private String title;
  private String body;
  private int userId;
}
