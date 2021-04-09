package no.hvl.dat251.backend.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptionConfig {

    @Bean
    public EncryptDecrypt getEncryptDecrypt() {
        return new EncryptDecrypt();
    }
}
