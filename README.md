# HR Portal

A Spring Boot application for managing employees in an HR portal, with session-based authentication. This project uses a centralized NeonDB PostgreSQL database, meaning all users will connect to the same database, and changes made by any user (e.g., adding, updating, or deleting employees) will be reflected for all users. The frontend is served directly from the backend at `http://localhost:8080/index.html`.

## Features
- **Session-Based Authentication**: Users (HR personnel) can log in and log out using email and password.
- **Employee Management**: CRUD operations for employees (Create, Read, Update, Delete).
  - Add a new employee with name, department, email, and salary.
  - View a list of all employees.
  - Edit an existing employee’s details.
  - Delete an employee.
- **Centralized Database**: All users connect to a single NeonDB PostgreSQL database, so changes are shared across all instances of the application.
- **Frontend Integration**: The frontend is served directly from the backend at `http://localhost:8080/index.html`, eliminating the need for a separate frontend server.

## Prerequisites
Before you begin, ensure you have the following installed:
- **Java 17**: Required to run the Spring Boot application.
- **Maven**: Used to build and run the project. Alternatively, you can use the Maven Wrapper (`mvnw`) included in the project.
- **A Web Browser**: To access the frontend (e.g., Chrome, Firefox).

**Note**: You don’t need to install PostgreSQL locally because the application connects to a centralized NeonDB instance. However, you will need the database credentials, which must be obtained from the project owner.

## Setup Instructions

### 1. Clone the Repository
Clone the project from GitHub to your local machine:

```bash
git clone <your-repo-url>
cd hr-portal
```

### 2. Install Prerequisites

- **Java 17**:
  - Verify that Java 17 is installed by running:
    ```bash
    java -version
- **Maven Installation**

- Verify Maven Installation:
Run the following command to check if Maven is already installed:

```bash
mvn -version
```
### 3. Database Setup

The database has already been set up using a centralized NeonDB PostgreSQL instance. All users will connect to the same database, and any changes will be reflected for everyone.  

No action is needed from your side — the credentials and connection settings are already configured. Everything is set up and ready to use! 🚀

### 4. Run the Application

Build and run the Spring Boot application using Maven:

- **Using Maven**:  
  ```bash
  mvn spring-boot:run
- **Using Maven Wrapper (if Maven is not installed)**:
```bash
./mvnw spring-boot:run  # On Unix/Linux/Mac
mvnw.cmd spring-boot:run  # On Windows
```
  The application will start on http://localhost:8080. You should see logs in the terminal indicating that the server is running, such as:
```bash
Tomcat started on port(s): 8080 (http) with context path ''
Started HrPortalApplication in X.XXX seconds (JVM running for Y.YYY)
```
### 5. Access the Application

Open your web browser and navigate to:
http://localhost:8080/index.html

You will be directed to the login page of the HR Portal.

## Usage

### Logging In

On the login page (`http://localhost:8080/index.html`), enter these login credentials.
- **Email**: `michael.scott@dundermifflin.com`
- **Password**: `worldsbestboss`

Click the **Sign In** button.

Then you will be redirected to the employee management page (`http://localhost:8080/employees.html`).

**Note**: If these credentials don’t work, let me know so I can help resolve the issue.  

### Managing Employees

Once logged in, you’ll be on the employee management page (`employees.html`):

- **View Employees**: The page displays a table of all employees in the database, including their ID, name, department, email, and salary.

- **Add an Employee**:
  - Click the **Add Employee** button.
  - Fill in the form with the employee’s name, department, email, and salary.
  - Click **Add Employee** to save the new employee to the database.

- **Edit an Employee**:
  - In the employee table, click the **Edit** button (pencil icon) next to an employee.
  - Update the employee’s details in the form.
  - Click **Update Employee** to save the changes.

- **Delete an Employee**:
  - In the employee table, click the **Delete** button (trash icon) next to an employee.
  - Confirm the deletion in the popup.
  - The employee will be removed from the database.

