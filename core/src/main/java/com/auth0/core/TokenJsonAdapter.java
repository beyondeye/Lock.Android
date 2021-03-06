package com.auth0.core;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/**
 * Created by daely on 5/13/2016.
 */
public class TokenJsonAdapter {
    @FromJson
    Token fromJson(TokenJson t) {
        if(t==null||t.isEmpty())
            return new Token(); //empty
        return new Token(t.idToken,t.accessToken,t.type,t.refreshToken);
    }
    @ToJson
    TokenJson toJson(Token t) {
        return  t;
    }
}
