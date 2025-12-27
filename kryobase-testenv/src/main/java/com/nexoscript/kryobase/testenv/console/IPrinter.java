package com.nexoscript.kryobase.testenv.console;

public interface IPrinter {

    void print(String message);
    void print(String message, boolean usePrefix);
    void println(String message);
    void println(String message, boolean usePrefix);
    void println(String message, boolean usePrefix, boolean newLine);

}
