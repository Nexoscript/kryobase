package com.nexoscript.kryobase.testenv.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nexoscript.kryobase.testenv.database.schema.adapter.InstantAdapter;
import com.nexoscript.kryobase.testenv.database.schema.adapter.UUIDAdapter;

import java.time.Instant;
import java.util.UUID;

public class JsonUtil {
    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(UUID.class, new UUIDAdapter())
            .registerTypeAdapter(Instant.class, new InstantAdapter())
            .create();

    private JsonUtil() {}
}
