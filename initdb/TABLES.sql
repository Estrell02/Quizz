CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       email VARCHAR(255),
                       score INT DEFAULT 0,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
);

CREATE TABLE quizzes (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         player_id BIGINT,
                         FOREIGN KEY (player_id) REFERENCES users(id)
);

-- Table pour les quizzes
CREATE TABLE quizzes (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT
);

-- Table pour les questions
CREATE TABLE questions (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           difficulty VARCHAR(255),
                           text VARCHAR(255),
                           type VARCHAR(255), -- "multiple choice" ou "true/false"
                           category VARCHAR(255),
                           correct_answer VARCHAR(255),
                           quiz_id BIGINT,
                           FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);

CREATE TABLE incorrect_answers (
                                   question_id BIGINT,
                                   incorrect_answer VARCHAR(255),
                                   FOREIGN KEY (question_id) REFERENCES questions(id)
);

);

CREATE TABLE party (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       host_user_id BIGINT NOT NULL,
                       FOREIGN KEY (host_user_id) REFERENCES users(id)
);

CREATE TABLE party_user (
                            party_id BIGINT,
                            user_id BIGINT,
                            PRIMARY KEY (party_id, user_id),
                            FOREIGN KEY (party_id) REFERENCES party(id) ON DELETE CASCADE,
                            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE party_players (
                               party_id BIGINT,
                               user_id BIGINT,
                               score INT DEFAULT 0,
                               PRIMARY KEY (party_id, user_id),
                               FOREIGN KEY (party_id) REFERENCES party(id) ON DELETE CASCADE,
                               FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
