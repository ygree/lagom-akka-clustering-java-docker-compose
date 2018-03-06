package com.example.hello.impl;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.cluster.singleton.ClusterSingletonManager;
import akka.cluster.singleton.ClusterSingletonManagerSettings;
import akka.cluster.singleton.ClusterSingletonProxy;
import akka.cluster.singleton.ClusterSingletonProxySettings;
import com.example.hello.api.GreetingMessage;
import com.example.hello.api.HelloService;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import javax.inject.Inject;

import static akka.pattern.PatternsCS.ask;

/**
 * Implementation of the HelloService.
 */
public class HelloServiceImpl implements HelloService {

    final ActorRef abcActor;

    @Inject
    public HelloServiceImpl(ActorSystem system) {

        ClusterSingletonManagerSettings settings =
                ClusterSingletonManagerSettings.create(system);

        system.actorOf(
                ClusterSingletonManager.props(
                        AbcActor.props(),
                        AbcActor.shutdownCommand(),
                        settings),
                "abc-actor");


        ClusterSingletonProxySettings proxySettings = ClusterSingletonProxySettings.create(system);

        this.abcActor = system.actorOf(ClusterSingletonProxy.props(
                "/user/abc-actor", proxySettings),
                "abc-proxy");
    }

    @Override
    public ServiceCall<NotUsed, GreetingMessage> hello(String id) {
        return request -> {
            return ask(abcActor, id, 1000).thenApply(m -> new GreetingMessage((String) m));
        };
    }

}
