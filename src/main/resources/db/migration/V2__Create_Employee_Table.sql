---- db/migration/V2__Create_Employee_Table.sql
CREATE TABLE employee (
    id serial,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    date_of_birth DATE,
    image TEXT,
    sexe VARCHAR(1),
    numero_matricule VARCHAR(100),
    adresse VARCHAR(255),
    email_personnel VARCHAR(255),
    email_professionnel VARCHAR(255),
    numero_cin VARCHAR(255),
    date_delivrance_cin DATE,
    lieu_delivrance_cin VARCHAR(255),
    fonction VARCHAR(255),
    nombre_enfants INTEGER,
    date_embauche DATE,
    date_depart DATE,
    categorie_socio_professionnelle VARCHAR(4),
    cnaps VARCHAR(100),
     primary key (id)
);
