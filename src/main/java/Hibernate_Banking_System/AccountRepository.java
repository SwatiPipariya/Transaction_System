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
public class AccountRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;
    private String fullName;
    private String email;
    private double balance;
    private String securityPin;

    @PersistenceContext
    private EntityManager entityManager;
    
    public AccountRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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

    public AccountRepository(EntityManager entityManager, Scanner scanner) {
        this.entityManager = entityManager;
    }

    public void save(AccountRepository account) {
        entityManager.persist(account);
    }

    public AccountRepository findByEmail(String email) {
        try {
            return entityManager.createQuery("SELECT a FROM AccountRepository a WHERE a.email = :email", AccountRepository.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean existsByEmail(String email) {
        return entityManager.createQuery("SELECT COUNT(a) FROM AccountRepository a WHERE a.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult() > 0;
    }

    // Additional methods for CRUD operations as needed

    public void creditMoney(long accountNumber) {
        try {
            entityManager.getTransaction().begin();
            System.out.println("Enter Amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Enter Security Pin");
            String securityPin = scanner.nextLine();

            Query query = entityManager.createQuery("SELECT a FROM AccountRepository a WHERE a.accountNumber = :accountNumber AND a.securityPin = :securityPin");
            query.setParameter("accountNumber", accountNumber);
            query.setParameter("securityPin", securityPin);
            AccountRepository account = (AccountRepository) query.getSingleResult();

            if (account != null) {
                account.setBalance(account.getBalance() + amount);
                entityManager.merge(account);
                System.out.println("Rs. " + amount + " credited Successfully");
                entityManager.getTransaction().commit();
            } else {
                System.out.println("Invalid Security Pin!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void debitMoney(long accountNumber) {
        try {
            entityManager.getTransaction().begin();
            System.out.println("Enter Amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Enter Security Pin: ");
            String securityPin = scanner.nextLine();

            Query query = entityManager.createQuery("SELECT a FROM AccountRepository a WHERE a.accountNumber = :accountNumber AND a.securityPin = :securityPin");
            query.setParameter("accountNumber", accountNumber);
            query.setParameter("securityPin", securityPin);
            AccountRepository account = (AccountRepository) query.getSingleResult();

            if (account != null) {
                double currentBalance = account.getBalance();
                if (amount <= currentBalance) {
                    account.setBalance(currentBalance - amount);
                    entityManager.merge(account);
                    System.out.println("Rs. " + amount + " debited Successfully!!");
                    entityManager.getTransaction().commit();
                } else {
                    System.out.println("Insufficient Balance!");
                }
            } else {
                System.out.println("Invalid Pin!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void transferMoney(long senderAccountNumber) {
        try {
            entityManager.getTransaction().begin();
            System.out.println("Enter Receiver Account Number: ");
            long receiverAccountNumber = scanner.nextLong();
            System.out.println("Enter Amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Enter Security Pin: ");
            String securityPin = scanner.nextLine();

            Query senderQuery = entityManager.createQuery("SELECT a FROM AccountRepository a WHERE a.accountNumber = :senderAccountNumber AND a.securityPin = :securityPin");
            senderQuery.setParameter("senderAccountNumber", senderAccountNumber);
            senderQuery.setParameter("securityPin", securityPin);
            AccountRepository senderAccount = (AccountRepository) senderQuery.getSingleResult();

            AccountRepository receiverAccount = entityManager.find(AccountRepository.class, receiverAccountNumber);

            if (senderAccount != null && receiverAccount != null) {
                double currentBalance = senderAccount.getBalance();
                if (amount <= currentBalance) {
                    senderAccount.setBalance(currentBalance - amount);
                    receiverAccount.setBalance(receiverAccount.getBalance() + amount);
                    entityManager.merge(senderAccount);
                    entityManager.merge(receiverAccount);
                    System.out.println("Rs. " + amount + " Transferred Successfully");
                    entityManager.getTransaction().commit();
                } else {
                    System.out.println("Insufficient Balance!!");
                }
            } else {
                System.out.println("Invalid Account Number or Security Pin!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getBalance(long accountNumber) {
        try {
            System.out.println("Enter Security Pin: ");
            String securityPin = scanner.nextLine();

            Query query = entityManager.createQuery("SELECT a FROM AccountRepository a WHERE a.accountNumber = :accountNumber AND a.securityPin = :securityPin");
            query.setParameter("accountNumber", accountNumber);
            query.setParameter("securityPin", securityPin);
            AccountRepository account = (AccountRepository) query.getSingleResult();

            if (account != null) {
                System.out.println("Balance: " + account.getBalance());
            } else {
                System.out.println("Invalid Pin!");
            }
       
        













































//package Hibernate_Banking_System;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Query;
//import java.util.Scanner;
//
//
//public class AccountManager {
//
//	    private EntityManager entityManager;
//	    private Scanner scanner;
//
//	    public AccountManager(EntityManager entityManager, Scanner scanner) {
//	        this.entityManager = entityManager;
//	        this.scanner = scanner;
//	    }
//
//	    public void creditMoney(long accountNumber) {
//	        try {
//	            entityManager.getTransaction().begin();
//	            System.out.println("Enter Amount: ");
//	            double amount = scanner.nextDouble();
//	            scanner.nextLine();
//	            System.out.println("Enter Security Pin");
//	            String securityPin = scanner.nextLine();
//
//	            Query query = entityManager.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber AND a.securityPin = :securityPin");
//	            query.setParameter("accountNumber", accountNumber);
//	            query.setParameter("securityPin", securityPin);
//	            Account account = (Account) query.getSingleResult();
//
//	            if (account != null) {
//	                account.setBalance(account.getBalance() + amount);
//	                entityManager.merge(account);
//	                System.out.println("Rs. " + amount + " credited Successfully");
//	                entityManager.getTransaction().commit();
//	            } else {
//	                System.out.println("Invalid Security Pin!");
//	            }
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	    }
//
//	    public void debitMoney(long accountNumber) {
//	        try {
//	            entityManager.getTransaction().begin();
//	            System.out.println("Enter Amount: ");
//	            double amount = scanner.nextDouble();
//	            scanner.nextLine();
//	            System.out.println("Enter Security Pin: ");
//	            String securityPin = scanner.nextLine();
//
//	            Query query = entityManager.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber AND a.securityPin = :securityPin");
//	            query.setParameter("accountNumber", accountNumber);
//	            query.setParameter("securityPin", securityPin);
//	            Account account = (Account) query.getSingleResult();
//
//	            if (account != null) {
//	                double currentBalance = account.getBalance();
//	                if (amount <= currentBalance) {
//	                    account.setBalance(currentBalance - amount);
//	                    entityManager.merge(account);
//	                    System.out.println("Rs. " + amount + " debited Successfully!!");
//	                    entityManager.getTransaction().commit();
//	                } else {
//	                    System.out.println("Insufficient Balance!");
//	                }
//	            } else {
//	                System.out.println("Invalid Pin!");
//	            }
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	    }
//
//	    public void transferMoney(long senderAccountNumber) {
//	        try {
//	            entityManager.getTransaction().begin();
//	            System.out.println("Enter Receiver Account Number: ");
//	            long receiverAccountNumber = scanner.nextLong();
//	            System.out.println("Enter Amount: ");
//	            double amount = scanner.nextDouble();
//	            scanner.nextLine();
//	            System.out.println("Enter Security Pin: ");
//	            String securityPin = scanner.nextLine();
//
//	            Query senderQuery = entityManager.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :senderAccountNumber AND a.securityPin = :securityPin");
//	            senderQuery.setParameter("senderAccountNumber", senderAccountNumber);
//	            senderQuery.setParameter("securityPin", securityPin);
//	            Account senderAccount = (Account) senderQuery.getSingleResult();
//
//	            Account receiverAccount = entityManager.find(Account.class, receiverAccountNumber);
//
//	            if (senderAccount != null && receiverAccount != null) {
//	                double currentBalance = senderAccount.getBalance();
//	                if (amount <= currentBalance) {
//	                    senderAccount.setBalance(currentBalance - amount);
//	                    receiverAccount.setBalance(receiverAccount.getBalance() + amount);
//	                    entityManager.merge(senderAccount);
//	                    entityManager.merge(receiverAccount);
//	                    System.out.println("Rs. " + amount + " Transferred Successfully");
//	                    entityManager.getTransaction().commit();
//	                } else {
//	                    System.out.println("Insufficient Balance!!");
//	                }
//	            } else {
//	                System.out.println("Invalid Account Number or Security Pin!!");
//	            }
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	    }
//
//	    public void getBalance(long accountNumber) {
//	        try {
//	            System.out.println("Enter Security Pin: ");
//	            String securityPin = scanner.nextLine();
//
//	            Query query = entityManager.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber AND a.securityPin = :securityPin");
//	            query.setParameter("accountNumber", accountNumber);
//	            query.setParameter("securityPin", securityPin);
//	            Account account = (Account) query.getSingleResult();
//
//	            if (account != null) {
//	                System.out.println("Balance: " + account.getBalance());
//	            } else {
//	                System.out.println("Invalid Pin!");
//	            }
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	    
//	    }
//}
//
