package com.medici.app;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.medici.app.entity.Book;

/**
 * 
 * @author a73s
 *
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	Firestore firestore;

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			writeDocumentFromMap();
			writeDocumentFromObject();
			readDocumentToMap();
			readDocumentToObject();

		};
	}

	private void writeDocumentFromMap() throws InterruptedException, java.util.concurrent.ExecutionException {
		DocumentReference docRef = this.firestore.collection("books").document("books");
		// Add document data with id "ada" using a hashmap
		Map<String, Object> data = new HashMap<>();
		data.put("title", "jamessss");
		data.put("author", "doorssss");
		data.put("year", 1970);

		ApiFuture<WriteResult> result = docRef.set(data);

		System.out.println("Update time: " + result.get().getUpdateTime());
	}

	private void writeDocumentFromObject() throws InterruptedException, ExecutionException {
		Book data = new Book(null, "billsss", "janessss", 1971);

		WriteResult writeResult = this.firestore.document("books/book").set(data).get();

		System.out.println("Update time: " + writeResult.getUpdateTime());
	}

	private void readDocumentToMap() throws InterruptedException, ExecutionException {
		DocumentReference docRef = this.firestore.document("books/book");

		ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = docRef.get();

		DocumentSnapshot document = documentSnapshotApiFuture.get();

		System.out.println("read: " + document.getData());
	}

	private void readDocumentToObject() throws InterruptedException, ExecutionException {
		ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = this.firestore.document("books/book").get();

		Book book = documentSnapshotApiFuture.get().toObject(Book.class);

		System.out.println("read: " + book);
	}

}