package io.openliberty.sample.system;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Readiness
@ApplicationScoped
public class SystemReadinessCheck implements HealthCheck {

    private static final String READINESS_CHECK = SystemResource.class.getSimpleName()
                                                 + " Readiness Check";

    @Inject
    @ConfigProperty(name = "io_openliberty_guides_system_inMaintenance")
    Provider<String> inMaintenance;

    @Override
    public HealthCheckResponse call() {
        if (inMaintenance != null && inMaintenance.get().equalsIgnoreCase("true")) {
            return HealthCheckResponse.down(READINESS_CHECK);
        }
        return HealthCheckResponse.up(READINESS_CHECK);
    }

}