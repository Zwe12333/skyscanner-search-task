
package com.skyscanner;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SkyscannerApplication extends Application<io.dropwizard.Configuration> {

    public static void main(String[] args) throws Exception {
        new SkyscannerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<io.dropwizard.Configuration> bootstrap) { }

    @Override
    public void run(io.dropwizard.Configuration configuration, Environment environment) throws Exception {
        environment.jersey().register(new SearchResource());
    }
}
