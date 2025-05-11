package com.kryobase.database.type;

import lombok.Getter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Collection {
    private String name;
    private Path path;
    private List<Document> documents;
    private List<User> user;

    public Collection(Database database, String name) {
        this.documents = new ArrayList<>();
        this.name = name;
        this.path = database.getPath().resolve(Path.of(this.name));
    }
}
