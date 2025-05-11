package com.kryobase.database;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permissions {
    ALL("ALL"),
    READ("READ"),
    WRITE("WRITE"),
    MANAGE("MANAGE"),
    CREATE("CREATE"),
    DELETE("DELETE");

    private final String key;
}
