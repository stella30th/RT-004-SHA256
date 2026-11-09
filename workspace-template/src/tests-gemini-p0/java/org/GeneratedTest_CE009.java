package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE009 {

@Test
public void testDepositAndInitialBalance() {
    BankAccount account = new BankAccount(100);
    assertEquals(100, account.viewBalance());

    int newBalance = account.deposit(50);
    assertEquals(150, newBalance);
    assertEquals(150, account.viewBalance());
}

@Test(expected = IllegalArgumentException.class)
public void testDepositNegativeAmountThrowsException() {
    BankAccount account = new BankAccount();
    account.deposit(-10);
}

@Test
public void testWithdrawValidAmount() {
    BankAccount account = new BankAccount(200);
    int balance = account.withdraw(80);
    assertEquals(120, balance);
    assertEquals(120, account.viewBalance());
}

@Test(expected = IllegalArgumentException.class)
public void testWithdrawMoreThanBalanceThrowsException() {
    BankAccount account = new BankAccount(50);
    account.withdraw(100);
}

@Test
public void testTransferBetweenAccounts() {
    BankAccount sender = new BankAccount(500);
    BankAccount receiver = new BankAccount(100);

    sender.transfer(receiver, 200);

    assertEquals(300, sender.viewBalance());
    assertEquals(300, receiver.viewBalance());
}

}
