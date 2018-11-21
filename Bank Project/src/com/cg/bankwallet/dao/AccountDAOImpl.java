package com.cg.bankwallet.dao;

import javax.persistence.EntityManager;

import com.cg.bankwallet.bean.AccountBean;
import com.cg.bankwallet.bean.WalletTransaction;

public class AccountDAOImpl implements IAccountDAO {

	EntityManager em;

	@Override
	public boolean createAccount(AccountBean accountBean) {

		try {
			em = JPAManager.createEntityManager();

			em.getTransaction().begin();
			em.persist(accountBean);
			em.getTransaction().commit();

			JPAManager.closeResources(em);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean checkAccount(int accId) {
		try {
			em = JPAManager.createEntityManager();

			boolean isGot = em.find(AccountBean.class, accId) != null;

			JPAManager.closeResources(em);

			return isGot;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public double showBalance(int accId) {
		try {
			em = JPAManager.createEntityManager();

			AccountBean accountBean = em.find(AccountBean.class, accId);

			double balance = accountBean.getBalance();

			JPAManager.closeResources(em);

			return balance;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean deposit(int accId, double amount,WalletTransaction wt) {
		try {
			em = JPAManager.createEntityManager();

			em.getTransaction().begin();
			AccountBean accountBean = em.find(AccountBean.class, accId);
			double balance = accountBean.getBalance() + amount;
			
			accountBean.setBalance(balance);
			accountBean.addTransation(wt);
			
			em.merge(accountBean);
			em.getTransaction().commit();
			JPAManager.closeResources(em);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean withdraw(int accId, double amount,WalletTransaction wt) {
		try {
			em = JPAManager.createEntityManager();

			em.getTransaction().begin();
			AccountBean accountBean = em.find(AccountBean.class, accId);
			
			double balance = accountBean.getBalance();
			if (balance > amount) {
				balance = accountBean.getBalance() - amount;
				accountBean.setBalance(balance);
				accountBean.addTransation(wt);
				em.merge(accountBean);
				em.getTransaction().commit();
				JPAManager.closeResources(em);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean fundTransfer(int senderAccId, int targetAccId, double amount,WalletTransaction wt, WalletTransaction wt2) {

		try {
			em = JPAManager.createEntityManager();

			em.getTransaction().begin();
			AccountBean accountBean = em.find(AccountBean.class, senderAccId);
			AccountBean accountBean2 = em.find(AccountBean.class, targetAccId);
			double balance = accountBean.getBalance();
			double balance2 = accountBean2.getBalance();
			if (balance > amount) {
				
				balance = accountBean.getBalance() - amount;
				accountBean.setBalance(balance);
				accountBean.addTransation(wt);
				em.merge(accountBean);

				balance2=accountBean2.getBalance() + amount;
				accountBean2.setBalance(balance2);
				accountBean2.addTransation(wt2);
				em.merge(accountBean2);
				
				em.getTransaction().commit();
				JPAManager.closeResources(em);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public AccountBean findAccount(int accId) {
		try {
			em = JPAManager.createEntityManager();

			AccountBean accountBean = em.find(AccountBean.class, accId);

			JPAManager.closeResources(em);

			return accountBean;
		} catch (Exception e) {
			return null;
		}
	}

}
