package db

import scalikejdbc.{AutoSession, ConnectionPool, scalikejdbcSQLInterpolationImplicitDef}

object Migrations extends App {
  Class.forName("org.postgresql.Driver")
  ConnectionPool.singleton("jdbc:postgresql://localhost:5432/postgres", "alexsander", "Alex1999+")

  implicit val session: AutoSession.type = AutoSession

   sql"""
  DROP SCHEMA IF EXISTS dott_db_test CASCADE;
  CREATE SCHEMA dott_db_test;

  CREATE TABLE IF NOT EXISTS dott_db_test.orders (
    id serial not null,
    customer_name character varying(50) unique NOT NULL,
    customer_phone character varying(15) unique NOT NULL,
    address  character varying(255) unique NOT NULL,
    total_spend numeric(10,2) NOT NULL,
    order_date timestamp without time zone NOT null,
    PRIMARY KEY (id)
  );

  CREATE TABLE IF NOT EXISTS dott_db_test.itens (
    id serial NOT NULL,
    cost numeric(10,2) NOT NULL,
    shipping_fee numeric(10,2) NOT NULL,
    tax_rate numeric(10,2) NOT NULL,
    order_id integer not null,
    PRIMARY KEY (id)
  );


  CREATE TABLE IF NOT EXISTS dott_db_test.products (
    item_id integer not null,
    product_name character varying(50) NOT NULL,
    category  character varying(50) NOT NULL,
    price numeric(10,2) NOT NULL,
    creation_date timestamp without time zone NOT null
  );

  ALTER TABLE dott_db_test.itens ADD
  FOREIGN KEY (order_id) REFERENCES dott_db_test.orders (id);


  ALTER TABLE dott_db_test.products ADD CONSTRAINT fk_item_id
  FOREIGN KEY (item_id) REFERENCES dott_db_test.itens(id);
  """.execute.apply()
}
