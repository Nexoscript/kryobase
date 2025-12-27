package com.nexoscript.kryobase.server;

import com.nexoscript.kryobase.api.server.rest.route.IRoute;
import com.nexoscript.kryobase.api.server.rest.route.IRouteHandler;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class RouteHandler implements IRouteHandler {
    private List<IRoute> routes;

    public RouteHandler() {
        this.routes = new ArrayList<>();
    }

    @Override
    public void handleRoutes(Javalin javalin) {
        for (IRoute route : this.routes) {
            switch (route.method()) {
                case GET -> javalin.get(route.path(), route::execute);
                case POST -> javalin.post(route.path(), route::execute);
                case PUT -> javalin.put(route.path(), route::execute);
                case PATCH -> javalin.patch(route.path(), route::execute);
                case BEFORE -> javalin.before(route.path(), route::execute);
                case DELETE -> javalin.delete(route.path(), route::execute);
            }
        }
    }

    @Override
    public void register(IRoute route) {
        this.routes.add(route);
    }

    @Override
    public void register(IRoute... routes) {
        for (IRoute route : routes) {
            this.register(route);
        }
    }

    @Override
    public void clear() {
        this.routes.clear();
    }

    @Override
    public void unregister(IRoute route) {
        this.routes.remove(route);
    }

    @Override
    public List<IRoute> getRegisteredRoutes() {
        return this.routes;
    }
}
