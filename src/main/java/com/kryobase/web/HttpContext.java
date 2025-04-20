package com.kryobase.web;

import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class HttpContext {
    private HttpExchange exchange;

    public HttpContext(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public void json(String json) {
        try {
            this.exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, json.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(json.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void file(File file) {
        try {
            if (!file.exists() || !file.isFile()) {
                String msg = "File not Found.";
                exchange.sendResponseHeaders(404, msg.length());
                exchange.getResponseBody().write(msg.getBytes());
                exchange.getResponseBody().close();
                return;
            }

            exchange.getResponseHeaders().set("Content-Type", Files.probeContentType(file.toPath()));
            exchange.getResponseHeaders().set("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            exchange.sendResponseHeaders(200, file.length());

            try (OutputStream os = exchange.getResponseBody();
                 InputStream fis = new FileInputStream(file)) {
                fis.transferTo(os);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void html(String html) {
        try {
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, html.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(html.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void html(File htmlFile) {
        try {
            String html = Files.readString(htmlFile.toPath(), StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, html.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(html.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void xml(String xml) {
        try {
            exchange.getResponseHeaders().set("Content-Type", "application/xml; charset=UTF-8");
            exchange.sendResponseHeaders(200, xml.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(xml.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> parseQuery() {
        String query = this.exchange.getRequestURI().getQuery();
        Map<String, String> params = new HashMap<>();
        if (query == null || query.isEmpty()) return params;

        for (String pair : query.split("&")) {
            String[] kv = pair.split("=", 2);
            String key = URLDecoder.decode(kv[0], StandardCharsets.UTF_8);
            String value = kv.length > 1 ? URLDecoder.decode(kv[1], StandardCharsets.UTF_8) : "";
            params.put(key, value);
        }
        return params;
    }

    public HttpExchange source() {
        return exchange;
    }
}
