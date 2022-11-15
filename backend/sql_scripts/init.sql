CREATE TABLE users (
    userId serial PRIMARY KEY,
    lastName varchar(255),
    firstName varchar(255),
    createdAt dateTime DEFAULT now(),
    updatedAt dateTime DEFAULT now()
);

INSERT INTO users(lastName, firstName)
VALUES("Doe", "John"),
("Smith", "Danny"),
("Smith", "Harry");
