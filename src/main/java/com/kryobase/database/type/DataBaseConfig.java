package com.kryobase.database.type;

import de.eztxm.ezlib.config.JsonConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class DataBaseConfig extends JsonConfig {
    private List<User> users;

    public DataBaseConfig(String path, String configName) {
        super(path, configName);
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }
}
