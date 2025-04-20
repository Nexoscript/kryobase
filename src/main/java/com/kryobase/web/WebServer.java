package com.kryobase.web;

import com.sun.net.httpserver.HttpServer;
import lombok.Getter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class WebServer {
    @Getter
    private int port;
    private HttpServer httpServer;
    @Getter
    private List<Request> requests;

    public WebServer(int port) {
        try {
            this.requests = new ArrayList<>();
            this.port = port;
            this.httpServer = HttpServer.create(new InetSocketAddress(this.port), 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public WebServer() {
        try {
            this.requests = new ArrayList<>();
            this.port = 8080;
            this.httpServer = HttpServer.create(new InetSocketAddress(this.port), 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void get(String path, Consumer<HttpContext> ctx) {
        this.requests.add(new Request(path, "GET", ctx));
    }

    public void post(String path, Consumer<HttpContext> ctx) {
        this.requests.add(new Request(path, "POST", ctx));
    }

    public void put(String path, Consumer<HttpContext> ctx) {
        this.requests.add(new Request(path, "PUT", ctx));
    }

    public void delete(String path, Consumer<HttpContext> ctx) {
        this.requests.add(new Request(path, "DELETE", ctx));
    }

    public void update(String path, Consumer<HttpContext> ctx) {
        this.requests.add(new Request(path, "UPDATE", ctx));
    }

    private void handleServer() {
        this.requests.forEach(request -> this.httpServer.createContext(request.path(), handle ->  {
            System.out.println("create Context");
           if(handle.getRequestMethod().equals(request.requestType().toUpperCase())) {
               request.ctx().accept(new HttpContext(handle));
           } else {
               System.out.println("Trigger Consumer!");
           }
        }));
    }

    public void start() {
        handleServer();
        this.httpServer.setExecutor(null);
        this.httpServer.start();
    }

    public void stop() {
        this.httpServer.stop(0);
    }

    public HttpServer getHttpServer() {
        return httpServer;
    }

}
