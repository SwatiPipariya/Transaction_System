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
	public class User {
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String fullName;
	    private String email;
	    private String password;
		
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
		public User(Long id, String fullName, String email, String password) {
			super();
			this.id = id;
			this.fullName = fullName;
			this.email = email;
			this.password = password;
		}
		public User() {
			super();
		}
		@Override
		public String toString() {
			return "User [id=" + id + ", fullName=" + fullName + ", email=" + email + ", password=" + password + "]";
		}
	    
		@Transactional
		public class UserRepository {

		    @PersistenceContext
		    private EntityManager entityManager;

		    public void save(User user) {
		        entityManager.persist(user);
		    }

		    public User findByEmailAndPassword(String email, String password) {
		        try {
		            return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class)
		                    .setParameter("email", email)
		                    .setParameter("password", password)
		                    .getSingleResult();
		        } catch (NoResultException e) {
		            return null;
		        }
		    }

		    public boolean existsByEmail(String email) {
		        return entityManager.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
		                .setParameter("email", email)
		                .getSingleResult() > 0;
		    }
		}

	    
	}


