DROP TABLE IF EXISTS Accounts;

CREATE TABLE Accounts (
  AccountId INT AUTO_INCREMENT  PRIMARY KEY,
  CustomerId INTEGER(10) NOT NULL,
  Amount INTEGER(10) NOT NULL
);

CREATE TABLE Transactions (
  TransactionId INT AUTO_INCREMENT  PRIMARY KEY,
  AccountId INT NOT NULL,
  Amount INTEGER(10) NOT NULL,
  Transaction_Type INT(1) NOT NULL
);

INSERT INTO Accounts (CustomerId, Amount) VALUES
  (1234, 0),
  (1235, 0);