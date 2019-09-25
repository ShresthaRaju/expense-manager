package com.hawahuri.expensemanager;

import org.junit.Before;
import org.junit.Test;


public class TransactionUnitTest {

    private TransactionImpl transactionImpl;

    @Before
    public void setup() {
        transactionImpl = new TransactionImpl();
    }

    @Test
    public void testA_emptyExpAmount_shouldNotAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Expense memo", "Expense", "Test Expense Category", "sdg13538853134", "2019-09-25", "");
        TransactionResponse transactionResponse = transactionImpl.addNewTransaction(newTransaction);
        assertNull(transactionResponse);
    }

    @Test
    public void testB_negativeExpAmount_shouldNotAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Expense memo", "Expense", "Test Expense Category", "sdg13538853134", "2019-09-25", -100);
        TransactionResponse transactionResponse = transactionImpl.addNewTransaction(newTransaction);
        assertNull(transactionResponse);
    }

    @Test
    public void testC_validExpAmount_shouldAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Expense memo", "Expense", "Test Expense Category", "sdg13538853134", "2019-09-25", 100);
        TransactionResponse transactionResponse = transactionImpl.addNewTransaction(newTransaction);
        assertNotNull(transactionResponse);
        assertEquals(100, newTransaction.getAmount());
    }

    @Test
    public void testD_emptyIncAmount_shouldNotAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Income memo", "Income", "Test Income Category", "sdg13538853134", "2019-09-25", "");
        TransactionResponse transactionResponse = transactionImpl.addNewTransaction(newTransaction);
        assertNull(transactionResponse);
    }

    @Test
    public void testE_negativeIncAmount_shouldNotAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Income memo", "Income", "Test Income Category", "sdg13538853134", "2019-09-25", -100);
        TransactionResponse transactionResponse = transactionImpl.addNewTransaction(newTransaction);
        assertNull(transactionResponse);
    }

    @Test
    public void testF_validIncAmount_shouldAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Income memo", "Income", "Test Income Category", "sdg13538853134", "2019-09-25", 100);
        TransactionResponse transactionResponse = transactionImpl.addNewTransaction(newTransaction);
        assertNotNull(transactionResponse);
        assertEquals(100, newTransaction.getAmount());
    }

    @Test
    public void testG_emptyCategory_shouldNotAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Income memo", "Income", "", "sdg13538853134", "2019-09-25", 100);
        TransactionResponse transactionResponse = transactionImpl.addNewTransaction(newTransaction);
        assertNull(transactionResponse);
    }

    @Test
    public void testH_transactionSize() {
        TransactionResponse transactionResponse = transactionImpl.getMyTransactions();
        assertNotNull(transactionResponse);
        assertEquals(2, transactionResponse.getMyTransactions().size());
    }

}
