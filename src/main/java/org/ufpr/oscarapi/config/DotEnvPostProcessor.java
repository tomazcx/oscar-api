package org.ufpr.oscarapi.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class DotEnvPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Path dotEnv = Path.of(".env");
        if (!Files.exists(dotEnv)) return;

        Map<String, Object> props = new HashMap<>();
        try {
            Files.lines(dotEnv)
                    .map(String::trim)
                    .filter(line -> !line.isBlank() && !line.startsWith("#"))
                    .forEach(line -> {
                        int eq = line.indexOf('=');
                        if (eq > 0) {
                            String key = line.substring(0, eq).trim();
                            String value = line.substring(eq + 1).trim();
                            props.put(key, value);
                        }
                    });
        } catch (IOException ignored) {}

        if (!props.isEmpty()) {
            // addLast garante que variáveis de sistema/Railway têm prioridade sobre o .env
            environment.getPropertySources().addLast(new MapPropertySource("dotenv", props));
        }
    }
}
