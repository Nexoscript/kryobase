package com.nexoscript.kryobase.api.server.rest.route;

import io.javalin.Javalin;

import java.util.List;

public interface IRouteHandler {
    void handleRoutes(Javalin javalin);
    void register(IRoute route);
    void register(IRoute... routes);
    void clear();
    void unregister(IRoute route);
    List<IRoute> getRegisteredRoutes();
}
