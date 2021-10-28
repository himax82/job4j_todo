CREATE TABLE IF NOT EXISTS categories (
                                          id SERIAL PRIMARY KEY,
                                          name VARCHAR(255)
);

create table users
(
    id       serial primary key,
    email    varchar(255) not null unique,
    name     varchar(255) not null,
    password varchar(255) not null
);

CREATE TABLE IF NOT EXISTS items (
                                     id SERIAL PRIMARY KEY,
                                     description TEXT NOT NULL,
                                     created TIMESTAMP NOT NULL,
                                     done BOOLEAN NOT NULL,
                                     user_id int not null references users (id),
                                     categories_id INT REFERENCES categories(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS items_categories (
                                                item_id INT REFERENCES items(id) NOT NULL,
                                                categories_id INT REFERENCES categories(id) NOT NULL
);

