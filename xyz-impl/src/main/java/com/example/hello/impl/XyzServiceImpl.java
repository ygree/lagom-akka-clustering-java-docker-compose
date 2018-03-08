package com.example.hello.impl;

import akka.NotUsed;
import akka.actor.ActorSystem;
import com.example.hello.api.HelloService;
import com.example.hello.api.XyzService;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of the HelloService.
 */
public class XyzServiceImpl implements XyzService {

    private final HelloService abcService;

    @Inject
    public XyzServiceImpl(HelloService abcService) {
        this.abcService = abcService;
    }

    @Override
    public ServiceCall<NotUsed, String> hello(String id) {

        return request -> {
            return abcService.hello(id).invoke().thenApply(m -> m.message + "*");
//            return CompletableFuture.completedFuture("Hello!");
        };
    }

}
