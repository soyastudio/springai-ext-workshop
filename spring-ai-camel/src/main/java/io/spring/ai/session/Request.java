package io.spring.ai.session;

import jakarta.servlet.http.HttpServletRequest;

public class Request {

    private final HttpServletRequest httpServletRequest;

    public Request(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
}
