CREATE TABLE IF NOT EXISTS product(

    id SERIAL PRIMARY KEY,
    name varchar(50) not null,
    description varchar(500) not null unique,
    url_image varchar(200) not null unique,
    price decimal not null,
    category category_type
)
