CREATE TABLE users (
    userId serial PRIMARY KEY,
	uuid varchar(255) UNIQUE,
    lastName varchar(255),
    firstName varchar(255),
    createdAt dateTime DEFAULT now(),
    updatedAt dateTime DEFAULT now()
);

INSERT INTO users(uuid, lastName, firstName)
VALUES("6ba7b811-9dad-11d1-80b4-00c04fd430c8", "Doe", "John"),
("f91728f0-7218-44fc-8cb6-554b33b4af8d", "Smith", "Danny"),
("47c3f3ad-16dc-445f-bcc0-086c046c5d84", "Smith", "Harry");
