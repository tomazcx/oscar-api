package org.ufpr.oscarapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OscarApiApplication {

    private static final Logger log = LoggerFactory.getLogger(OscarApiApplication.class);

    public static void main(String[] args) {
        log.info("=== ENV VARS ===");
        log.info("PGHOST={}", System.getenv("PGHOST"));
        log.info("PGPORT={}", System.getenv("PGPORT"));
        log.info("PGDATABASE={}", System.getenv("PGDATABASE"));
        log.info("PGUSER={}", System.getenv("PGUSER"));
        log.info("PGPASSWORD={}", System.getenv("PGPASSWORD") != null ? "***set***" : "NOT SET");
        log.info("================");
        SpringApplication.run(OscarApiApplication.class, args);
    }

}
