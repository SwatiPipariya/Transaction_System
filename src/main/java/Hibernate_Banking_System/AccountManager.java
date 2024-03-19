package Hibernate_Banking_System;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Scanner;


public class AccountManager {

	    private EntityManager entityManager;
	    private Scanner scanner;

	    public AccountManager(EntityManager entityManager, Scanner scanner) {
	        this.entityManager = entityManager;
	        this.scanner = scanner;
	    }

	    public void creditMoney(long accountNumber) {
	        try {
	            entityManager.getTransaction().begin();
	            System.out.println("Enter Amount: ");
	            double amount = scanner.nextDouble();
	            scanner.nextLine();
	            System.out.println("Enter Security Pin");
	            String securityPin = scanner.nextLine();

	            Query query = entityManager.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber AND a.securityPin = :securityPin");
	            query.setParameter("accountNumber", accountNumber);
	            query.setParameter("securityPin", securityPin);
	            Account account = (Account) query.getSingleResult();

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

	            Query query = entityManager.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber AND a.securityPin = :securityPin");
	            query.setParameter("accountNumber", accountNumber);
	            query.setParameter("securityPin", securityPin);
	            Account account = (Account) query.getSingleResult();

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

	            Query senderQuery = entityManager.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :senderAccountNumber AND a.securityPin = :securityPin");
	            senderQuery.setParameter("senderAccountNumber", senderAccountNumber);
	            senderQuery.setParameter("securityPin", securityPin);
	            Account senderAccount = (Account) senderQuery.getSingleResult();

	            Account receiverAccount = entityManager.find(Account.class, receiverAccountNumber);

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

	            Query query = entityManager.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber AND a.securityPin = :securityPin");
	            query.setParameter("accountNumber", accountNumber);
	            query.setParameter("securityPin", securityPin);
	            Account account = (Account) query.getSingleResult();

	            if (account != null) {
	                System.out.println("Balance: " + account.getBalance());
	            } else {
	                System.out.println("Invalid Pin!");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    
	}


