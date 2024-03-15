DROP TYPE IF EXISTS challenge_status CASCADE;
CREATE TYPE challenge_status AS ENUM ('UPCOMING', 'ACTIVE', 'EXPIRED');

-- No need to modify the ENUM type and other tables not related directly to the changes

-- Adjust the personal_challenges table
DROP TABLE IF EXISTS personal_challenges CASCADE;
CREATE TABLE personal_challenges (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
                                     description TEXT,
                                     reward INTEGER,
                                     start_date TIMESTAMP,
                                     end_date TIMESTAMP,
                                     completed BOOLEAN DEFAULT FALSE,
                                     status challenge_status DEFAULT 'UPCOMING',
                                     user_id INTEGER,
                                     FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Note: Ensure the users table and others are correctly set up as before


DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (
                        id SERIAL PRIMARY KEY,
                        username VARCHAR(50) UNIQUE NOT NULL,
                        password VARCHAR(64) NOT NULL,
                        email VARCHAR(100) UNIQUE NOT NULL,
                        summoner_name VARCHAR(100),
                        currency_balance INTEGER DEFAULT 0,
                        personal_challenge_id INTEGER,
                        FOREIGN KEY (personal_challenge_id) REFERENCES personal_challenges(id) ON DELETE CASCADE,
                        status challenge_status DEFAULT 'UPCOMING'
);





DROP TABLE IF EXISTS challenges CASCADE;
CREATE TABLE challenges (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            description TEXT,
                            reward INTEGER,
                            start_date TIMESTAMP,
                            end_date TIMESTAMP
);

-- Setting up the 'rewards' table
DROP TABLE IF EXISTS rewards CASCADE;
CREATE TABLE rewards (
                         id SERIAL PRIMARY KEY,
                         type VARCHAR(50),
                         description TEXT,
                         value INTEGER,
                         challenge_id INTEGER NOT NULL,
                         FOREIGN KEY (challenge_id) REFERENCES challenges(id) ON DELETE CASCADE
);

-- Setting up the 'transactions' table
DROP TABLE IF EXISTS transactions CASCADE;
CREATE TABLE transactions (
                              id SERIAL PRIMARY KEY,
                              user_id INTEGER NOT NULL,
                              amount INTEGER,
                              date TIMESTAMP NOT NULL,
                              type VARCHAR(50),
                              description TEXT,
                              FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Creating the enum type 'role_name'
DROP TYPE IF EXISTS role_name CASCADE;
CREATE TYPE role_name AS ENUM ('ROLE_USER', 'ROLE_ADMIN');

-- Setting up the 'roles' table
DROP TABLE IF EXISTS roles CASCADE;
CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name role_name NOT NULL
);

-- Inserting initial data into 'roles' table
INSERT INTO roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN')
    ON CONFLICT (name) DO NOTHING;

-- Setting up the 'users' table

-- Setting up the 'user_roles' table
DROP TABLE IF EXISTS user_roles CASCADE;
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
