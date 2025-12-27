package com.nexoscript.kryobase.testenv.console.commands.impl;

import com.nexoscript.kryobase.testenv.console.commands.Command;
import com.nexoscript.kryobase.testenv.console.commands.CommandManager;

public class HelpCommand implements Command {

    private final CommandManager manager;

    public HelpCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public String desc() {
        return "Zeigt alle Befehle an";
    }

    @Override
    public void execute(String[] args) {
        for (Command cmd : manager.getCommands()) {
            System.out.printf("%-10s - %s%n", cmd.name(), cmd.desc());
        }
    }
}

