package com.medici.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.gcp.data.firestore.repository.config.EnableReactiveFirestoreRepositories;
import org.springframework.cloud.gcp.data.firestore.transaction.ReactiveFirestoreTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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

	@Autowired
	ReactiveFirestoreTransactionManager txManager;

	@Bean
	DefaultTransactionDefinition transactionDefinition() {

		DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		transactionDefinition.setReadOnly(false);
		return transactionDefinition;
	}

	@Bean
	TransactionalOperator operator() {
		TransactionalOperator operator = TransactionalOperator.create(this.txManager, transactionDefinition());
		return operator;
	}

}
