package io.github.aprilvuylsteke000.workers;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.worker.JobWorker;
import io.github.aprilvuylsteke000.handlers.ImageLastStoredServiceHandler;

import java.time.Duration;

public class ImageLastStoredWorker {
    private final ZeebeClient client;

    public ImageLastStoredWorker(ZeebeClient client) {
        this.client = client;
    }

    public void start() {
        final JobWorker worker = client.newWorker()
                .jobType("getLastStoredImage")
                .handler(new ImageLastStoredServiceHandler())
                .timeout(Duration.ofSeconds(10).toMillis())
                .open();
    }
}