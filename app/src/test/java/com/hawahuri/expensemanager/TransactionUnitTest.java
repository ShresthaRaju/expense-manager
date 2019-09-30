package com.hawahuri.expensemanager;

import com.hawahuri.expensemanager.impl.TransactionImpl;
import com.hawahuri.expensemanager.models.Transaction;
import com.hawahuri.expensemanager.response.TransactionResponse;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TransactionUnitTest {

    private TransactionImpl transactionImpl;

    @Before
    public void setup() {
        transactionImpl = new TransactionImpl();
    }

    @Test
    public void testA_emptyExpAmount_shouldNotAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Expense memo", "Expense", "5d879975de6f522844aa111e", "2019-09-25", "5d90c4828fad49338c15706c");
        TransactionResponse transactionResponse = transactionImpl.addNewTransaction(newTransaction);
        assertNull(transactionResponse);
    }

    @Test
    public void testB_negativeExpAmount_shouldNotAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Expense memo", "Expense", "5d879975de6f522844aa111e", "2019-09-25", "5d90c4828fad49338c15706c", -100);
        TransactionResponse transactionResponse = transactionImpl.addNewTransaction(newTransaction);
        assertNull(transactionResponse);
    }

    @Test
    public void testC_zeroExpAmount_shouldNotAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Expense memo", "Expense", "5d879975de6f522844aa111e", "2019-09-25", "5d90c4828fad49338c15706c", 0);
        TransactionResponse transactionResponse = transactionImpl.addNewTransaction(newTransaction);
        assertNull(transactionResponse);
    }

    @Test
    public void testD_validExpAmount_shouldAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Expense memo", "Expense", "5d879975de6f522844aa111e", "2019-09-25", "5d90c4828fad49338c15706c", 100);
        TransactionResponse transactionResponse = transactionImpl.addNewTransaction(newTransaction);
        assertEquals(100.0, transactionResponse.getTransaction().getAmount());
    }

    @Test
    public void testE_emptyIncAmount_shouldNotAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Income memo", "Income", "5d879975de6f522844aa111e", "2019-09-25", "5d90c4828fad49338c15706c");
        TransactionResponse transactionResponse = transactionImpl.addNewTransaction(newTransaction);
        assertNull(transactionResponse);
    }

    @Test
    public void testF_negativeIncAmount_shouldNotAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Income memo", "Income", "5d879975de6f522844aa111e", "2019-09-25", "5d90c4828fad49338c15706c", -100);
        TransactionResponse transactionResponse = transactionImpl.addNewTransaction(newTransaction);
        assertNull(transactionResponse);
    }

    @Test
    public void testG_zeroIncAmount_shouldNotAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Income memo", "Income", "5d879975de6f522844aa111e", "2019-09-25", "5d90c4828fad49338c15706c", 0);
        TransactionResponse transactionResponse = transactionImpl.addNewTransaction(newTransaction);
        assertNull(transactionResponse);
    }

    @Test
    public void testH_validIncAmount_shouldAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Income memo", "Income", "5d879975de6f522844aa111e", "2019-09-25", "5d90c4828fad49338c15706c", 100);
        TransactionResponse transactionResponse = transactionImpl.addNewTransaction(newTransaction);
        assertEquals(100.0, transactionResponse.getTransaction().getAmount());
    }

    @Test
    public void testI_emptyCategory_shouldNotAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Income memo", "Income", "5d879975de6f522844aa111e", "2019-09-25", "", 100);
        TransactionResponse transactionResponse = transactionImpl.addNewTransaction(newTransaction);
        assertNull(transactionResponse);
    }

    @Test
    public void testJ_transactionSize() {
        TransactionResponse transactionResponse = transactionImpl.getTransactions("5d879975de6f522844aa111e");
        assertEquals(2, transactionResponse.getMyTransactions().size());
    }

}
