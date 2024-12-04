package io.github.aprilvuylsteke000.workers;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.worker.JobWorker;
import io.github.aprilvuylsteke000.handlers.ImageByTypeStorageServiceHandler;

import java.time.Duration;

public class ImageByTypeStorageWorker {
    private final ZeebeClient client;

    public ImageByTypeStorageWorker(ZeebeClient client) {
        this.client = client;
    }

    public void start() {
        final JobWorker worker = client.newWorker()
                .jobType("requestImageByType")
                .handler(new ImageByTypeStorageServiceHandler())
                .timeout(Duration.ofSeconds(10).toMillis())
                .open();
    }
}