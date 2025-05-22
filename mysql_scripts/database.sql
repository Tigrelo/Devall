CREATE TABLE sites (
                       id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                       nome VARCHAR(100) NOT NULL,
                       url VARCHAR(255) NOT NULL,
                       rss VARCHAR(255),
                       criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE usuario (
                         id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         email VARCHAR(100),
                         criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE posts (
                       id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                       site_id BIGINT UNSIGNED NOT NULL,
                       autor_id BIGINT UNSIGNED NOT NULL,
                       titulo VARCHAR(255) NOT NULL,
                       resumo TEXT,
                       url VARCHAR(255) NOT NULL,
                       data_publicacao TIMESTAMP NOT NULL,
                       criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       FOREIGN KEY (site_id) REFERENCES sites(id),
                       FOREIGN KEY (autor_id) REFERENCES usuario(id)
);

CREATE TABLE click (
                       id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                       post_id BIGINT UNSIGNED NOT NULL,
                       clicked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (post_id) REFERENCES posts(id)
);
