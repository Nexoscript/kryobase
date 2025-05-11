package com.kryobase.commands;

import com.kryobase.KryoBase;
import com.kryobase.KryoCmd;

public class CreateCommand implements KryoCmd {
    @Override
    public void execute(String[] args) {
        if(args.length >= 2) {
            switch (args[1].toUpperCase()) {
                case "DB" -> {
                    KryoBase.getInstance().getLogger().logLn("Create DB");
                    KryoBase.getInstance().getKryoBaseManager().createDatabase(args[2]);
                }
                case "COLLECTION" -> {
                    KryoBase.getInstance().getLogger().logLn("Create COLLECTION");
                }
                case "DOCUMENT" -> {
                    KryoBase.getInstance().getLogger().logLn("Create DOCUMENT");
                }
                case "USER" -> {
                    KryoBase.getInstance().getLogger().logLn("Create USER");
                    KryoBase.getInstance().getKryoBaseManager().createUser(
                            args[2],
                            args[3],
                            args[4].split(":")
                    );
                }
            }
        } else {
            KryoBase.getInstance().getLogger().logLn("use create < DB | COLLECTION | DOCUMENT | USER >");
        }
    }

    @Override
    public String name() {
        return "create";
    }

    @Override
    public String[] alias() {
        return new String[0];
    }

    @Override
    public String desc() {
        return "Create DBS | COLLECTIONS | DOCUMENTS | USERS";
    }
}
