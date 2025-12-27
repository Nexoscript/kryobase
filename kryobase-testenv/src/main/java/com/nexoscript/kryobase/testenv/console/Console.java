package com.nexoscript.kryobase.testenv.console;

import com.nexoscript.kryobase.testenv.console.commands.Command;
import com.nexoscript.kryobase.testenv.console.commands.CommandManager;
import com.nexoscript.kryobase.testenv.console.exception.NoTerminalFoundException;
import org.jline.reader.*;
import org.jline.reader.impl.history.DefaultHistory;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public final class Console extends Thread implements IConsole {
    private CommandManager commandManager = new CommandManager();

    private Terminal terminal;
    private Completer completer;
    private LineReader reader;
    private IPrinter printer;
    private boolean running = false;

    public void register(Command command) {
        commandManager.register(command);
    }

    public void stopConsole() {
        running = false;
        this.exit();
    }

    @Override
    public void run() {
        running = true;
        try {
            terminal = TerminalBuilder.builder().system(true).build();
            completer = (_, line, candidates) -> {
                String buffer = line.line();
                for (Command command : commandManager.getCommands()) {
                    if (command.name().startsWith(buffer)) {
                        candidates.add(new Candidate(command.name()));
                    }
                }
            };
            reader = LineReaderBuilder.builder().terminal(terminal).completer(completer).history(new DefaultHistory())
                    .build();
            if (terminal == null) {
                throw new NoTerminalFoundException("Terminal is null.");
            }
            this.printer = new Printer(terminal.writer(), "Kryobase » ");
            printer.println("Welcome to Kryobase - Type 'help' to list commands.", true);
            terminal.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start() {
        this.run();
        while (running) {
            String line;
            try {
                line = reader.readLine("@" + System.getProperty("user.name") + " » ");
            } catch (UserInterruptException e) {
                continue;
            } catch (EndOfFileException e) {
                break;
            }
            if (line == null)
                break;
            line = line.trim();
            if (line.isEmpty())
                continue;
            String[] parts = line.split("\\s+");
            String cmd = parts[0];
            handleCommand(cmd, parts);
            terminal.flush();
        }
        this.exit();
    }

    @Override
    public void handleCommand(String cmd, String[] parts) {
        Command command = commandManager.get(cmd);
        if (command == null) {
            this.printer.println("Unknown Command: " + cmd, true);
        }
        command.execute(parts);
    }

    @Override
    public void exit() {
        this.printer = null;
        this.reader = null;
        this.completer = null;
        this.terminal.flush();
        this.terminal = null;
    }

    @Override
    public IPrinter getPrinter() {
        return printer;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
