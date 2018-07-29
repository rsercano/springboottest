package com.pribas.springboottestwithsuperpom.app.handler.impl;

import com.pribas.springboottestwithsuperpom.app.beans.Post;
import com.pribas.springboottestwithsuperpom.app.handler.ExampleConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Component
class ExampleConsumerImpl implements ExampleConsumer {

  private final RestTemplate restTemplate;

  @Autowired
  public ExampleConsumerImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @PostMapping("/availability")
  public Post getPost(@RequestBody Post requestedPost) {
    return null;
  }

}
