create table article
(
    id                bigint not null
        primary key,
    author            varchar(255),
    content           varchar(255),
    image_url         varchar(255),
    posted            timestamp,
    short_description varchar(255),
    title             varchar(255)
        constraint uk571gx7oqo5xpmgocegaidlcu9
            unique
);