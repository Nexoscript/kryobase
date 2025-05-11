package com.kryobase.database.type;

import com.kryobase.KryoBase;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Database {
    private String name;
    private Path path;
    private List<Collection> collections;
    private File databaseDir;
    private List<User> user;

    public Database(String name) {
        try {
            this.collections = new ArrayList<>();
            this.name = name;
            this.path = Path.of(KryoBase.getInstance().getDatabasePath(), this.name);

            if (!Files.exists(this.path)) {
                Files.createDirectory(this.path);
            }
            this.databaseDir = this.path.toFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
