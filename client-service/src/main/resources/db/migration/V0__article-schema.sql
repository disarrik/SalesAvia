create table author
(
    id                bigint not null
        primary key,
    name              varchar(255) not null,
    surname           varchar(255) not null
);

create table article
(
    id                bigint not null
        primary key,
    author            bigint,
    content           varchar(4095),
    image_url         varchar(255),
    posted            timestamp,
    short_description varchar(1023),
    title             varchar(255)
        constraint uk571gx7oqo5xpmgocegaidlcu9
            unique,
        constraint author_fk foreign key (author) references author(id)
);