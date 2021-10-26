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
                                     user_id int not null references users (id)
);

