package com.medici.app.repository;

import java.util.List;

import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.medici.app.entity.Book;

@RepositoryRestResource(collectionResourceRel = "books", path = "book")
public interface BookRepository extends FirestoreReactiveRepository<Book> {

	List<Book> findByAuthor(String author);

	List<Book> findByYearGreaterThan(int year);

	List<Book> findByAuthorAndYear(String author, int year);
}
