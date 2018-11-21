package com.cg.bankwallet.service;

import com.cg.bankwallet.bean.AccountBean;
import com.cg.bankwallet.bean.WalletTransaction;
import com.cg.bankwallet.exception.AccountException;

public interface IAccountService {

	boolean createAccount(AccountBean accountBean) throws AccountException;

	double showBalance(int accId) throws AccountException;

	boolean deposit(int accId, double amount,WalletTransaction wt) throws AccountException;

	boolean withdraw(int accId, double amount, WalletTransaction wt) throws AccountException;

	boolean fundTransfer(int senderAccId, int targetAccId, double amount, WalletTransaction wt, WalletTransaction wt2) throws AccountException;

	AccountBean findAccount(int accId);

	boolean validData(AccountBean accountBean) throws AccountException;
}
