package com.nexoscript.kryobase.api.server.rest.route;

import io.javalin.http.Context;

public interface IRoute {
    void execute(Context context);
    HttpMethod method();
    String path();
}
