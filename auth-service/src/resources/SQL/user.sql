CREATE TABLE sys_user (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          username VARCHAR(50) NOT NULL UNIQUE COMMENT '登录账号',
                          password VARCHAR(200) NOT NULL COMMENT 'BCrypt 加密后的密码',

                          real_name VARCHAR(100) COMMENT '真实姓名',
                          id_card VARCHAR(200) COMMENT '身份证号（AES 加密）',
                          phone VARCHAR(200) COMMENT '手机号（AES 加密）',
                          email VARCHAR(200) COMMENT '邮箱（AES 加密）',

                          phone_plain VARCHAR(20) COMMENT '手机号明文索引用于查询（只存部分，例如后4位）',
                          id_card_hash VARCHAR(100) COMMENT '身份证哈希用于唯一性验证',

                          create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                          update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
