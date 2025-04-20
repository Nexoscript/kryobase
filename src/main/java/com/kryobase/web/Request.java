package com.kryobase.web;

import java.util.function.Consumer;

public record Request(String path, String requestType, Consumer<HttpContext> ctx) {
}
