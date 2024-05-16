-- chat页面
CREATE TABLE chat (
    id INT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(255) NOT NULL,
    sessionid VARCHAR(50),
    query_params JSON  -- 或者根据实际情况选择适当的字段类型
);