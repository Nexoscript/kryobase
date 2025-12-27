package com.nexoscript.kryobase.testenv.console.commands;

public interface Command {
    String name();
    String desc();
    void execute(String[] args);
}
