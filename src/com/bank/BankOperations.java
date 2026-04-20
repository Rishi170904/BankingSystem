package com.bank;

import java.sql.*;
import java.util.Scanner;

public class BankOperations {

    Scanner sc = new Scanner(System.in);

    public void createAccount() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Account Number: ");
            int accNo = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Initial Balance: ");
            double balance = sc.nextDouble();

            String query = "INSERT INTO accounts VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, accNo);
            ps.setString(2, name);
            ps.setDouble(3, balance);

            ps.executeUpdate();

            System.out.println("Account Created!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deposit() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Account Number: ");
            int accNo = sc.nextInt();

            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();

            String query = "UPDATE accounts SET balance = balance + ? WHERE account_number=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setDouble(1, amount);
            ps.setInt(2, accNo);

            ps.executeUpdate();

            System.out.println("✅ Deposit Successful!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void withdraw() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Account Number: ");
            int accNo = sc.nextInt();

            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();

            String query = "UPDATE accounts SET balance = balance - ? WHERE account_number=? AND balance >= ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setDouble(1, amount);
            ps.setInt(2, accNo);
            ps.setDouble(3, amount);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Withdrawal Successful!");
            else
                System.out.println("Insufficient Balance!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkBalance() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Account Number: ");
            int accNo = sc.nextInt();

            String query = "SELECT balance FROM accounts WHERE account_number=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, accNo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Balance: ₹" + rs.getDouble("balance"));
            } else {
                System.out.println("Account Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayAll() {

        try {
            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM accounts");

            System.out.println("\n---- All Accounts ----");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("account_number") + " | "
                        + rs.getString("account_holder") + " | ₹"
                        + rs.getDouble("balance"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}