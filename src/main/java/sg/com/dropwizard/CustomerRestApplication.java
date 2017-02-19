package sg.com.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import sg.com.dropwizard.customer.health.TemplateHealthCheck;
import sg.com.dropwizard.customer.resources.CustomerResource;

/**
 * Created by Achmad Nazmy on 17/02/2017
 * Senior Software Engineer | NetXcel Systems Pte Ltd
 * a.nazmy@gmail.com
 * nazmy@netxcel.com.sg | www.netxcel.com.sg
 */
public class CustomerRestApplication extends Application<CustomerRestConfiguration> {
    public static void main(String[] args) throws Exception{
        new CustomerRestApplication().run(args);
    }

    @Override
    public String getName() {
        //return super.getName();
        return "customer-rest";
    }

    @Override
    public void initialize(Bootstrap<CustomerRestConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public void run(CustomerRestConfiguration customerRestConfiguration,
                    Environment environment) throws Exception {
        final CustomerResource resource = new CustomerResource(
                customerRestConfiguration.getTemplate(),
                customerRestConfiguration.getDefaultName());

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(customerRestConfiguration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        environment.jersey().register(resource);
    }
}
