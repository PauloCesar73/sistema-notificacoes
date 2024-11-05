package com.seuprojeto.sistema_notificacoes.infrastructure.config;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.config.json}")
    private String firebaseConfigJson;

    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        @SuppressWarnings("unchecked")
        Map<String, Object> jsonMap = mapper.readValue(firebaseConfigJson, Map.class);

        InputStream serviceAccount = new ByteArrayInputStream(mapper.writeValueAsBytes(jsonMap));

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
