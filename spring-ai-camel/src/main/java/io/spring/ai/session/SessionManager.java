package io.spring.ai.session;

public abstract class SessionManager {

    private static SessionManager me;

    protected SessionManager() {
        me = this;
    }

    public static SessionManager getInstance() {
        return me;
    }

    public abstract Session create(Request request);

    public abstract Session fetch(String id);

    public abstract void update(Session session);
}
