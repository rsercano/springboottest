package com.pribas.springboottestwithsuperpom.app.config;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
class LoggingFilter implements Filter {

  private static final String LOGGED_IN_USER_AC7 = "loggedInUsername";
  private static final String LOGGED_IN_USER_IP_AC7 = "loggedInUserIPAddress";

  @Override
  public void init(FilterConfig filterConfig) {
  }

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    setHeaders(httpRequest, httpResponse);
    chain.doFilter(httpRequest, httpResponse);
  }

  private void setHeaders(HttpServletRequest request, HttpServletResponse response) {
    MDC.put(LoggingHeaderKeys.THREAD_ID.name(), "" + Thread.currentThread().getId());

    if (request.getHeader(LoggingHeaderKeys.REQUEST_ID.name()) != null) {
      MDC.put(LoggingHeaderKeys.SESSION_ID.name(), request.getHeader(LoggingHeaderKeys.REQUEST_ID.name()));
      response.addHeader(LoggingHeaderKeys.REQUEST_ID.name(), request.getHeader(LoggingHeaderKeys.REQUEST_ID.name()));
    } else {
      String randomSessionId = UUID.randomUUID().toString();
      MDC.put(LoggingHeaderKeys.SESSION_ID.name(), randomSessionId);
      response.addHeader(LoggingHeaderKeys.REQUEST_ID.name(), randomSessionId);
    }

    if (request.getHeader(LOGGED_IN_USER_IP_AC7) != null) {
      MDC.put(LoggingHeaderKeys.IP_ADDRESS.name(), request.getHeader(LOGGED_IN_USER_IP_AC7));
    } else if (request.getRemoteAddr() != null) {
      MDC.put(LoggingHeaderKeys.IP_ADDRESS.name(), request.getRemoteAddr());
    }

    if (request.getHeader(LOGGED_IN_USER_AC7) != null) {
      MDC.put(LoggingHeaderKeys.LOGGED_IN_USER.name(), request.getHeader(LOGGED_IN_USER_AC7));
    }
  }

}
