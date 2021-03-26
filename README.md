# Banking API
The Banking API will manage the bank accounts of its users. It will be managed by the Bank's employees and admins. Employees and Admins count as Standard users with additional abilities. All users must be able to update their personal information, such as username, password, first and last names, as well as email. Accounts owned by users must support withdrawal, deposit, and transfer. Transfer of funds should be allowed between accounts owned by the same user, as well as between accounts owned by different users. Standard users should be able to register and login to see their account information. They can have either Checking or Savings accounts. Employees can view all customer information, but not modify in any way. Admins can both view all user information, as well as directly modify it.

## Contributors
-Hon Pan Loi (solo)

## Roles / Responsibilities 
- Database design and construct 
- Version control 
- Front end design and implementation
- Back end implementation
- Database connectivity
- Test case writing
- QA
- Debug

## Technologies Used
* Maven - 2.22.1
* Log4j - 1.2.17
* Postgresql - 42.2.5
* Junit - 5.4.2
* Mokito - 3.7.7
* Java - 8
* Spring Tool Suite - 4.8.1.Release
* Dbeaver - 7.3.4
* Git Bash - 2.25.1

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
1. Download development tools (Spring Tool Suite, Dbeaver, Gitbash)
2. 
  
(include git clone command)
(include all environment setup steps)

> Be sure to include BOTH Windows and Unix command  
> Be sure to mention if the commands only work on a specific platform (eg. AWS, GCP)

- All the `code` required to get started
- Images of what it should look like

## Usage

> Here, you instruct other people on how to use your project after they’ve installed it. This would also be a good place to include screenshots of your project in action.

## License

This project uses the following license: [<license_name>](<link>).

