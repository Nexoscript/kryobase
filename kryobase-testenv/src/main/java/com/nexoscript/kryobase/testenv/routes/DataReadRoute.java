package com.nexoscript.kryobase.testenv.routes;

import com.nexoscript.kryobase.api.server.rest.route.HttpMethod;
import com.nexoscript.kryobase.api.server.rest.route.IRoute;
import com.nexoscript.kryobase.testenv.database.storage.Table;
import com.nexoscript.kryobase.testenv.launch.Main;
import com.nexoscript.kryobase.testenv.util.JsonUtil;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

public class DataReadRoute implements IRoute {
    @Override
    public @NotNull String path() {
        return "/api/data/{table}";
    }

    @Override
    public @NotNull HttpMethod method() {
        return HttpMethod.GET;
    }

    @Override
    public void execute(@NotNull Context ctx) {
        String tableName = ctx.pathParam("table");
        Table table = Main.INSTANCE.db().storage().table(tableName);

        if (table == null) {
            ctx.status(404).result("Table not found: " + tableName);
            return;
        }

        ctx.contentType("application/json").result(JsonUtil.GSON.toJson(table.rows()));
    }
}
