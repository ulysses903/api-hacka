CREATE TABLE IF NOT EXISTS customer(

    id SERIAL PRIMARY KEY,
    name varchar(50) not null,
    email varchar(50) not null unique,
    cpf varchar(11) not null unique
)
