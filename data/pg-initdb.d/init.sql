DROP TABLE IF EXISTS expenses;
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS users;


CREATE TABLE IF NOT EXISTS users
(
    id       VARCHAR(255)         NOT NULL,
    username VARCHAR(255) NOT NULL,
    name     VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS groups
(
    id   VARCHAR(255)         NOT NULL,
    name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS members
(
    group_id VARCHAR(255) NOT NULL,
    user_id  VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS expenses
(
    id          VARCHAR(255)         NOT NULL,
    group_id    VARCHAR(255)         NOT NULL,
    user_id     VARCHAR(255)         NOT NULL,
    description VARCHAR(255) NOT NULL,
    amount      DECIMAL      NOT NULL,
    created_at  TIMESTAMP         NOT NULL DEFAULT LOCALTIMESTAMP
    );

ALTER TABLE members
DROP CONSTRAINT IF EXISTS fk_members_to_groups;
ALTER TABLE members
DROP CONSTRAINT IF EXISTS fk_members_to_users;
ALTER TABLE expenses
DROP CONSTRAINT IF EXISTS fk_expenses_to_groups;
ALTER TABLE expenses
DROP CONSTRAINT IF EXISTS fk_expenses_to_users;


ALTER TABLE users
DROP CONSTRAINT IF EXISTS pk_users;
ALTER TABLE groups
DROP CONSTRAINT IF EXISTS pk_groups;
ALTER TABLE members
DROP CONSTRAINT IF EXISTS pk_members;
ALTER TABLE expenses
DROP CONSTRAINT IF EXISTS pk_expenses;


ALTER TABLE users
    ADD CONSTRAINT pk_users PRIMARY KEY (id);
ALTER TABLE groups
    ADD CONSTRAINT pk_groups PRIMARY KEY (id);
ALTER TABLE members
    ADD CONSTRAINT pk_members PRIMARY KEY (group_id, user_id);
ALTER TABLE expenses
    ADD CONSTRAINT pk_expenses PRIMARY KEY (id, group_id, user_id);


ALTER TABLE members
    ADD CONSTRAINT fk_members_to_groups FOREIGN KEY (group_id) REFERENCES groups (id) /* [jooq ignore start] */ ON DELETE CASCADE/* [jooq ignore stop] */;
ALTER TABLE members
    ADD CONSTRAINT fk_members_to_users FOREIGN KEY (user_id) REFERENCES users (id) /* [jooq ignore start] */ ON DELETE CASCADE/* [jooq ignore stop] */;
ALTER TABLE expenses
    ADD CONSTRAINT fk_expenses_to_groups FOREIGN KEY (group_id) REFERENCES groups (id) /* [jooq ignore start] */ ON DELETE CASCADE/* [jooq ignore stop] */;
ALTER TABLE expenses
    ADD CONSTRAINT fk_expenses_to_users FOREIGN KEY (user_id) REFERENCES users (id) /* [jooq ignore start] */ ON DELETE CASCADE/* [jooq ignore stop] */;
