package com.kryobase;

import java.util.ArrayList;
import java.util.List;

public class KryoLogger {
    private List<String> outputBuffer;

    public KryoLogger() {
        this.outputBuffer = new ArrayList<>();
    }

    public void logLn(String msg) {
        this.outputBuffer.add(msg);
        System.out.println(msg);
    }

    public void log(String msg) {
        this.outputBuffer.add(msg);
        System.out.print(msg);
    }

    public List<String> getOutputBuffer() {
        return this.outputBuffer;
    }

    public void clear() {
        this.outputBuffer.clear();
    }
}
