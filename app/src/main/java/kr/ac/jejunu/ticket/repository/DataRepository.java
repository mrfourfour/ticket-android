package kr.ac.jejunu.ticket.repository;

import kr.ac.jejunu.ticket.application.GetTokenable;

public abstract class DataRepository {

    protected String accessToken;

    protected DataRepository(GetTokenable getTokenable) {
        this.accessToken = getTokenable.getToken();
    }
}
