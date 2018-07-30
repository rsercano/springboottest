package com.pribas.springboottestwithsuperpom.app.handler.impl;

import com.pribas.springboottestwithsuperpom.app.beans.Post;
import com.pribas.springboottestwithsuperpom.app.handler.ExampleConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Component
class ExampleConsumerImpl implements ExampleConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExampleConsumerImpl.class);

  private final RestTemplate restTemplate;

  @Autowired
  public ExampleConsumerImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public Post getPost(@RequestBody Post requestedPost) {
    Post post = restTemplate.postForObject("/posts", requestedPost, Post.class);
    LOGGER.info("post: " + post);

    return post;
  }

}
