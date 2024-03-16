DROP TYPE IF EXISTS challenge_status CASCADE;
CREATE TYPE challenge_status AS ENUM ('UPCOMING', 'ACTIVE', 'EXPIRED');
DROP TYPE IF EXISTS challenge_goal CASCADE;
CREATE TYPE challenge_goal AS ENUM ('KILLS', 'ASSISTS', 'CS', 'VISION_SCORE', 'GOLD_EARNED', 'DAMAGE_DEALT', 'TURRET_KILLS', 'OBJECTIVE_KILLS', 'BARON_KILL', 'DRAGON_KILL', 'HERALD_KILL', 'INHIBITOR_KILL', 'WIN');
CREATE TYPE challenge_type AS ENUM ('DAILY', 'BOUNTY', 'WEEKLY', 'COMPETITIVE');

-- No need to modify the ENUM type and other tables not related directly to the changes

-- Adjust the personal_challenges table
DROP TABLE IF EXISTS personal_challenges CASCADE;
CREATE TABLE personal_challenges (
                                     id SERIAL PRIMARY KEY,
                                     challenge_goal challenge_goal NOT NULL,
                                     goal_value INTEGER NOT NULL,
                                     description TEXT,
                                     reward INTEGER,
                                     start_date TIMESTAMP,
                                     end_date TIMESTAMP,
                                     completed BOOLEAN DEFAULT FALSE,
                                     status challenge_status DEFAULT 'UPCOMING',
                                     user_id INTEGER,
                                     FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                     type challenge_type NOT NULL
);

-- Note: Ensure the users table and others are correctly set up as before


DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (
                        id SERIAL PRIMARY KEY,
                        username VARCHAR(50) UNIQUE NOT NULL,
                        password VARCHAR(64) NOT NULL,
                        email VARCHAR(100) UNIQUE NOT NULL,
                        summoner_name VARCHAR(100),
                        summoner_tag VARCHAR(10),
                        currency_balance INTEGER DEFAULT 0,
                        personal_challenge_id INTEGER,
                        FOREIGN KEY (personal_challenge_id) REFERENCES personal_challenges(id) ON DELETE CASCADE


);





DROP TABLE IF EXISTS challenges CASCADE;
CREATE TABLE challenges (
                            id SERIAL PRIMARY KEY,
                            challenge_goal challenge_goal NOT NULL,
                            goal_value INTEGER NOT NULL,
                            description TEXT,
                            reward INTEGER,
                            start_date TIMESTAMP,
                            end_date TIMESTAMP,
                            status challenge_status DEFAULT 'UPCOMING',
                            type challenge_type NOT NULL
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
