package com.medici.app.repository;

import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;

import com.medici.app.entity.Expense;

public interface ExpensesRepository extends FirestoreReactiveRepository<Expense> {

}
