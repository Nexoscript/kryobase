package com.kryobase.database;

import com.kryobase.KryoBase;
import com.kryobase.database.type.DataBaseConfig;
import com.kryobase.database.type.Database;
import com.kryobase.database.type.User;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KryoBaseManager {
    private List<Database> databases;

    public KryoBaseManager() {
        this.databases = new ArrayList<>();
    }

    public void createDatabase(String name) {
        this.databases.add(new Database(name));
        DataBaseConfig dataBaseConfig = new DataBaseConfig(Path.of(KryoBase.getInstance().getDatabasePath(), name).toString(), name + ".kryo");
        dataBaseConfig.save();
        KryoBase.getInstance().getLogger().logLn("Database " + name + " created!");
    }

    public void createUser(String databaseName, String username, String[] permissions) {
        for (String permission : permissions) {
            permission = permission.toUpperCase();
        }
        DataBaseConfig dataBaseConfig = new DataBaseConfig(
                Path.of(KryoBase.getInstance().getDatabasePath(),
                        databaseName).toString(),
                databaseName + ".kryo"
        );
        dataBaseConfig.addUser(new User(
                username,
                Arrays.stream(permissions).toList()
        ));
        dataBaseConfig.save();
        KryoBase.getInstance().getLogger().logLn("User " + username + " created!");
    }

    public void loadDatabases() {

    }

    public void loadCollections() {

    }

    public void loadDocuments() {

    }
}
