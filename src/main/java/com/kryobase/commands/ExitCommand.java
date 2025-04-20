package com.kryobase.commands;

import com.kryobase.KryoBase;
import com.kryobase.KryoCmd;

public class ExitCommand implements KryoCmd {
    @Override
    public void execute(String[] args) {
        KryoBase.getInstance().getLogger().logLn("Shutdown the Database");
        KryoBase.getInstance().shutdown();
    }

    @Override
    public String name() {
        return "exit";
    }

    @Override
    public String[] alias() {
        return new String[] {
                "-e", "-ex"
        };
    }

    @Override
    public String desc() {
        return "Shutdown the Database";
    }
}
