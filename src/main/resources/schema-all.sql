DROP TABLE IF EXISTS CUSTOMER;

CREATE TABLE CUSTOMER  (
    customer_id INT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    account_no VARCHAR(20),
   PRIMARY KEY (customer_id)
   );