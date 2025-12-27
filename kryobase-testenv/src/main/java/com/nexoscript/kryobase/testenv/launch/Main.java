package com.nexoscript.kryobase.testenv.launch;

import com.nexoscript.kryobase.api.server.KryobaseServerProvider;
import com.nexoscript.kryobase.server.KryobaseServer;
import com.nexoscript.kryobase.testenv.console.Console;
import com.nexoscript.kryobase.testenv.console.commands.impl.ExitCommand;
import com.nexoscript.kryobase.testenv.console.commands.impl.HelpCommand;
import com.nexoscript.kryobase.testenv.database.DatabaseEngine;
import com.nexoscript.kryobase.testenv.routes.DataInsertRoute;
import com.nexoscript.kryobase.testenv.routes.DataReadRoute;
import com.nexoscript.kryobase.testenv.routes.SchemaCreateRoute;

import java.nio.file.Path;


public class Main {
    public static Main INSTANCE;
    private Console console;
    private String host = "localhost";
    private int port = 8080;
    private DatabaseEngine db;

    private void registerRoutes() {
        KryobaseServerProvider.get().getRouteHandler().register(new SchemaCreateRoute());
        KryobaseServerProvider.get().getRouteHandler().register(new DataInsertRoute());
        KryobaseServerProvider.get().getRouteHandler().register(new DataReadRoute());
    }

    private void registerCommands() {
        this.console.register(new HelpCommand(this.console.getCommandManager()));
        this.console.register(new ExitCommand());
    }

    public void shutdown() {
        this.console.getPrinter().println("Database stopped and saved.", true);
        this.db.shutdown();
        this.console.getPrinter().println("Server stopped.", true);
        KryobaseServerProvider.get().shutdown();
        this.console.getPrinter().println("Console stopped.", true);
        this.console.stopConsole();
    }

    void main() {
        INSTANCE = this;
        System.out.println("Console started.");
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));

        KryobaseServerProvider.register(new KryobaseServer());
        this.registerRoutes();
        KryobaseServerProvider.get().start(this.host, this.port);
        System.out.println("Server started on http://" + this.host + ":" + this.port + "/");

        this.db = new DatabaseEngine(Path.of("database"));
        db.start();
        System.out.println("Database started.");

        this.console = new Console();
        this.registerCommands();
        this.console.start();
    }

    public DatabaseEngine db() {
        return db;
    }

    public Console console() {
        return console;
    }
}