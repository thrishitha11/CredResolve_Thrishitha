# CredResolve_Thrishitha
# Expense Sharing Application (Splitwise-like Backend)

## Overview

This project is a simplified **Expense Sharing backend application** inspired by Splitwise. It allows users to create shared expenses, split them using different strategies, and track who owes whom. The focus of this project is **backend design, business logic, and clean architecture**, not UI.

The application is implemented using **Java and Spring Boot** and exposes REST APIs to interact with the system.

---

## Features

* Add shared expenses
* Supported split type:

  * Equal split
* Track balances (who owes whom)
* View balances per user
* In-memory data storage (no database required)

---

## Tech Stack

* **Language:** Java 17
* **Framework:** Spring Boot
* **Build Tool:** Maven
* **Server:** Embedded Tomcat
* **API Testing:** Browser / curl / Postman (optional)
* **IDE:** Eclipse 

---

## Project Structure

```
expense-sharing-app
│
├── src/main/java/com/example/expense
│   ├── ExpenseSharingApplication.java
│   ├── ExpenseController.java
│   └── ExpenseRequest.java
│
├── src/main/resources
│   └── application.properties
│
└── pom.xml
```

---

## How to Run the Application

### Step 1: Import Project

1. Extract the ZIP file
2. Open **Eclipse**
3. Go to **File → Import → Existing Maven Projects**
4. Select the extracted project folder
5. Click **Finish**

### Step 2: Run the Application

* Navigate to:

  ```
  src/main/java/com/example/expense/ExpenseSharingApplication.java
  ```
* Right-click → **Run As → Java Application**

The application will start on:

```
http://localhost:8080
```

---

## API Endpoints

### 1. Add Expense

**POST** `/expenses`

**Request Body (JSON):**

```json
{
  "paidBy": "A",
  "amount": 300,
  "users": ["A", "B", "C"]
}
```

**Description:**

* Adds an expense paid by one user
* Splits the amount equally among all users

---

### 2. View Balances

**GET** `/expenses/balances`

**Response (Example):**

```json
{
  "B": { "A": 100.0 },
  "C": { "A": 100.0 }
}
```

**Meaning:**

* User B owes A ₹100
* User C owes A ₹100

---

## How Balance Calculation Works

1. The payer pays the full amount
2. Each participant owes an equal share
3. Balances are stored as:

   ```
   debtor → creditor → amount
   ```
4. Only non-zero balances are stored

This approach ensures clarity and avoids unnecessary complexity.

---

## Testing the APIs

 Browser (GET only)

```
http://localhost:8080/expenses/balances
```



**View Balances:**

```bash
curl http://localhost:8080/expenses/balances
```

---

## Design Decisions

* Used REST APIs to keep the system modular and testable
* Stored balances in-memory for simplicity
* Focused on business logic instead of UI
* Followed controller–service separation principles

---

## Limitations

* Only equal split is currently supported
* Data is lost on application restart
* No authentication or authorization

---

## Future Enhancements

* Support Exact and Percentage splits
* Add balance simplification
* Add settle-up functionality
* Persist data using a database (H2 / MySQL)
* Add user and group management

---

## Author Notes

This project was built to demonstrate **backend system design**, **clean code structure**, and **problem-solving approach** for an engineering design assignment.
