package com.pribas.springboottestwithsuperpom.app.handler;

import com.pribas.springboottestwithsuperpom.app.beans.Post;

/**
 * Consumes <a src="https://jsonplaceholder.typicode.com/">this example</a>.
 * <p>
 * Just for demonstration of RestTemplate
 * </p>
 */
public interface ExampleConsumer {

  Post getPost(Post requestedPost);
}
