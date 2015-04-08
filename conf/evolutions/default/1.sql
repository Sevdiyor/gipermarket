# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "Sweet" ("ID" SERIAL NOT NULL PRIMARY KEY,"NAME" VARCHAR(254) DEFAULT '' NOT NULL,"IMGURL" VARCHAR(254) DEFAULT '' NOT NULL,"COST" VARCHAR(254) DEFAULT '' NOT NULL);

# --- !Downs

drop table "Sweet";

