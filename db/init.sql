CREATE DATABASE  IF NOT EXISTS `tues` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `tues`;

DROP TABLE IF EXISTS `challenges`;
CREATE TABLE challenges (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,
                         reward INTEGER,
                         start_date TIMESTAMP,
                         end_date TIMESTAMP
);

-- Creating the 'rewards' table
DROP TABLE IF EXISTS `rewards`;
CREATE TABLE rewards (
                         id SERIAL PRIMARY KEY,
                         type VARCHAR(50),
                         description TEXT,
                         value INTEGER,
                         challenge_id INTEGER NOT NULL,
                         FOREIGN KEY (challenge_id) REFERENCES challenges(id) ON DELETE CASCADE
);

-- Creating the 'transactions' table
DROP TABLE IF EXISTS `transactions`;
CREATE TABLE transactions (
                         id SERIAL PRIMARY KEY,
                         user_id INTEGER NOT NULL,
                         amount INTEGER,
                         date TIMESTAMP NOT NULL,
                         type VARCHAR(50),
                         description TEXT,
                         FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `roles`;
CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(20) UNIQUE NOT NULL
);

INSERT INTO roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN')
    ON CONFLICT (name) DO NOTHING;


DROP TABLE IF EXISTS `users`;
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(64) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       summoner_name VARCHAR(100),
                       currency_balance INTEGER DEFAULT 0
);

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE user_roles (
                            user_id INTEGER NOT NULL,
                            role_id INTEGER NOT NULL,
                            CONSTRAINT fk_user
                                FOREIGN KEY(user_id)
                                    REFERENCES users(id)
                                    ON DELETE CASCADE,
                            CONSTRAINT fk_role
                                FOREIGN KEY(role_id)
                                    REFERENCES roles(id)
                                    ON DELETE CASCADE,
                            PRIMARY KEY (user_id, role_id)
);