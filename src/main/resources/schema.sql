DROP TABLE if EXISTS pet;
DROP TABLE if EXISTS category;
DROP TABLE if EXISTS tags;

CREATE TABLE category (
 id INT PRIMARY KEY,
 name VARCHAR(250) NOT NULL
 );
 
 CREATE TABLE tags (
 id INT PRIMARY KEY,
 name VARCHAR(250) NOT NULL
 );
 
 INSERT INTO category (id, name) VALUES
 (1, 'Cat'),
 (2, 'Dog');
 
 INSERT INTO tags (id, name) VALUES
 (1, 'tag1'),
 (2, 'tag2');
 
CREATE TABLE pet (
 id INT PRIMARY KEY,
 name VARCHAR(250) NOT NULL,
 status VARCHAR(20) NOT NULL,
 category VARCHAR(20) NOT NULL,
 tag VARCHAR(10) NOT NULL
 );
 
INSERT INTO pet (id, name, status, category, tag) VALUES
(1, 'Tom', 'Available', 'cat', 'tag1'),
(2, 'Tucker', 'Available', 'dog', 'tag2'),
(3, 'Maxine', 'Available', 'dog', 'tag2'),
(4, 'Nala', 'Sold', 'dog', 'tag2');