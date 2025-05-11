package com.kryobase.config;

import de.eztxm.ezlib.config.annotation.JsonConfig;
import de.eztxm.ezlib.config.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@JsonConfig
@AllArgsConstructor
@NoArgsConstructor
public class MainConfig {
    @JsonValue(name = "database.path")
    private String path;
    @JsonValue(name = "database.port")
    private int port;
}
