-- 资源表：SID（source ID，primary key）,ID(foreign id), type, URL
CREATE TABLE source(
    sid INT PRIMARY KEY AUTO_INCREMENT,
    id INT,
    type VARCHAR(100),
    url VARCHAR(255)
);