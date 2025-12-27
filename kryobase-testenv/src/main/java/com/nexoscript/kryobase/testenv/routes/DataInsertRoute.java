package com.nexoscript.kryobase.testenv.routes;

import com.nexoscript.kryobase.api.server.rest.route.HttpMethod;
import com.nexoscript.kryobase.api.server.rest.route.IRoute;
import com.nexoscript.kryobase.testenv.database.storage.Row;
import com.nexoscript.kryobase.testenv.database.storage.Table;
import com.nexoscript.kryobase.testenv.launch.Main;
import com.nexoscript.kryobase.testenv.util.JsonUtil;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;

public class DataInsertRoute implements IRoute {
    @Override
    public @NotNull String path() {
        return "/api/data/{table}/insert";
    }

    @Override
    public @NotNull HttpMethod method() {
        return HttpMethod.POST;
    }

    @Override
    public void execute(@NotNull Context ctx) {
        String tableName = ctx.pathParam("table");
        Table table = Main.INSTANCE.db().storage().table(tableName);

        if (table == null) {
            ctx.status(404).result("Table not found: " + tableName);
            return;
        }

        try {
            Map<String, Object> data = JsonUtil.GSON.fromJson(ctx.body(), Map.class);
            UUID id = data.containsKey("id") ? UUID.fromString(data.get("id").toString()) : UUID.randomUUID();

            Row row = new Row().set("id", id);
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                row.set(entry.getKey(), entry.getValue());
            }

            table.insert(row);
            ctx.status(201).result(JsonUtil.GSON.toJson(row));
        } catch (Exception e) {
            ctx.status(400).result("Error inserting data: " + e.getMessage());
        }
    }
}
