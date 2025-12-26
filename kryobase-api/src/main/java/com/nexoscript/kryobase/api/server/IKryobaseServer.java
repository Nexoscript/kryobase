package com.nexoscript.kryobase.api.server;

public interface IKryobaseServer {
	void start(String hostname, int port);
	void shutdown();
}
