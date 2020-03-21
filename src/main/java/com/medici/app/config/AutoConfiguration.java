package com.medici.app.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.gcp.data.firestore.repository.config.EnableReactiveFirestoreRepositories;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.medici.app.entity.Book;
import com.medici.app.entity.Expense;
import com.medici.app.repository.BookRepository;
import com.medici.app.repository.ExpensesRepository;

@Configuration
@EntityScan(basePackageClasses = { Expense.class, Book.class })
@EnableAutoConfiguration
@ConditionalOnProperty(value = "spring.cloud.gcp.firestore.enabled", matchIfMissing = true)
@EnableTransactionManagement
@EnableReactiveFirestoreRepositories(basePackageClasses = { ExpensesRepository.class, BookRepository.class })
public class AutoConfiguration {

}
