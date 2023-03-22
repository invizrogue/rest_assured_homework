package study.qa.config;

import org.aeonbits.owner.Config;

@RestConfig.Sources({"classpath:${env}.properties"})

public interface RestConfig extends Config {

    @Key("baseUrl")
    String getBaseUrl();

    @Key("basePath")
    String getBasePath();
}