package com.nexoscript.kryobase.testenv.console;

import java.io.PrintWriter;

public record Printer(PrintWriter writer, String prefix) implements IPrinter {

    @Override
    public void print(String message) {
        this.println(message, false, false);
    }

    @Override
    public void print(String message, boolean usePrefix) {
        this.println(message, usePrefix, false);
    }

    @Override
    public void println(String message) {
        this.println(message, false, true);
    }

    @Override
    public void println(String message, boolean usePrefix) {
        this.println(message, usePrefix, true);
    }

    @Override
    public void println(String message, boolean usePrefix, boolean newLine) {
        this.writer.write((usePrefix ? prefix : "") + message + (newLine ? "\n" : ""));
    }
}
