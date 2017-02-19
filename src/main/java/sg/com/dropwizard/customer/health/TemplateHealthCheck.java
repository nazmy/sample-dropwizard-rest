package sg.com.dropwizard.customer.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by Achmad Nazmy on 18/02/2017
 * Senior Software Engineer | NetXcel Systems Pte Ltd
 * a.nazmy@gmail.com
 * nazmy@netxcel.com.sg | www.netxcel.com.sg
 */
public class TemplateHealthCheck extends HealthCheck {
    private final String template;

    public TemplateHealthCheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        final String customer = String.format(template, "TEST");
        if (!customer.contains("TEST")) {
            return Result.unhealthy("template doesn't include name");
        }
        return Result.healthy();
    }
}
