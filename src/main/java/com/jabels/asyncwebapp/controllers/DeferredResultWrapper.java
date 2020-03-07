package com.jabels.asyncwebapp.controllers;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class DeferredResultWrapper {

    public static <T> DeferredResult<T> from(CompletableFuture<T> data) {
        var result = new DeferredResult<T>();
        asyncComputationInit(data, result);
        return result;
    }

    public static <T> DeferredResult<T> from(DeferredResult result, CompletableFuture<T> data) {
        asyncComputationInit(data, result);
        return result;
    }

    private static <T> void asyncComputationInit(CompletableFuture<T> data, DeferredResult<T> result) {
        data.whenComplete((t, throwable) -> {
            if (null != throwable) {
                if (throwable instanceof CompletionException) {
                    result.setErrorResult(throwable.getCause());
                } else {
                    result.setErrorResult(throwable);
                }
            } else {
                result.setResult(t);
            }
        });
    }
}
