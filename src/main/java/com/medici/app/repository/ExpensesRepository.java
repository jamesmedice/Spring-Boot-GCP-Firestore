package com.medici.app.repository;

import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.medici.app.entity.Expense;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "expenses", path = "expenses")
public interface ExpensesRepository extends FirestoreReactiveRepository<Expense> {
}
