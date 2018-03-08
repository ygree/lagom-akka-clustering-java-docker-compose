package com.example.hello.impl;

import com.example.hello.api.XyzService;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.api.ServiceLocator;
import com.lightbend.lagom.javadsl.client.ConfigurationServiceLocator;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.typesafe.config.Config;
import play.Environment;

/**
 * The module that binds the HelloService so that it can be served.
 */
public class XyzModule extends AbstractModule implements ServiceGuiceSupport {
    private final Environment environment;
    private final Config config;

    public XyzModule(Environment environment, Config config) {
        this.environment = environment;
        this.config = config;
    }

    @Override
    protected void configure() {
        bindService(XyzService.class, XyzServiceImpl.class);

        if (environment.isProd()) {
            bind(ServiceLocator.class).to(ConfigurationServiceLocator.class);
        }

    }
}
