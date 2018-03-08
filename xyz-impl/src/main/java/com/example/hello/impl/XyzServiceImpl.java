package com.example.hello.impl;

import akka.NotUsed;
import akka.actor.ActorSystem;
import com.example.hello.api.XyzService;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of the HelloService.
 */
public class XyzServiceImpl implements XyzService {


    @Inject
    public XyzServiceImpl(ActorSystem system) {

    }

    @Override
    public ServiceCall<NotUsed, String> hello(String id) {
        return request -> {
            return CompletableFuture.completedFuture("Hello!");
        };
    }

}
