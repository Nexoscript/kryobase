package com.nexoscript.kryobase.server.routes;

import com.nexoscript.kryobase.api.server.rest.route.HttpMethod;
import com.nexoscript.kryobase.api.server.rest.route.IRoute;
import io.javalin.http.Context;

public class InfoRoute implements IRoute {
    @Override
    public void execute(Context context) {
        context.json("Server is running.").status(200);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.GET;
    }

    @Override
    public String path() {
        return "/api/v1/info";
    }
}
