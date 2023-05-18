CREATE TABLE IF NOT EXISTS USER
(
    user_id  BIGINT      NOT NULL,
    token    VARCHAR     NOT NULL,
    login    VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    fio      VARCHAR     NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS ADS
(
    ads_id      BIGINT  NOT NULL,
    user_id     BIGINT  NOT NULL,
    ads_content VARCHAR NOT NULL,
    PRIMARY KEY (ads_id),
    FOREIGN KEY (user_id)
);
