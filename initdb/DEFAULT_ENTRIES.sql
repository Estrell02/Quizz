INSERT INTO users (email, score, username, password) VALUES
                                                         ('Michoux@example.com', 0, 'Michoux', 'password1'),
                                                         ('Jul@example.com', 0, 'Julien', 'password2'),
                                                         ('Kel@example.com', 0, 'Kelian', 'password3');

INSERT INTO quizzes (id) VALUES (1);


INSERT INTO questions (difficulty, text, type, category, correct_answer, quiz_id)
VALUES
    ('easy', 'What is 2+2?', 'multiple choice', 'math', '4', 1);


INSERT INTO incorrect_answers (question_id, incorrect_answer)
VALUES
    (1, '3'),
    (1, '5'),
    (1, '6');


INSERT INTO questions (difficulty, text, type, category, correct_answer, quiz_id)
VALUES
    ('easy', 'The earth is flat.', 'true/false', 'science', 'false', 1);


INSERT INTO incorrect_answers (question_id, incorrect_answer)
VALUES
    (2, 'true');

INSERT INTO party (host_user_id) VALUES (1);

INSERT INTO party_user (party_id, user_id) VALUES (1, 2), (1, 3);


