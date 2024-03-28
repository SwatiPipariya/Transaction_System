ackage Hibernate_Banking_System;


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
	    
		
		   
	    
	}


