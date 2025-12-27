package com.nexoscript.kryobase.testenv.console.commands;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();

    public void register(Command command) {
        commands.put(command.name(), command);
    }

    public Command get(String label) {
        return commands.get(label);
    }

    public Collection<Command> getCommands() {
        return commands.values();
    }
}
