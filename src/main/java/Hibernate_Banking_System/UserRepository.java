package Hibernate_Banking_System;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Scanner;

@Entity
public class UserRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String password;

    @PersistenceContext
    private EntityManager entityManager;
    
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Constructors, getters, and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRepository(EntityManager entityManager, Scanner scanner) {
        this.entityManager = entityManager;
    }

    public void save(UserRepository user) {
        entityManager.persist(user);
    }

    public UserRepository findByEmailAndPassword(String email, String password) {
        try {
            return entityManager.createQuery("SELECT u FROM UserRepository u WHERE u.email = :email AND u.password = :password", UserRepository.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean existsByEmail(String email) {
        return entityManager.createQuery("SELECT COUNT(u) FROM UserRepository u WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult() > 0;
    }
}



















































//package Hibernate_Banking_System;
//
//import java.util.Scanner;
//
//import javax.persistence.EntityManager;
//import javax.persistence.NoResultException;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
////import org.springframework.stereotype.Repository;
//
//
//	@Repository
//	@Transactional
//	public class UserRepository {
//
//		 @PersistenceContext
//		    private EntityManager entityManager;
//
//		    public UserRepository(EntityManager entityManager2, Scanner scanner) {
//			// TODO Auto-generated constructor stub
//		}
//
//			public void save(User user) {
//		        entityManager.persist(user);
//		    }
//
//		    public User findByEmailAndPassword(String email, String password) {
//		        try {
//		            return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class)
//		                    .setParameter("email", email)
//		                    .setParameter("password", password)
//		                    .getSingleResult();
//		        } catch (NoResultException e) {
//		            return null;
//		        }
//		    }
//
//		    public boolean existsByEmail(String email) {
//		        return entityManager.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
//		                .setParameter("email", email)
//		                .getSingleResult() > 0;
//		    }
//		}
//
//	
//

