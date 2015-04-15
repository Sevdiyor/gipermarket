# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "ANY" ("ID" SERIAL NOT NULL PRIMARY KEY,"NAME" VARCHAR(254) DEFAULT '' NOT NULL,"IMGURL" VARCHAR(254) DEFAULT '' NOT NULL,"COST" VARCHAR(254) DEFAULT '' NOT NULL,"SECTION_ID" INTEGER NOT NULL);
create table "SECTIONS" ("ID" SERIAL NOT NULL PRIMARY KEY,"NAME" VARCHAR(254) DEFAULT '' NOT NULL);
alter table "ANY" add constraint "ANY_FK_SECTION_ID" foreign key("SECTION_ID") references "SECTIONS"("ID") on update NO ACTION on delete NO ACTION;

# --- !Downs

alter table "ANY" drop constraint "ANY_FK_SECTION_ID";
drop table "SECTIONS";
drop table "ANY";

