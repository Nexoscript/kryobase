package com.nexoscript.kryobase.testenv.routes;

import com.nexoscript.kryobase.api.server.rest.route.HttpMethod;
import com.nexoscript.kryobase.api.server.rest.route.IRoute;
import com.nexoscript.kryobase.testenv.database.schema.EntitySchema;
import com.nexoscript.kryobase.testenv.launch.Main;
import com.nexoscript.kryobase.testenv.util.JsonUtil;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

public class SchemaCreateRoute implements IRoute {
    @Override
    public @NotNull String path() {
        return "/api/schema/create";
    }

    @Override
    public @NotNull HttpMethod method() {
        return HttpMethod.POST;
    }

    @Override
    public void execute(@NotNull Context ctx) {
        try {
            EntitySchema schema = JsonUtil.GSON.fromJson(ctx.body(), EntitySchema.class);
            Main.INSTANCE.db().schemas().register(schema);
            Main.INSTANCE.db().getOrCreateTable(schema);

            ctx.status(201).result("Schema '" + schema.name() + "' created and table initialized.");
        } catch (Exception e) {
            ctx.status(400).result("Invalid Schema Definition: " + e.getMessage());
        }
    }
}
