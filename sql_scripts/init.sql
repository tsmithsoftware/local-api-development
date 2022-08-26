CREATE TABLE users (
    userId int PRIMARY KEY,
    lastName varchar(255),
    firstName varchar(255)
);

INSERT INTO users(lastName, firstName)
VALUES("Doe", "John"),
VALUES("Smith", "Danny"),
VALUES("Smith", "Harry");
