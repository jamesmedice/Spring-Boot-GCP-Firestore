package com.medici.app.resource;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.medici.app.entity.Expense;
import com.medici.app.repository.ExpensesRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author a73s
 *
 */
@RestController
@RequestMapping(value = "/expense")
public class FirestoreExpenseResource {

	protected Logger logger = Logger.getLogger(FirestoreExpenseResource.class.getName());

	@Autowired
	ExpensesRepository expensesRepository;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Expense expense) {

		try {

			Expense payload = expensesRepository.save(expense).block();
			return new ResponseEntity(payload, new HttpHeaders(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable String id) {

		try {

			Mono<Expense> payload = expensesRepository.findById(id);
			return new ResponseEntity(payload, new HttpHeaders(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {

		try {

			Flux<Expense> payload = expensesRepository.findAll();
			return new ResponseEntity(payload, new HttpHeaders(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public ResponseEntity<?> count() {

		try {

			Mono<Long> payload = expensesRepository.count();
			return new ResponseEntity(payload, new HttpHeaders(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
