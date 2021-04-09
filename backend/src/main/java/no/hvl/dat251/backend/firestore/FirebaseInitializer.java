package no.hvl.dat251.backend.firestore;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import no.hvl.dat251.backend.util.EncryptDecrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseInitializer {

    @Autowired
    EncryptDecrypt encryptDecrypt;

    @PostConstruct
    public void initialize() throws IOException {
        encryptDecrypt.decrypt("./secret.json");
        FileInputStream serviceAccount = new FileInputStream("./secret.json");
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        // scuffed method to re-encrypt file
        encryptDecrypt.encrypt("secret.json");
        if (FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
		}
    }
    
    public Firestore getDatabase() throws IOException {
		return FirestoreClient.getFirestore();
	}
	
	public FirebaseAuth getAuth() throws IOException {
		return FirebaseAuth.getInstance();
	}
}