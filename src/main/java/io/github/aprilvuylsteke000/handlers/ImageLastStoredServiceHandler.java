package io.github.aprilvuylsteke000.handlers;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;

public class ImageLastStoredServiceHandler implements JobHandler {

    @Override
    public void handle(JobClient client, final ActivatedJob job) {
        // Logic for handling the job
        System.out.println("Handling job with type 'getLastStoredImage' ");

        // Example: complete the job
        try {
            client.newCompleteCommand(job.getKey())
                    .variables("{\"status\":\"success\"}")
                    .send()
                    .join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
