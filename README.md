# Banking API
The Banking API will manage the bank accounts of its users. It will be managed by the Bank's employees and admins. Employees and Admins count as Standard users with additional abilities. All users must be able to update their personal information, such as username, password, first and last names, as well as email. Accounts owned by users must support withdrawal, deposit, and transfer. Transfer of funds should be allowed between accounts owned by the same user, as well as between accounts owned by different users. Standard users should be able to register and login to see their account information. They can have either Checking or Savings accounts. Employees can view all customer information, but not modify in any way. Admins can both view all user information, as well as directly modify it.

## Contributors
* Hon Pan Loi (solo)

## Roles / Responsibilities 
* Database design and construct 
* Version control 
* Front end design and implementation
* Back end implementation
* Database connectivity
* Test case writing
* QA
* Debug

## Technologies Used
* Maven - 2.22.1
* Log4j - 1.2.17
* PostgreSQL - 42.2.5
* Junit - 5.4.2
* Mokito - 3.7.7
* Java - 8
* Spring Tool Suite - 4.8.1.Release
* Dbeaver - 7.3.4
* Git Bash - 2.25.1
* Language used - Java 8, SQL, XML

## User Stroies / Features
* As a user, I can login. 
* As a customer, I can apply for a new bank account with a starting balance.
* As a customer, I can view the balance of a specific account.
* As a customer, I can make a withdrawal or deposit to a specific account. 
* As the system, I reject invalid transactions. 
	* Ex:
		* A withdrawal that would result in a negative balance.
		* A deposit or withdrawal of negative money.

* As an employee, I can approve or reject an account.
* As an employee, I can view a customer's bank accounts.
* As a user, I can register for a customer account. 
* As a customer, I can post a money transfer to another account.
* As a customer, I can accept a money transfer from another account.
* An employee, I can view a log of all transactions.

## Getting Started
1. Download and install development tools. (Spring Tool Suite, Dbeaver, Gitbash)
2. Open Git bash on the location that you want your project folder to be.
3. In the Git bash CLI, enter the command 'git clone https://github.com/honpanloi/Banking-API.git'.
4. You should see the project folder is created on your computer.
5. Open Dbeaver.
6. Create a new connection with local client PostgreSQL 12.
7. Create a new schema under that connection called 'my_bank_app'
8. Right Click on the 'my_bank_app' schema. -> Tools -> Restore.
9. Select the backup file at myFirstBankApp/dump-postgres-202103261724.sql.
10. Click on local client and select PostgreSQL 12.
11. Click 'start'.
12. A schema with some dummy data should be created
13. Open Spring Tool Suite with the workspace of your choice.
14. File -> Import -> Maven -> Existing Maven Projects.
15. Navigate to the folder Banking-API where the pom.xml is located and click finish.
16. You should see a project named 'com.myCarDealer' get imported.
17. Right click on the project -> Run as -> Java application.
18. You can now use the app with the console.
 
## Usage
* After you successfully have the project running in Spring Tool Suit console, it should be very intuitive on how to use the app. You can create a new user following the instruction it gives you. You can start creating users and login with the users you created! After you loged in as a customer, you can apply for differet accounts. If you loged in as a employee, you can manage account applications as well as customer registration.


## License Information
* Open-source. Not for commercial use.
