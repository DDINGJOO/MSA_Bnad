CREATE TABLE band (
                      band_id VARCHAR(26) PRIMARY KEY,
                      leader_id VARCHAR(26) NOT NULL,
                      band_name VARCHAR(100) NOT NULL,
                      band_thumbnail TEXT,
                      category ENUM('ROCK', 'POP', 'INDIE', 'METAL', 'JAZZ', 'FUNK', 'BLUES', 'HIPHOP', 'CLASSIC', 'ETC') NOT NULL,
                      preferred_region VARCHAR(100),
                      comment VARCHAR(255),
                      check_full_bang BOOLEAN DEFAULT FALSE
);


CREATE TABLE band_recruit_position (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       band_id VARCHAR(26) NOT NULL,
                                       position ENUM('VOCAL', 'GUITAR', 'BASS', 'DRUM', 'KEYBOARD', 'PRODUCER', 'ETC') NOT NULL,
                                       is_closed BOOLEAN DEFAULT FALSE,
                                       description VARCHAR(255),
                                       FOREIGN KEY (band_id) REFERENCES band(band_id) ON DELETE CASCADE
);
CREATE TABLE band_member (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             band_id VARCHAR(26) NOT NULL,
                             user_id VARCHAR(26) NOT NULL,
                             position ENUM('VOCAL', 'GUITAR', 'BASS', 'DRUM', 'KEYBOARD', 'PRODUCER', 'ETC') NOT NULL,
                             joined_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (band_id) REFERENCES band(band_id) ON DELETE CASCADE
);


CREATE INDEX idx_band_category ON band(category);
CREATE INDEX idx_band_region ON band(preferred_region);
CREATE INDEX idx_band_position_band ON band_recruit_position(band_id, position);
CREATE INDEX idx_band_member_band ON band_member(band_id);
CREATE INDEX idx_band_member_user ON band_member(user_id);
