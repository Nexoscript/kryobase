package com.kryobase.database.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class User {
    private String name;
    private List<String> permissions;
}
