create table if not exists bank
(
    id   int auto_increment
        primary key,
    name varchar(45) not null,
    constraint id_UNIQUE
        unique (id),
    constraint name_UNIQUE
        unique (name)
);

create table if not exists currency
(
    id   int auto_increment
        primary key,
    name varchar(45) not null,
    code varchar(45) not null,
    constraint code_UNIQUE
        unique (code),
    constraint id_UNIQUE
        unique (id),
    constraint name_UNIQUE
        unique (name)
);

create table if not exists user_verification
(
    id                       int auto_increment
        primary key,
    name                     varchar(255)         not null,
    surname                  varchar(255)         not null,
    passport_number          varchar(255)         not null,
    passport_photo_reference varchar(255)         not null,
    is_banned                tinyint(1) default 0 not null
);

create table if not exists user
(
    id                   int auto_increment
        primary key,
    username             varchar(255)                        not null,
    email                varchar(255)                        not null,
    password             varchar(255)                        not null,
    user_verification_id int                                 null,
    created_at           timestamp default CURRENT_TIMESTAMP not null,
    constraint user_verification_unique
        unique (user_verification_id),
    constraint user_verification_id_fk
        foreign key (user_verification_id) references user_verification (id)
);

create table if not exists bank_account
(
    id              int auto_increment
        primary key,
    card_number     int         not null,
    bank_id         int         not null,
    user_id         int         not null,
    currency_id     int         not null,
    cardholder_name varchar(45) not null,
    constraint fk_bank_account_bank
        foreign key (bank_id) references bank (id),
    constraint fk_bank_account_currency
        foreign key (currency_id) references currency (id),
    constraint fk_bank_account_user
        foreign key (user_id) references user (id)
            on delete cascade
);

create index fk_bank_account_bank_idx
    on bank_account (bank_id);

create index fk_bank_account_currency_idx
    on bank_account (currency_id);

create index fk_bank_account_user_idx
    on bank_account (user_id);

create table if not exists trade
(
    id                        int auto_increment
        primary key,
    initiator_user_id         int                                                            not null,
    responder_user_id         int                                                            null,
    is_seller                 tinyint                                                        not null,
    trade_currency_id         int                                                            not null,
    trade_currency_amount     float unsigned                                                 not null,
    exchange_currency_id      int                                                            not null,
    exchange_rate             float unsigned                                                 not null,
    status                    enum ('open', 'responded', 'closed') default 'open'            not null,
    is_confirmed_by_initiator tinyint                              default 0                 not null,
    is_confirmed_by_responder tinyint                              default 0                 not null,
    created_at                timestamp                            default CURRENT_TIMESTAMP not null,
    replied_at                timestamp                                                      null,
    closed_at                 timestamp                                                      null,
    constraint id_UNIQUE
        unique (id),
    constraint fk_trade_exchange_currency
        foreign key (exchange_currency_id) references currency (id),
    constraint fk_trade_initiator
        foreign key (initiator_user_id) references user (id),
    constraint fk_trade_responder
        foreign key (responder_user_id) references user (id),
    constraint fk_trade_trade_currency
        foreign key (trade_currency_id) references currency (id)
);

create index fk_trade_currency_idx
    on trade (trade_currency_id);

create index fk_trade_exchange_currency_idx
    on trade (exchange_currency_id);

create index fk_trade_initiator_idx
    on trade (initiator_user_id);

create index fk_trade_responder_idx
    on trade (responder_user_id);

DELIMITER //

CREATE TRIGGER trade_BEFORE_UPDATE
    BEFORE UPDATE ON trade
    FOR EACH ROW
BEGIN
    IF NEW.status = 'responded' AND OLD.status <> 'responded' THEN
        SET NEW.replied_at = CURRENT_TIMESTAMP;
    END IF;

    IF NEW.status = 'closed' AND OLD.status <> 'closed' THEN
        SET NEW.closed_at = CURRENT_TIMESTAMP;
    END IF;
END;
//

DELIMITER ;


create table if not exists trade_feedback
(
    id             int auto_increment
        primary key,
    author_user_id int          not null,
    trade_id       int          not null,
    is_positive    tinyint      not null,
    text           varchar(255) null,
    constraint id_UNIQUE
        unique (id),
    constraint fk_trade_feedback_author
        foreign key (author_user_id) references user (id),
    constraint fk_trade_feedback_trade
        foreign key (trade_id) references trade (id)
            on delete cascade
);

create index fk_trade_feedback_author_idx
    on trade_feedback (author_user_id);

create index fk_trade_feedback_trade_idx
    on trade_feedback (trade_id);

create table if not exists trade_message
(
    id         int auto_increment
        primary key,
    user_id    int                                 not null,
    trade_id   int                                 not null,
    text       varchar(255)                        null,
    media      varchar(255)                        null,
    created_at timestamp default CURRENT_TIMESTAMP not null,
    constraint id_UNIQUE
        unique (id),
    constraint fk_trade_message_trade
        foreign key (trade_id) references trade (id)
            on delete cascade,
    constraint fk_trade_message_user
        foreign key (user_id) references user (id),
    constraint CHK_Content
        check ((`text` is not null) or (`media` is not null))
);

create index fk_trade_message_trade_idx
    on trade_message (trade_id);

create index fk_trade_message_user_idx
    on trade_message (user_id);


