---- db/migration/V3__Create_Telephones_Table.sql
CREATE TABLE telephones (
    id serial,
    employee_id BIGINT,
    entreprise_id BIGINT,
    numero BIGINT,
    FOREIGN KEY (employee_id) REFERENCES employee (id),
    FOREIGN KEY (entreprise_id) REFERENCES entreprise (id),
    primary key (id)
);
