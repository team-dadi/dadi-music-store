CREATE DATABASE IF NOT EXISTS `dadi`;

create table `dadi`.album (id integer not null auto_increment, name varchar(255), price double precision, primary key (id)) engine=MyISAM;
create table `dadi`.artist (id integer not null auto_increment, name varchar(255), primary key (id)) engine=MyISAM;
create table `dadi`.genre (id integer not null auto_increment, name varchar(255), primary key (id)) engine=MyISAM;
create table `dadi`.song (id integer not null auto_increment, name varchar(255), album_id integer, artist_id integer, genre_id integer, primary key (id)) engine=MyISAM;
alter table `dadi`.song add constraint FKrcjmk41yqj3pl3iyii40niab0 foreign key (album_id) references album (id);
alter table `dadi`.song add constraint FKa21ft97nj7thwrp5d31xdaxr foreign key (artist_id) references artist (id);
alter table `dadi`.song add constraint FK3kr980xhy18ojchq1ekbevypy foreign key (genre_id) references genre (id);