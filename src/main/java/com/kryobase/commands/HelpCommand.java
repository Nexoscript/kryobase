package com.kryobase.commands;

import com.kryobase.KryoBase;
import com.kryobase.KryoCmd;

public class HelpCommand implements KryoCmd {
    @Override
    public void execute(String[] args) {
        KryoBase.getInstance().getLogger().logLn("##################### Command List #####################");
        for (KryoCmd command : KryoBase.getInstance().getCommands()) {
            KryoBase.getInstance().getLogger().logLn(command.name() + " -> " + command.desc());
        }
        KryoBase.getInstance().getLogger().logLn("##################### Command List #####################");
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public String[] alias() {
        return new String[] {
            "-h", "-help"
        };
    }

    @Override
    public String desc() {
        return "List Commands and Descriptions";
    }
}
