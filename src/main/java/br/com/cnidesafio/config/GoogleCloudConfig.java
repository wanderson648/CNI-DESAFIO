package br.com.cnidesafio.config;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.LanguageServiceSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

@Configuration
public class GoogleCloudConfig {

    @Bean
    public LanguageServiceClient languageServiceClient() throws Exception {
        InputStream credentialsStream = getClass()
                .getClassLoader()
                .getResourceAsStream("google-credentials.json");

        assert credentialsStream != null;
        LanguageServiceSettings settings = LanguageServiceSettings.newBuilder()
                .setCredentialsProvider(
                        FixedCredentialsProvider.create(
                                ServiceAccountCredentials.fromStream(credentialsStream)
                        )
                ).build();

        return LanguageServiceClient.create(settings);
    }
}