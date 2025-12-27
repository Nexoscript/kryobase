package com.nexoscript.kryobase.api.server;

import com.nexoscript.kryobase.api.server.rest.route.IRouteHandler;
import io.javalin.Javalin;

public interface IKryobaseServer {
	void start(String hostname, int port);
	void shutdown();
	IRouteHandler getRouteHandler();
	Javalin getServer();
}
