package com.cg.bankwallet.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.cg.bankwallet.bean.AccountBean;
import com.cg.bankwallet.bean.CustomerBean;
import com.cg.bankwallet.bean.WalletTransaction;
import com.cg.bankwallet.exception.AccountException;
import com.cg.bankwallet.service.AccountServiceImpl;
import com.cg.bankwallet.service.IAccountService;

public class Client {

	static Scanner scanner = new Scanner(System.in);
	static IAccountService service = new AccountServiceImpl();

	public static void main(String[] args) {

		while (true) {
			System.out.println("*********Bank Wallet *********");

			System.out
					.println("1 . Create Account\n2 . Show Balance\n3 . Deposit\n4 . Withdraw\n5 . FundTransfer\n6 . Print Transaction\n7 . Exit");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				try {
					Create();
				} catch (ParseException e) {
					System.out.println(e.getMessage());
				} catch (AccountException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				try {
					showBalance();
				} catch (AccountException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				try {
					deposit();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				try {
					withdraw();
				} catch (AccountException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				try {
					fundTransfer();
				} catch (AccountException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				printTransactions();
				break;
			case 7:
				System.exit(0);
				break;
			}
		}
	}

	private static void Create() throws ParseException, AccountException,
			Exception {
		System.out.println("****** Create Account ******");
		System.out.println("Enter First Name");
		String fname = scanner.next();

		System.out.println("Enter Last Name");
		String lname = scanner.next();

		System.out.println("Enter Email id");
		String email = scanner.next();

		System.out.println("Enter Phone Number");
		String phone = scanner.next();

		System.out.println("Enter PAN number");
		String pan = scanner.next();

		System.out.println("Enter Address");
		String address = scanner.next();

		CustomerBean customerBean = new CustomerBean();
		customerBean.setAddress(address);
		customerBean.setEmailId(email);
		customerBean.setPanNum(pan);
		customerBean.setPhoneNo(phone);
		customerBean.setFirstName(fname);
		customerBean.setLastName(lname);

		int accId = (int) (Math.random() * 1000000000);

		System.out.println("Enter Date of Opening (DD/MM/YYYY)");
		String accDateInput = scanner.next();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateOfOpening = sdf.parse(accDateInput);

		System.out.println("Enter balance to create account");
		double balance = scanner.nextDouble();
 
		AccountBean accountBean = new AccountBean();
		accountBean.setAccountId(accId);
		accountBean.setBalance(balance);
		accountBean.setDateOfOpening(dateOfOpening);
		accountBean.setInitialDeposit(balance);
		accountBean.setCustomerBean(customerBean);

		boolean result = service.createAccount(accountBean);

		if (result) {
			System.out
					.println("Congratulations Customer account has been created successfully...");
		} else {
			System.out.println("Enter valid details..");
		}

	}

	private static void showBalance() throws AccountException, Exception {

		System.out.println("****** Check Balance ******");
		System.out.println("Enter Account Number or Account ID");
		int accId = scanner.nextInt();

		double balance = service.showBalance(accId);

		System.out.println(balance);

	}

	private static void deposit() throws Exception {

		System.out.println("****** Deposit Amount ******");
		System.out.println("Enter Account Number or Account ID");
		int accId = scanner.nextInt();

		System.out.println("Enter Amount to Deposit");
		double amount = scanner.nextDouble();

		WalletTransaction wt = new WalletTransaction();
		int transactionId = (int) (Math.random() * 10000000);
		wt.setTransactionId(transactionId);
		wt.setTransactionType(1);
		wt.setAccountId(accId);
		wt.setTransactionDate(new Date());
		wt.setTransactionAmt(amount);
		wt.setBeneficiaryAccountId(accId);

		boolean result = service.deposit(accId, amount, wt);

		if (result) {
			System.out.println("Deposit Successfully..!");

		} else {
			System.out.println("Deposit Failed.. Check Account Number");
		}
	}

	private static void withdraw() throws AccountException {
		System.out.println("****** Deposit Amount ******");
		System.out.println("Enter Account Number or Account ID");
		int accId = scanner.nextInt();

		System.out.println("Enter Amount to withdraw");
		double amount = scanner.nextDouble();

		WalletTransaction wt = new WalletTransaction();
		int transactionId = (int) (Math.random() * 10000000);
		wt.setTransactionId(transactionId);
		wt.setTransactionType(2);
		wt.setTransactionDate(new Date());
		wt.setAccountId(accId);
		wt.setTransactionAmt(amount);
		wt.setBeneficiaryAccountId(accId);

		boolean result = service.withdraw(accId, amount, wt);

		if (result) {
			System.out.println("Withdraw Successfull..!");
		} else {
			System.out
					.println("Withdraw Failed.. Check Account Number and Amount");
		}

	}

	private static void fundTransfer() throws AccountException {

		System.out.println("Enter Account ID to Transfer Money From");
		int senderAccId = scanner.nextInt();

		System.out.println("Enter Account ID to Transfer Money to");
		int targetAccId = scanner.nextInt();

		System.out.println("Enter amount that you want to transfer");
		double amount = scanner.nextDouble();

		WalletTransaction wt = new WalletTransaction();
		int transactionId = (int) (Math.random() * 10000000);
		wt.setTransactionId(transactionId);
		wt.setTransactionType(3);
		wt.setTransactionDate(new Date());
		wt.setAccountId(senderAccId);
		wt.setTransactionAmt(amount);
		wt.setBeneficiaryAccountId(targetAccId);

		WalletTransaction wt2 = new WalletTransaction();
		int transactionId2 = (int) (Math.random() * 10000000);
		wt.setTransactionId(transactionId2);
		wt.setTransactionType(3);
		wt.setTransactionDate(new Date());
		wt.setAccountId(targetAccId);
		wt.setTransactionAmt(amount);
		wt.setBeneficiaryAccountId(senderAccId);
		boolean result = service.fundTransfer(senderAccId, targetAccId, amount,
				wt, wt2);

		if (result) {
			System.out.println("Transfering Money from Account done");
		} else {
			System.out.println("Transfering Money from Account Failed ");
		}

	}

	private static void printTransactions() {
		System.out.println("Enter Account ID for all Transaction Details");
		int accId = scanner.nextInt();

		AccountBean accountBean = service.findAccount(accId);

		List<WalletTransaction> transactions = accountBean.getAllTransactions();
/*
		System.out.println(accountBean);
		System.out.println(accountBean.getCustomerBean());*/

		/*System.out .println("------------------------------------------------------------------");*/

		for (WalletTransaction wt : transactions) {

			String str = "";
			if (wt.getTransactionType() == 1) {
				str = str + "DEPOSIT";
			}
			if (wt.getTransactionType() == 2) {
				str = str + "WITHDRAW";
			}
			if (wt.getTransactionType() == 3) {
				str = str + "FUND TRANSFER";
			}

			str = str + "\t\t" + wt.getTransactionDate();

			str = str + "\t\t" + wt.getTransactionAmt();
			System.out.println(str);
		}

		System.out
				.println("------------------------------------------------------------------");

	}

}
