package io.github.perftool.trace.report.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class MongoConfig {

    private String host;

    private int port;

    private String username;

    private String password;

    private String databaseName;

    private String collectionName;

    public MongoConfig() {
    }
}
