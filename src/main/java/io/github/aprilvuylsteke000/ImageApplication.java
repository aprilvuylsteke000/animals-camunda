package io.github.aprilvuylsteke000;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProvider;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProviderBuilder;
import io.github.aprilvuylsteke000.config.ConfigLoader;
import io.github.aprilvuylsteke000.workers.ImageByTypeStorageWorker;
import io.github.aprilvuylsteke000.workers.ImageLastStoredWorker;

public class ImageApplication {

    public static void main(String[] args) {

        final OAuthCredentialsProvider credentialsProvider =
                new OAuthCredentialsProviderBuilder()
                        .authorizationServerUrl(ConfigLoader.getProperty("zeebe.authorization-server-url"))
                        .audience(ConfigLoader.getProperty("zeebe.token-audience"))
                        .clientId(ConfigLoader.getProperty("zeebe.client-id"))
                        .clientSecret(ConfigLoader.getProperty("zeebe.client-secret"))
                        .build();

        try (final ZeebeClient client =
                     ZeebeClient.newClientBuilder()
                             .gatewayAddress(ConfigLoader.getProperty("zeebe.address"))
                             .credentialsProvider(credentialsProvider)
                             .build()) {

            System.out.println("Connected to: " + client.newTopologyRequest().send().join());

            // Start workers
            new ImageByTypeStorageWorker(client).start();
            new ImageLastStoredWorker(client).start();

            // Keep application running
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
