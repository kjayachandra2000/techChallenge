package com.blockchain.exercise.support;

import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.SystemConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Environment {

    private File BASE_DIR = new File(System.getProperty("user.dir"));
    private File CONFIG_FILE = new File(BASE_DIR, "config.properties");

    public String get(String setting) {
        CompositeConfiguration config = null;
        try {
            config = new CompositeConfiguration();
            config.addConfiguration(new SystemConfiguration());
            Parameters params = new Parameters();
            FileBasedConfigurationBuilder<FileBasedConfiguration> builderDefaults =
                    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                            .configure(params.fileBased()
                                    .setFile(CONFIG_FILE));

            config.addConfiguration(builderDefaults.getConfiguration());
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return (System.getenv(setting) != null) ? System.getenv(setting) : config.getString(setting);
    }
}