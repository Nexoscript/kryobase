package com.nexoscript.kryobase.server;

import com.nexoscript.kryobase.api.server.IKryobaseServer;
import com.nexoscript.kryobase.api.server.rest.route.IRouteHandler;
import io.javalin.Javalin;

public class KryobaseServer implements IKryobaseServer {
	private Javalin server;
	private RouteHandler routeHandler;

	public KryobaseServer() {
		this.routeHandler = new RouteHandler();
		System.setProperty("org.slf4j.simpleLogger.log.io.javalin", "error");
		System.setProperty("org.slf4j.simpleLogger.log.org.eclipse.jetty", "error");
		this.server = Javalin.create(config -> {
			config.showJavalinBanner = false;
		});
	}

	@Override
	public void start(String hostname, int port) {
		this.routeHandler.handleRoutes(this.server);
		this.server.start(hostname, port);
	}

	@Override
	public void shutdown() {
		this.routeHandler.clear();
		this.server.stop();
	}

	@Override
	public Javalin getServer() {
		return server;
	}

	@Override
	public IRouteHandler getRouteHandler() {
		return routeHandler;
	}
}
