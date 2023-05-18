CREATE TABLE IF NOT EXISTS USER
(
    user_id  IDENTITY    NOT NULL,
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
    campaign_id BIGINT  NOT NULL,
    date_end    DATE    not null,
    PRIMARY KEY (ads_id),
    FOREIGN KEY (user_id) REFERENCES USER (user_id)
);
