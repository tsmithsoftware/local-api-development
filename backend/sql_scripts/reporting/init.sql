CREATE TABLE users (
    userId serial PRIMARY KEY,
	uuid varchar(255) UNIQUE,
    lastName varchar(255),
    firstName varchar(255),
    createdAt dateTime DEFAULT now(),
    updatedAt dateTime DEFAULT now()
);