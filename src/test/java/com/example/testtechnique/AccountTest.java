package com.example.testtechnique;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class AccountTest {
    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    @Test
    void deposit() {
        account.deposit(1000, LocalDate.of(2023, 1, 10));
        account.deposit(2000, LocalDate.of(2023, 1, 13));
        String expectedOutput = "Date       | Amount | Balance\n" +
                "2023-01-10 | 1000 | 1000\n" +
                "2023-01-13 | 2000 | 3000\n";
        assertEquals(expectedOutput, account.printStatement());
    }

    @Test
    void withdraw() {
        account.deposit(1500, LocalDate.of(2023, 1, 10));
        account.withdraw(500, LocalDate.of(2023, 1, 11));
        String expectedOutput = "Date       | Amount | Balance\n" +
                "2023-01-10 | 1500 | 1500\n" +
                "2023-01-11 | -500 | 1000\n";
        assertEquals(expectedOutput, account.printStatement());
    }

    @Test
    void printStatement() {
        account.deposit(1000, LocalDate.of(2023, 1, 10));
        account.deposit(2000, LocalDate.of(2023, 1, 13));
        account.withdraw(500, LocalDate.of(2023, 1, 14));
        String expectedOutput = "Date       | Amount | Balance\n" +
                "2023-01-10 | 1000 | 1000\n" +
                "2023-01-13 | 2000 | 3000\n" +
                "2023-01-14 | -500 | 2500\n";
        assertEquals(expectedOutput, account.printStatement());
    }

    // Additional tests for thorough validation
    @Test
    void testDeposit() {
        account.deposit(500, LocalDate.of(2023, 1, 10));
        assertFalse(account.printStatement().isEmpty());
    }

    @Test
    void testWithdraw() {
        account.deposit(500, LocalDate.of(2023, 1, 10)); // ensure there is an amount to withdraw
        account.withdraw(200, LocalDate.of(2023, 1, 11));
        assertTrue(account.printStatement().contains("-200"));
    }

    @Test
    void testPrintStatement() {
        account.deposit(100, LocalDate.of(2023, 1, 10));
        account.withdraw(50, LocalDate.of(2023, 1, 11));
        String output = account.printStatement();
        assertAll("Should test multiple aspects in one print statement",
                () -> assertTrue(output.contains("100")),
                () -> assertTrue(output.contains("-50")),
                () -> assertTrue(output.contains("50")));
    }
}
