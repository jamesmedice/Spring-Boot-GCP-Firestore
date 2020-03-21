package com.medici.app.entity;

import org.springframework.cloud.gcp.data.firestore.Document;

import com.google.cloud.firestore.annotation.DocumentId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collectionName = "books")
public class Book {

	@DocumentId
	Long id;

	String title;

	String author;

	int year;
}
