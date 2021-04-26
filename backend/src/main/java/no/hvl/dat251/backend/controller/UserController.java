//package no.hvl.dat251.backend.controller;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.google.firebase.auth.FirebaseAuthException;
//import com.google.firebase.auth.FirebaseToken;
//
//import no.hvl.dat251.backend.firestore.FirestoreConfig;
//import no.hvl.dat251.backend.model.User;
//import no.hvl.dat251.backend.repository.UserRepository;
//
//@RestController
//public class UserController {
//	
//	@Autowired
//	UserRepository userRepository;
//	
//	@Autowired
//	FirestoreConfig config;
//	
//	@GetMapping("/api/users")
//	public ResponseEntity<List<User>> getAllUsers(){
//		try {
//			List<User> users = new ArrayList<User>();
//			userRepository.findAll().forEach(users::add);		
//			if(users.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			return new ResponseEntity<>(users, HttpStatus.OK);
//		}catch(Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//	@GetMapping("/api/users/{id}")
//	public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
//		Optional<User> user = userRepository.findById(id);
//		System.out.println(id);
//		if (user.isPresent()) {
//			return new ResponseEntity<>(user.get(), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//	
//	@GetMapping("/api/users/user/{email}")
//	public ResponseEntity<User> getUserByUsername(@PathVariable("email") String email, @RequestHeader (name="Authorization") String token){
//		String _token = token.replaceAll("Bearer ", "");
//		if(getValidToken(_token) != null) {
//			Optional<User> user = userRepository.findByEmail(email);
//			if (user.isPresent()) {
//				return new ResponseEntity<>(user.get(), HttpStatus.OK);
//			} else{
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			}
//		}else {
//			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
//		}
//	}
//	
//	@PutMapping("/api/users/{id}")
//	//@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<User> updateUser(@RequestHeader (name="Authorization") String token, @PathVariable("id") String id, @RequestParam(required = false) Long roleId) {
//		String _token = token.replaceAll("Bearer ", "");
//		FirebaseToken firebasetoken = getValidToken(_token);
//		if(firebasetoken != null) {
//			Optional<User> userData = userRepository.findById(id);
//			if (userData.isPresent()) {
//				User updatedUser = userData.get();
//				if (firebasetoken.getEmail() != null) {
//					updatedUser.setEmail(firebasetoken.getEmail());
//				}
//				return new ResponseEntity<>(userRepository.save(updatedUser), HttpStatus.OK);
//			} else {
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			}
//		}else {
//			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
//		}
//	}
//	
//	@PostMapping("/api/users")
//	public ResponseEntity<User> createUser(@RequestHeader (name="Authorization") String token, @RequestParam(required = false) Long roleId) {
//		String _token = token.replaceAll("Bearer ", "");
//		FirebaseToken firebasetoken = getValidToken(_token);
//		if(firebasetoken != null) {
//		
//			try {
//				User newUser = new User(firebasetoken.getUid(), firebasetoken.getEmail());
//				User savedUser = userRepository.save(newUser);
//				return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//			} catch (Exception e) {
//				e.printStackTrace();
//				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//		}else {
//			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
//		}
//	}
//	
//	@DeleteMapping("/api/users/{id}")
//	public ResponseEntity<HttpStatus> deleteUser(@RequestHeader (name="Authorization") String token,@PathVariable("id") String id) {
//		String _token = token.replaceAll("Bearer ", "");
//		FirebaseToken firebasetoken = getValidToken(_token);
//		if(firebasetoken != null) {
//			try {
//				config.getAuth().deleteUser(id);
//				userRepository.deleteById(id);
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			} catch (Exception e) {
//				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//			}	
//		}else {
//			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
//		}
//	}
//	
//	
//	private FirebaseToken getValidToken(String token) {
//		try {
//			return config.getAuth().verifyIdToken(token);
//		}catch(FirebaseAuthException e) {
//			e.printStackTrace();
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//}
