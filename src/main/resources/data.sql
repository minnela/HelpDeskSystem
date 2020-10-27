SELECT MAX(userid)+1 FROM users;
CREATE SEQUENCE test_id_seq MINVALUE 3;
ALTER TABLE users
    ALTER userid SET DEFAULT nextval('test_id_seq');



SELECT MAX(issueid)+1 FROM issue;
CREATE SEQUENCE test_id_sequ MINVALUE 3;
ALTER TABLE issue
    ALTER issueid SET DEFAULT nextval('test_id_sequ');

DELETE FROM users WHERE userid= 6;

DELETE FROM users WHERE userid=10 ;

Insert Into users VALUES (1,'minnela','admin', '45678','admin@gmail.com','ADMIN', null);

DELETE FROM users WHERE userid=1 ;

UPDATE users
SET userrolee = 'ADMIN'
WHERE userid = 9;

Delete from users where userid =12