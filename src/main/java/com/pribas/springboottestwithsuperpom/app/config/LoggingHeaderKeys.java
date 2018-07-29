package com.pribas.springboottestwithsuperpom.app.config;

/**
 * Keeps keys which are used to transport related information between HTTP calls for log4j.
 */
enum LoggingHeaderKeys {
  SESSION_ID, THREAD_ID, LOGGED_IN_USER, IP_ADDRESS, REQUEST_ID
}
