-- Table: tournament
CREATE TABLE tournament (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    location VARCHAR(255),
    entry_fee DOUBLE PRECISION,
    cash_prize_amount DOUBLE PRECISION
);

-- Table: member
CREATE TABLE member (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    email VARCHAR(255),
    membership_type VARCHAR(100),
    phone_number VARCHAR(50),
    membership_start_date DATE,
    membership_duration_months INTEGER
);

-- Join Table: member_tournament
CREATE TABLE member_tournament (
    member_id BIGINT NOT NULL,
    tournament_id BIGINT NOT NULL,
    PRIMARY KEY (member_id, tournament_id),
    FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE,
    FOREIGN KEY (tournament_id) REFERENCES tournament(id) ON DELETE CASCADE
);