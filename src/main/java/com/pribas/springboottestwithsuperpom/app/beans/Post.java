package com.pribas.springboottestwithsuperpom.app.beans;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;

/**
 * Post beans defined; <a href="https://github.com/typicode/jsonplaceholder#how-to">here</a>.
 * <p>
 * { id: 101, title: 'foo', body: 'bar', userId: 1 }
 * </p>
 */

@Value
@Builder
@JsonDeserialize(builder = Post.PostBuilder.class)
public class Post {

  private int id;
  private String title;
  private String body;
  private int userId;

}
