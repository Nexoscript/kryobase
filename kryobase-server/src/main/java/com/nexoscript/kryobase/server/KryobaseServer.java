package com.nexoscript.kryobase.server;

import com.nexoscript.kryobase.api.server.IKryobaseServer;
import io.javalin.Javalin;

public class KryobaseServer implements IKryobaseServer {
	private Javalin server;
	private RouteHandler routeHandler;

	public KryobaseServer() {
		this.server = Javalin.create(config -> {
			config.showJavalinBanner = false;
		});
		this.routeHandler = new RouteHandler();
		this.routeHandler.handleRoutes(this.server);
	}

	@Override
	public void start(String hostname, int port) {
		this.server.start(hostname, port);
		System.out.println("Server started on " + hostname + ":" + port);
	}

	@Override
	public void shutdown() {
		System.out.println( "Shutting down server...");
		this.routeHandler.clear();
		this.server.stop();
	}

	public Javalin getServer() {
		return server;
	}

	public RouteHandler getRouteHandler() {
		return routeHandler;
	}
}
