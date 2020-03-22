package com.medici.app.repository;

import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;

import com.medici.app.entity.Book;

import reactor.core.publisher.Flux;

public interface BookRepository extends FirestoreReactiveRepository<Book> {

	Flux<Book> findByAuthor(String author);

	Flux<Book> findByYearGreaterThan(int year);

	Flux<Book> findByAuthorAndYear(String author, int year);
}
