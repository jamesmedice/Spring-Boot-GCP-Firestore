package com.medici.app.entity;

import org.springframework.cloud.gcp.data.firestore.Document;

import com.google.cloud.firestore.annotation.DocumentId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collectionName = "expenses")
public class Expense {

	@DocumentId
	String id;

	String partnumber;

	String partdescription;

	int quantity;

	double priceperitem;

	double amount;
}