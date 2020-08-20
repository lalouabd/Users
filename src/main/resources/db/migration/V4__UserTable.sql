CREATE TABLE usertable (
         email varchar(200) not null PRIMARY KEY  ,
      fullname varchar(300) not null ,
 post varchar(500) not null,
dob date not null,
password varchar(500) not null,
gender varchar(10),
isActivated int  ,
isLocked int ,
isAdmin int,
imageid UUID
);

insert into usertable values(
'root@admin.com',
'abdelwahd ait hamd',
'student',
'1998-02-05',
'$2a$10$/r8.3koLvgrEUbEcgNOr7ewInIuhGe976d70l3EsRtgzCS8sPvdQa',
'MALE',
1,
1,
1,
'358053d2-63ec-4c5d-9363-baa8f15c2a9f');

