package com.q1.practical.q1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.annotation.PostConstruct;
import java.sql.Statement;

@RestController
public class BankController {

    static final String DB_URL = "jdbc:mysql://localhost:3306/lenden?createDatabaseIfNotExist=true";
    static final String USER = "root";
    static final String PASS = "@Arjun2801";

    @PostConstruct
    public void initDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            // Create table
            String createTableSql = "CREATE TABLE IF NOT EXISTS accounts (" +
                    "account_number INT PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "balance DOUBLE)";
            stmt.execute(createTableSql);

            // Insert sample data
            stmt.execute("INSERT IGNORE INTO accounts (account_number, name, balance) VALUES (101, 'Arjun', 5000.0)");
            stmt.execute("INSERT IGNORE INTO accounts (account_number, name, balance) VALUES (102, 'Receiver', 1000.0)");
        } catch (SQLException e) {
            System.out.println("Failed to initialize database table: " + e.getMessage());
        }
    }

    @GetMapping("/q1/transfer")
    public String transfer(@RequestParam("account_number") int fromAccount, 
                           @RequestParam("amount") double amount) {
        Connection conn = null;
        PreparedStatement checkBalanceStmt = null;
        PreparedStatement debitStmt = null;
        PreparedStatement creditStmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false);

            int toAccount = 102;

            // Check if sufficient balance exists
            String checkBalanceSql = "SELECT balance FROM accounts WHERE account_number = ?";
            checkBalanceStmt = conn.prepareStatement(checkBalanceSql);
            checkBalanceStmt.setInt(1, fromAccount);
            ResultSet rs = checkBalanceStmt.executeQuery();

            double currentBalance = 0;
            if (rs.next()) {
                currentBalance = rs.getDouble("balance");
            } else {
                conn.rollback();
                return "Transaction Failed: Source account not found.";
            }

            if (currentBalance < amount) {
                conn.rollback();
                return "Transaction Failed: Insufficient balance. Current balance is " + currentBalance;
            }

            // Debit
            String debitSql = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
            debitStmt = conn.prepareStatement(debitSql);
            debitStmt.setDouble(1, amount);
            debitStmt.setInt(2, fromAccount);
            int debitRows = debitStmt.executeUpdate();

            // Credit
            String creditSql = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
            creditStmt = conn.prepareStatement(creditSql);
            creditStmt.setDouble(1, amount);
            creditStmt.setInt(2, toAccount);
            int creditRows = creditStmt.executeUpdate();

            if (debitRows == 1 && creditRows == 1) {
                conn.commit();
                return String.format("Transaction Successful!<br>Debited %.2f from Account %d<br>Credited %.2f to Account %d", 
                                     amount, fromAccount, amount, toAccount);
            } else {
                conn.rollback();
                return "Transaction Failed: Error updating rows.";
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException re) {
                re.printStackTrace();
            }
            return "Transaction Failed: " + e.getMessage();
        } finally {
            try {
                if (checkBalanceStmt != null) checkBalanceStmt.close();
                if (debitStmt != null) debitStmt.close();
                if (creditStmt != null) creditStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    @GetMapping("/q1/balance")
    public String checkBalance(@RequestParam("account_number") int account) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM accounts WHERE account_number = ?")) {
            
            stmt.setInt(1, account);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return "Account " + account + " Balance: " + rs.getDouble("balance");
            } else {
                return "Account " + account + " not found.";
            }
        } catch (SQLException e) {
            return "Error checking balance: " + e.getMessage();
        }
    }
}
