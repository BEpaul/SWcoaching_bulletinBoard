TRUNCATE TABLE member;
INSERT INTO member(username, password, name, remark)
VALUES
    ('skku_student1','test1','student1','test student'),
    ('skku_student2','test2','student2','test student');

SET FOREIGN_KEY_CHECKS=0;

TRUNCATE TABLE board;
INSERT INTO board(id, title, remark)
VALUES
    (1,'free board','this is free board. Keep a rule and manner'),
    (2,'QnA','this is QnA board. Keep a rule and manner');

TRUNCATE TABLE post;
INSERT INTO post(board_id, title, contents, writer)
VALUES
    (1, 'I want to study Spring hard.','do not work well...', 'Paul'),
    (1, 'Would you like to study with me?','it is difficult study alone. Please let me know!','Andy');

SET FOREIGN_KEY_CHECKS=1;