package no.hvl.dat251.backend.firestore;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirestoreConfig {

	@Autowired
	FirebaseInitializer init;
	
    @Bean
    public Firestore getDb() {
        return FirestoreClient.getFirestore();
    }
    
    @Bean
    public FirebaseAuth getAuth() throws IOException {
 		return FirebaseAuth.getInstance();
 	}
}
