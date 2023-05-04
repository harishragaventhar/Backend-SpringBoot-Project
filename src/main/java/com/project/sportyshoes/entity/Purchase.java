package com.project.sportyshoes.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int purchase_id;

	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne()
	@JoinColumn(name = "product_id")
	private Product product;

	@CreationTimestamp
	private Timestamp orderedDate;

	public Purchase() {
	}

	public Purchase(User user, Product product) {
		this.user = user;
		this.product = product;
	}

	public int getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}

	public Timestamp getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(Timestamp orderedDate) {
		this.orderedDate = orderedDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Timestamp getOrdered_date() {
		return orderedDate;
	}

	public void setOrdered_date(Timestamp ordered_date) {
		this.orderedDate = ordered_date;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + purchase_id + ", user=" + user + ", product=" + product + ", ordered_date="
				+ orderedDate + "]";
	}

}
