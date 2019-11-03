package kr.ac.jejunu.ticket.application;

import android.app.Application;

import kr.ac.jejunu.ticket.data.TokenData;

public class AppApplication extends Application implements GetTokenable {

    public void setTokenData(TokenData tokenData) {
        this.tokenData = tokenData;
    }

    private TokenData tokenData;

    @Override
    public String getToken() {
        return tokenData.getAccess_token();
    }
}
