-- chat页面
CREATE TABLE chat (
		cid INT AUTO_INCREMENT PRIMARY KEY,
    uid INT,
		sessionid INT,
		`date` Date,
    question VARCHAR(255) NOT NULL,
		answer VARCHAR(255) NOT NULL
);

CREATE TABLE chathistory (
    uid INT,
		sessionid INT AUTO_INCREMENT PRIMARY KEY,
		`date` Date
);