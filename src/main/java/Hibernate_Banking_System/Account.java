package Hibernate_Banking_System;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

	@Entity
	public class Account {
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long accountNumber;
	    private String fullName;
	    private String email;
	    private double balance;
	    private String securityPin;
		
	    
	    // Constructors, getters, and setters
	    public Long getAccountNumber() {
			return accountNumber;
		}
		public void setAccountNumber(Long accountNumber) {
			this.accountNumber = accountNumber;
		}
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public double getBalance() {
			return balance;
		}
		public void setBalance(double balance) {
			this.balance = balance;
		}
		public String getSecurityPin() {
			return securityPin;
		}
		public void setSecurityPin(String securityPin) {
			this.securityPin = securityPin;
		}
		public Account(Long accountNumber, String fullName, String email, double balance, String securityPin) {
			super();
			this.accountNumber = accountNumber;
			this.fullName = fullName;
			this.email = email;
			this.balance = balance;
			this.securityPin = securityPin;
		}
		public Account() {
			super();
		}
		
		@Transactional
		public class AccountRepository {

		    @PersistenceContext
		    private EntityManager entityManager;

		    public void save(Account account) {
		        entityManager.persist(account);
		    }

		    public Account findByEmail(String email) {
		        try {
		            return entityManager.createQuery("SELECT a FROM Account a WHERE a.email = :email", Account.class)
		                    .setParameter("email", email)
		                    .getSingleResult();
		        } catch (NoResultException e) {
		            return null;
		        }
		    }

		    public boolean existsByEmail(String email) {
		        return entityManager.createQuery("SELECT COUNT(a) FROM Account a WHERE a.email = :email", Long.class)
		                .setParameter("email", email)
		                .getSingleResult() > 0;
		    }

		    // Additional methods for CRUD operations as needed
		
	}


