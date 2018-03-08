package com.example.hello.api;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

/**
 * The hello service interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the HelloService.
 */
public interface XyzService extends Service {
    /**
     * Example: curl http://localhost:9000/api/hello/Alice
     *
     */
    ServiceCall<NotUsed, String> hello(String id);

    @Override
    default Descriptor descriptor() {
        return named("xyz")
                .withCalls(
                        pathCall("/api/hello/:id", this::hello)
                )
                .withAutoAcl(true);
    }
}
