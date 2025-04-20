package com.kryobase;

public interface KryoCmd {
    void execute(String[] args);
    String name();
    String[] alias();
    String desc();
}
