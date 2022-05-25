package db

import scalikejdbc.{AutoSession, ConnectionPool, scalikejdbcSQLInterpolationImplicitDef}

object Seeding extends App {
  Class.forName("org.postgresql.Driver")
  ConnectionPool.singleton("jdbc:postgresql://localhost:5432/postgres", "alexsander", "Alex1999+")

  implicit val session: AutoSession.type = AutoSession

  sql"""INSERT into dott_db_test.orders (customer_name, customer_phone , address , total_spend, order_date)
  VALUES('John Smith', '55 051 98141117', '1 Market Street',  350.00, '2021-09-01 00:00:00.000');

  INSERT into dott_db_test.orders (customer_name, customer_phone , address , total_spend, order_date)
  VALUES('Kane Smith', '55 021 98141185', '2 Market Street', 180.00, '2021-07-02 00:00:00.000');

  INSERT into dott_db_test.orders (customer_name, customer_phone , address , total_spend, order_date)
  VALUES('Bane Smith', '55 011 98141189', '3 Market Street',  180.00, '2021-11-05 00:00:00.000');

  INSERT into dott_db_test.orders (customer_name, customer_phone , address , total_spend, order_date)
  VALUES('Xohn Smith', '55 081 98141187', '4 Market Street', 350.00, '2021-07-09 00:00:00.000');

  INSERT into dott_db_test.orders (customer_name, customer_phone , address , total_spend, order_date)
  VALUES('Sane Smith', '55 041 98141117', '5 Market Street',  180.00, '2021-12-03 00:00:00.000');

  INSERT into dott_db_test.orders (customer_name, customer_phone , address , total_spend, order_date)
  VALUES('Qane Smith', '55 091 98141117', '6 Market Street',  180.00, '2021-09-10 00:00:00.000');

  INSERT into dott_db_test.orders (customer_name, customer_phone , address , total_spend, order_date)
  VALUES('Tohn Smith', '55 021 98541185', '7 Market Street',  350.00, '2021-07-02 00:00:00.000');

  INSERT into dott_db_test.orders (customer_name, customer_phone , address , total_spend, order_date)
  VALUES('Pane Smith', '55 011 98141188', '8 Market Street',  180.00, '2022-01-02 00:00:00.000');

  INSERT into dott_db_test.orders (customer_name, customer_phone , address , total_spend, order_date)
  VALUES('Wane Smith', '55 092 98141176', '9 Market Street',  350.00, '2021-05-02 00:00:00.000');

  INSERT into dott_db_test.orders (customer_name, customer_phone , address , total_spend, order_date)
  VALUES('Vane Smith', '55 051 98145119', '10 Market Street',  180.00, '2021-07-02 00:00:00.000');

  INSERT into dott_db_test.orders (customer_name, customer_phone , address , total_spend, order_date)
  VALUES('Zane Smith', '55 011 88141189', '11 Market Street',  350.00, '2021-03-02 00:00:00.000');
  """.execute.apply()

  sql"""
       INSERT INTO dott_db_test.itens (cost, shipping_fee, tax_rate, order_id)
       VALUES(100, 15, 5, 9);

       INSERT INTO dott_db_test.itens (cost, shipping_fee, tax_rate, order_id)
       VALUES(100, 15, 5, 7);

       INSERT INTO dott_db_test.itens (cost, shipping_fee, tax_rate, order_id)
       VALUES(100, 15, 5, 1);

       INSERT INTO dott_db_test.itens (cost, shipping_fee, tax_rate, order_id)
       VALUES(150, 25, 5, 2);

       INSERT INTO dott_db_test.itens (cost, shipping_fee, tax_rate, order_id)
       VALUES(200, 20, 10, 1);

       INSERT INTO dott_db_test.itens (cost, shipping_fee, tax_rate, order_id)
       VALUES(200, 20, 10, 9);

       INSERT INTO dott_db_test.itens (cost, shipping_fee, tax_rate, order_id)
       VALUES(150, 25, 5, 3);

       INSERT INTO dott_db_test.itens (cost, shipping_fee, tax_rate, order_id)
       VALUES(200, 20, 10, 7);

       INSERT INTO dott_db_test.itens (cost, shipping_fee, tax_rate, order_id)
       VALUES(200, 20, 10, 4);

       INSERT INTO dott_db_test.itens (cost, shipping_fee, tax_rate, order_id)
       VALUES(100, 15, 5, 4);

       INSERT INTO dott_db_test.itens (cost, shipping_fee, tax_rate, order_id)
       VALUES(150, 25, 5, 10);

       INSERT INTO dott_db_test.itens (cost, shipping_fee, tax_rate, order_id)
       VALUES(150, 25, 5, 5);

       INSERT INTO dott_db_test.itens (cost, shipping_fee, tax_rate, order_id)
       VALUES(150, 25, 5, 8);

       INSERT INTO dott_db_test.itens (cost, shipping_fee, tax_rate, order_id)
       VALUES(150, 25, 5, 6);
     """.execute.apply()

    sql"""
        INSERT INTO dott_db_test.products (item_id, product_name, category, price, creation_date)
        VALUES(1, 'simple cell phone', 'tech', 100, '2019-01-01 00:00:00');


        INSERT INTO dott_db_test.products (item_id, product_name, category, price, creation_date)
        VALUES(2, 'simple cell phone', 'tech', 100, '2018-01-01 00:00:00');


        INSERT INTO dott_db_test.products (item_id, product_name, category, price, creation_date)
        VALUES(3, 'simple cell phone', 'tech', 100, '2018-01-01 00:00:00');


        INSERT INTO dott_db_test.products (item_id, product_name, category, price, creation_date)
        VALUES(4, 'samsung phone', 'tech', 150, '2021-05-01 00:00:00');


        INSERT INTO dott_db_test.products (item_id, product_name, category, price, creation_date)
        VALUES(5, 'iphone', 'tech', 200, '2020-01-01 00:00:00');


        INSERT INTO dott_db_test.products (item_id, product_name, category, price, creation_date)
        VALUES(6, 'iphone', 'tech', 200, '2020-09-21 00:00:00');


        INSERT INTO dott_db_test.products (item_id, product_name, category, price, creation_date)
        VALUES(7, 'samsung phone', 'tech', 150, '2020-09-21 00:00:00');


        INSERT INTO dott_db_test.products (item_id, product_name, category, price, creation_date)
        VALUES(8, 'iphone', 'tech', 200, '2021-09-21 00:00:00');


        INSERT INTO dott_db_test.products (item_id, product_name, category, price, creation_date)
        VALUES(9, 'iphone', 'tech', 200, '2020-10-18 00:00:00');


        INSERT INTO dott_db_test.products (item_id, product_name, category, price, creation_date)
        VALUES(10, 'simple cell phone', 'tech', 100, '2020-10-18 00:00:00');


        INSERT INTO dott_db_test.products (item_id, product_name, category, price, creation_date)
        VALUES(11, 'samsung phone', 'tech', 150, '2017-10-18 00:00:00');
     """.execute.apply()
}
