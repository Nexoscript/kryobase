package com.kryobase.database.type;

import com.kryobase.KryoBase;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Document {
    private File documentFile;
    private String name;
    private Path path;
    private List<User> user;

    public Document(Collection collection, String name) {
        this.name = name;
        this.path = collection.getPath().resolve(Path.of(this.name));
        this.documentFile = this.path.toFile();
        if (!this.documentFile.exists()) {
            KryoBase.getInstance().getLogger().logLn("Document init Error");
        }
        this.user = new ArrayList<>();
    }
}
