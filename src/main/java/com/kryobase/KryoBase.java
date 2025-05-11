package com.kryobase;

import com.kryobase.commands.CreateCommand;
import com.kryobase.commands.ExitCommand;
import com.kryobase.commands.HelpCommand;
import com.kryobase.config.MainConfig;
import com.kryobase.database.KryoBaseManager;
import de.eztxm.ezlib.config.object.JsonUtil;
import de.eztxm.ezlib.config.reflect.JsonProcessor;
import io.javalin.Javalin;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
public class KryoBase {
    private static KryoBase INSTANCE;
    private KryoLogger logger;
    private Thread databaseThread;
    private String kryoBasePrompt = "kryoBase>>";
    private String databasePath;
    private List<KryoCmd> commands;
    private Javalin webServer;
    private MainConfig config;
    private KryoBaseManager kryoBaseManager;

    private void loadCommands() {
        this.commands.add(new HelpCommand());
        this.commands.add(new CreateCommand());
        this.commands.add(new ExitCommand());
    }

    private void loadRequests() {

    }

    public void shutdown() {
        this.webServer.stop();
        System.exit(0);
    }

    public KryoBase() {
        INSTANCE = this;
        this.logger = new KryoLogger();
        this.logger.logLn("#############################################");
        this.logger.logLn("#                                           #");
        this.logger.logLn("#                 KryoBase DB               #");
        this.logger.logLn("#       command: help for command list      #");
        this.logger.logLn("#                                           #");
        this.logger.logLn("#############################################");

        JsonUtil.prettyPrint = true;

        if(!Files.exists(Path.of("config.json"))) {
            JsonProcessor<MainConfig> processor = JsonProcessor.loadConfiguration(MainConfig.class);
            MainConfig config = processor.getInstance();
            config.setPath("kryobase");
            config.setPort(8000);
            processor.saveConfiguration();
            this.logger.logLn("Config Created!");
        }
        JsonProcessor<MainConfig> processor = JsonProcessor.loadConfiguration(MainConfig.class);
        this.config = processor.getInstance();
        this.databasePath = this.config.getPath();
        this.logger.logLn("Config Loaded!");

        if(!Files.exists(Path.of(databasePath))) {
            try {
                Files.createDirectories(Path.of(databasePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        
        this.commands = new ArrayList<>();
        this.loadCommands();
        this.logger.logLn("Commands Loaded!");

        this.kryoBaseManager = new KryoBaseManager();
        this.logger.logLn("KryoBase Manager Initialized!");

        this.webServer = Javalin.create(config -> {
            config.showJavalinBanner = false;
        });
        loadRequests();
        this.webServer.start(this.config.getPort());
        this.logger.logLn("WebServer started on https://127.0.0.1:" + webServer.port() + "/");

        Scanner scanner = new Scanner(System.in);
        this.logger.log(this.kryoBasePrompt);
        this.databaseThread = new Thread(() -> {
            while (scanner.hasNext()) {
                String[] command = scanner.nextLine().split(" ");
                if(command.length >= 1) {
                    this.commands.forEach(cmd -> {
                        if(command[0].equalsIgnoreCase(cmd.name())) {
                            cmd.execute(command);
                        }
                        for (String alias : cmd.alias()) {
                            if(command[0].equalsIgnoreCase(alias)) {
                                cmd.execute(command);
                            }
                        }
                    });
                }
                this.logger.log(this.kryoBasePrompt);
            }
        });
        this.databaseThread.start();
    }

    public static void main(String[] args) {
        new KryoBase();
    }

    public static KryoBase getInstance() {
        return INSTANCE;
    }

}
