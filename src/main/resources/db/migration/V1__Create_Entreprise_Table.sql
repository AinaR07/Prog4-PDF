---- db/migration/V1__Create_Entreprise_Table.sql
CREATE TABLE entreprise (
    id serial,
    nom VARCHAR(255),
    description VARCHAR(255),
    slogan VARCHAR(255),
    adresse VARCHAR(255),
    email_contact VARCHAR(255),
    nif VARCHAR(50),
    stat VARCHAR(50),
    rcs VARCHAR(50),
    logo TEXT,
    primary key (id)
);
