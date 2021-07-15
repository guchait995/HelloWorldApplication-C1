package com.test.common;

import com.google.common.collect.ImmutableList;
import com.test.employee.EmployeeWebResource;
import com.test.employee.model.Employee;
import com.test.employee.repo.EmployeeRepo;
import io.dropwizard.Application;
import io.dropwizard.Bundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.SessionFactoryFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class HelloWorldApplcation extends Application<HelloWorldConfiguration> {
    public static final ImmutableList<Class<?>> ENTITIES = ImmutableList.of(
            Employee.class);

    private final HibernateBundle<HelloWorldConfiguration> hibernate = new HibernateBundle<HelloWorldConfiguration>(ENTITIES,new SessionFactoryFactory()) {
        @Override
        public DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
        @Override
        protected void configure(Configuration configuration) {
            configuration.setProperty("hibernate.hbm2dll.extra_physical_table_types", "MATERIALIZED VIEW");
            configuration.setProperty("hibernate.jdbc.batch_size", String.valueOf(10));
            configuration.setProperty("hibernate.order_inserts", "true");
            configuration.setProperty("hibernate.order_updates", "true");
            super.configure(configuration);
        }
    };

    @Override
    public String getName() {
        return "HelloWorld";
    }
    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }


    public static void main(String[] args) throws Exception {
        new HelloWorldApplcation().run(args);
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {
        SessionFactory sessionFactory=hibernate.getSessionFactory();
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        EmployeeRepo employeeRepo=new EmployeeRepo(sessionFactory);

        environment.jersey().register(new EmployeeWebResource(employeeRepo));
    }


}
