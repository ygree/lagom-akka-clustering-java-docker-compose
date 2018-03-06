package com.example.hello.impl;


import akka.actor.*;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashSet;
import java.util.Set;

public class AbcActor extends AbstractLoggingActor {

    public static Props props() {
        return Props.create(AbcActor.class, AbcActor::new);
    }

    public static Object shutdownCommand() {
        return Commands.shutdown;
    }

    private enum Commands {
        shutdown
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .matchEquals(AbcActor.Commands.shutdown, m -> {
                    log().info("Received: AbcActor.Commands.end");
                })
                .match(String.class, m -> {
                    log().info("Received message: {}", m.toString());
                    getSender().tell("Hello, " + m + "!", getSelf());
                })
                .build();
    }
}