package io.spring.ai.service;

import io.spring.ai.session.Request;
import io.spring.ai.session.Session;
import io.spring.ai.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class Dispatcher {

    public String prepare(HttpServletRequest httpServletRequest) {
        Session session = SessionManager.getInstance().create(new Request(httpServletRequest));
        return session.getId();
    }

    public Session fetch(String id) {
        return SessionManager.getInstance().fetch(id);
    }
}
