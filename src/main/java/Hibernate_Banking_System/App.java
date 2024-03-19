package Hibernate_Banking_System;

/**
 * Hello world!
 *
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import Hibernate_Banking_System.User.UserRepository;

public class BankingApp {

	    @PersistenceContext
	    private static EntityManager entityManager;

	    public static void main(String[] args) {
	        // Initialize EntityManagerFactory and get EntityManager
			EntityManagerFactory entityManager = Persistence.createEntityManagerFactory("products");

	        UserRepository userRepository = new UserRepository();
	        AccountRepository accountRepository = new AccountRepository();

	        // Use userRepository and accountRepository for operations
	    }
	}
