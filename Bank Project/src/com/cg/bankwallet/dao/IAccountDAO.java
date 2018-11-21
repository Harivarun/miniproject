package com.cg.bankwallet.dao;

import com.cg.bankwallet.bean.AccountBean;
import com.cg.bankwallet.bean.WalletTransaction;

public interface IAccountDAO {

	boolean createAccount(AccountBean accountBean);

	boolean checkAccount(int accId);

	double showBalance(int accId);

	boolean deposit(int accId, double amount,WalletTransaction wt);

	boolean withdraw(int accId, double amount, WalletTransaction wt);

	boolean fundTransfer(int senderAccId, int targetAccId, double amount, WalletTransaction wt, WalletTransaction wt2);

	AccountBean findAccount(int accId);
	
}
