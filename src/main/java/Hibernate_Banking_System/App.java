package Hibernate_Banking_System;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class App {

    @PersistenceContext
    private static EntityManager entityManager;

    public static void main(String[] args) {
        // Initialize EntityManagerFactory and get EntityManager
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankUnit");
//        EntityManager entityManager = emf.createEntityManager();

        entityManager = emf.createEntityManager(); // Assigning to the static field entityManager

        Scanner scanner = new Scanner(System.in);

//        UserRepository userRepository = new UserRepository(entityManager);
//        AccountRepository accountRepository = new AccountRepository(entityManager);

        System.out.println("Good afternoon");

        // Use userRepository and accountRepository for operations
        
//        public void registerUser(String fullName, String email, String password) {
            UserRepository user = new UserRepository(entityManager);
            user.setFullName("Priya");
            user.setEmail("priya@gmail.com");
            user.setPassword("priya123@");
            save(user);
            
         // Close scanner and EntityManager
            scanner.close();
            entityManager.close();
    }
    
    

	private static void save(UserRepository user) {
        entityManager.persist(user);
		
	}
//	 public void save(UserRepository user) {
//	        entityManager.persist(user);
//	    }

        
//        scanner.close();
    }






























//package Hibernate_Banking_System;
//
//import java.util.Scanner;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.PersistenceContext;
//
//import Hibernate_Banking_System.Account.AccountRepository;
//import Hibernate_Banking_System.UserRepository;
//
//
//
//public class App {
//
//    @PersistenceContext
//    private static EntityManager entityManager;
//
//    public static void main(String[] args) {
//        // Initialize EntityManagerFactory and get EntityManager
//        
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankUnit");
//        EntityManager entityManager = emf.createEntityManager();
//
//        Scanner scanner = new Scanner(System.in);
//
//        UserRepository userRepository = new UserRepository(entityManager, scanner);
//        AccountRepository accountRepository = new AccountRepository(entityManager, scanner);
//
//        System.out.println("Good afternoon");
//
//        // Use userRepository and accountRepository for operations
//    }
//}
//
//
//

































//package Hibernate_Banking_System;
//
//
//import java.util.Scanner;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.PersistenceContext;
//
//import Hibernate_Banking_System.Account.AccountRepository;
//import Hibernate_Banking_System.User.UserRepository;
//
//public class App {
//
//	    @PersistenceContext
//	    private static EntityManager entityManager;
//
//	    public static void main(String[] args) {
//	        // Initialize EntityManagerFactory and get EntityManager
//	    	
//	    	EntityManagerFactory emf = Persistence.createEntityManagerFactory( "BankUnit" );
//	    	EntityManager entitymanager = emf.createEntityManager();
//
//	        Scanner scanner = new Scanner(System.in);
//
//	        UserRepository userRepository = new UserRepository(entityManager, scanner);
//	        AccountRepository accountRepository = new AccountRepository(entityManager, scanner);
//	        
//	        System.out.println("Good afternoon");
//
//	        // Use userRepository and accountRepository for operations
//	    }
//	}
