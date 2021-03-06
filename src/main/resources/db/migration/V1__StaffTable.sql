CREATE TABLE staff(
    id int not null primary key,
    birth_day date,
    birth_place varchar(500),
    sex         varchar(10),
    sit_fam     varchar(100),
    nationalite  varchar(100),
    date_embauche varchar(100),
    cin varchar(50),
    section_analytique varchar(500),
    grade       varchar(100),
    functio_n   varchar(500),
    post        varchar(300),
    categorie   varchar(100),
    echelon     int,
    ent_effect  varchar(100),
    regime_retraite varchar(300),
    affil_recore int,
    date_der_promo varchar(100),
    image_link UUID

);
