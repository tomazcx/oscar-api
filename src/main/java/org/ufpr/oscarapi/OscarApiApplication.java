package org.ufpr.oscarapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OscarApiApplication {

    private static final Logger log = LoggerFactory.getLogger(OscarApiApplication.class);

    public static void main(String[] args) {
        var context = SpringApplication.run(OscarApiApplication.class, args);
        var env = context.getEnvironment();
        log.info("=== CONFIGURAÇÕES CARREGADAS ===");
        log.info("PGHOST={}", env.getProperty("PGHOST"));
        log.info("PGPORT={}", env.getProperty("PGPORT"));
        log.info("PGDATABASE={}", env.getProperty("PGDATABASE"));
        log.info("PGUSER={}", env.getProperty("PGUSER"));
        log.info("PGPASSWORD={}", env.getProperty("PGPASSWORD") != null ? "***definida***" : "NÃO DEFINIDA");
        log.info("================================");
    }

}
