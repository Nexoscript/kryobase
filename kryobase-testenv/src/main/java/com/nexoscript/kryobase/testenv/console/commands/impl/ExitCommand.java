package com.nexoscript.kryobase.testenv.console.commands.impl;


import com.nexoscript.kryobase.testenv.console.commands.Command;

public class ExitCommand implements Command {

    @Override
    public String name() {
        return "exit";
    }

    @Override
    public String desc() {
        return "Beendet das Programm";
    }

    @Override
    public void execute(String[] args) {
        System.exit(0);
    }
}

