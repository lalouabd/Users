create  table user (
email varchar(200) not null primary key,
fullname varchar(300) not null ,
post varchar(500) not null,
dob date not null,
password varchar(500) not null,
gender varchar(10),
isActivated bit ,
isLocked bit,
isAdmin bit,
imageid UUID
);

insert into user values(
'root@admin.com',
'abdelwahd ait hamd',
'student',
'1998-02-05',
'$2y$10$VuvwKtztyEz505XT03kg1.pUY7RetBetmuGVB3AhPFdDLIZzk8Bnm',
'MALE',
1,
1,
1,
'358053d2-63ec-4c5d-9363-baa8f15c2a9f');

create table operations(
       id UUID primary key,
       ownerEmail varchar(200),
       operationTime date,
       operationDetails varchar (500),
       operationStatus varchar(50),

    FOREIGN KEY (ownerEmail) REFERENCES user(email)
);
