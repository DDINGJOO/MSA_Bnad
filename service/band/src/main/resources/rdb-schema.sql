-- 1) Band 테이블 생성
-- 엔티티: Band.java :contentReference[oaicite:0]{index=0}&#8203;:contentReference[oaicite:1]{index=1}
CREATE TABLE band (
                      band_id           VARCHAR(36)    PRIMARY KEY,
                      leader_id         VARCHAR(255)   NOT NULL,
                      band_name         VARCHAR(255)   NOT NULL,
                      category          VARCHAR(50),
                      preferred_region  VARCHAR(100),
                      description       TEXT,
                      created_at        DATETIME       NOT NULL,
                      modified_at       DATETIME       NOT NULL
);

-- 2) BandPositionSlot 테이블 생성
-- 엔티티: BandPositionSlot.java :contentReference[oaicite:2]{index=2}&#8203;:contentReference[oaicite:3]{index=3}
CREATE TABLE band_position_slot (
                                    id               BIGINT         AUTO_INCREMENT PRIMARY KEY,
                                    band_id          VARCHAR(36)    NOT NULL,
                                    position         VARCHAR(50)    NOT NULL,
                                    required_count   INT            NOT NULL,
                                    is_closes        BOOLEAN        DEFAULT FALSE,
                                    FOREIGN KEY (band_id) REFERENCES band(band_id) ON DELETE CASCADE
);

-- 3) BandMember 테이블 생성
-- 엔티티: BandMember.java :contentReference[oaicite:4]{index=4}&#8203;:contentReference[oaicite:5]{index=5}
CREATE TABLE band_member (
                             id             BIGINT         AUTO_INCREMENT PRIMARY KEY,
                             band_id        VARCHAR(36)    NOT NULL,
                             user_id        VARCHAR(255)   NOT NULL,
                             position       VARCHAR(50),
                             description    VARCHAR(255),
                             is_confirmed   BOOLEAN        DEFAULT FALSE,
                             confirmed_at   DATETIME,
                             joined_at      DATETIME,
                             FOREIGN KEY (band_id) REFERENCES band(band_id) ON DELETE CASCADE
);