**Note**: All changes (adding, updating, deleting employees) are saved to the centralized NeonDB database and will be visible to all users of the application.

### Logging Out

On the employee management page, click the **Logout** link in the top-right corner.

You will be redirected to the login page (`index.html`).

**Note**: After logging out, the `JSESSIONID` cookie will still be present in your browser, but the server-side session is invalidated, so you’ll need to log in again to access the employee management features.

## Project Structure

```bash
The project follows a standard Spring Boot structure:
hr-portal/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/hrportal/hr_portal/
│   │   │       ├── config/
│   │   │       │   ├── CorsConfig.java       # CORS configuration (currently unused)
│   │   │       │   └── SecurityConfig.java   # Spring Security configuration
│   │   │       ├── controller/
│   │   │       │   ├── AuthController.java   # Handles login/logout
│   │   │       │   └── EmployeeController.java # Handles employee CRUD operations
│   │   │       ├── model/
│   │   │       │   ├── Employee.java         # Employee entity
│   │   │       │   └── HrUser.java           # HR user entity
│   │   │       │   
│   │   │       ├── repository/
│   │   │       │   ├── EmployeeRepository.java # Repository for Employee
│   │   │       │   └── HrUserRepository.java   # Repository for HrUser
│   │   │       │   
│   │   │       ├── service/
│   │   │       │   ├── EmployeeService.java  # Business logic for employee operations
│   │   │       │   └── HrUserService.java    # Business logic for HR user authentication
│   │   │       └── HrPortalApplication.java  # Main application class
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── employees.html            # Employee management page
│   │       │   ├── index.html                # Login page
│   │       │   └── styles.css                # CSS styles
│   │       └── application.properties        # Application configuration
│   └── test/                                 # Test directory (currently minimal)
├── pom.xml                                   # Maven configuration
└── .gitignore                                # Git ignore file
```

## Notes

### Centralized Database:
- The application uses a centralized NeonDB PostgreSQL instance, so all users connect to the same database.  
- Changes made by one user (e.g., adding an employee) will be visible to all other users automatically.  
- The database setup is already complete — no changes are needed.  

---

### Session-Based Authentication:
- The application uses `HttpSession` to manage user sessions. After logging in, a `JSESSIONID` cookie is set in the browser to track the session.  
- On logout, the server-side session is invalidated, but the `JSESSIONID` cookie remains in the browser. This doesn’t affect security or functionality because the session is no longer valid on the server.  

---

### Frontend:
- The frontend is served directly from [`http://localhost:8080/index.html`](http://localhost:8080/index.html), so there’s no need for a separate frontend server.  
- API requests are made to the same origin (`localhost:8080`), meaning **CORS is not required**.  
- The `CorsConfig.java` file is present but unused in the current setup.  

---

### Security:
- **Passwords** are currently stored in **plain text** in the `hr_users` table. In the future, I'll try to integrate hash passwords using a library like `BCryptPasswordEncoder`.  
- The application allows all requests (`permitAll()` in `SecurityConfig`), but `EmployeeController` manually enforces authentication using session checks.  

---

## Troubleshooting

### Cannot Connect to Database:
- The database is already set up, and no changes are required.  
- If you experience connection issues, let me know so I can help troubleshoot.  

---

### Login Fails with "Invalid Credentials":
- Use the provided credentials to log in. If they don’t work, let me know, and I’ll look into it.  

---

### Cannot Access Employee Page After Logout:
- This is expected behavior. After logging out, you must log in again to access the employee management page.  
- The `JSESSIONID` cookie may still be present, but the server-side session is invalidated.  

---

### Error Adding Employee (Email Already Exists):
- The `email` field in the `Employee` table has a **unique constraint**.  
- If you try to add an employee with an email that already exists, the operation will fail. Use a different email address.  

---

If you encounter any other issues, check the server logs in the terminal where the application is running, or let me know, and I’ll help you troubleshoot! 🚀  
