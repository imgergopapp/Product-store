DROP TRIGGER IF EXISTS products_in_stock_check ON products;
DROP TRIGGER IF EXISTS carts_quantity_check ON carts;
DROP TRIGGER IF EXISTS order_completion_date_check ON orders;

DROP FUNCTION IF EXISTS products_in_stock_check;
DROP FUNCTION IF EXISTS carts_quantity_check;
DROP FUNCTION IF EXISTS order_completion_date_check;

DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS carts CASCADE;
DROP TABLE IF EXISTS transporters CASCADE;
DROP TABLE IF EXISTS orders CASCADE;

CREATE TABLE users(
	user_id SERIAL PRIMARY KEY,
	user_name VARCHAR(100),
	password VARCHAR(64),
	email VARCHAR(60),
	role VARCHAR(20) DEFAULT 'REGULAR',
	country VARCHAR(40) DEFAULT 'Not set',
	zip_code NUMERIC(4) DEFAULT 0,
	city VARCHAR(40) DEFAULT 'Not set',
	street VARCHAR(100) DEFAULT 'Not set'
);

CREATE TABLE products(
	product_id SERIAL PRIMARY KEY,
	product_name VARCHAR(100),
	category VARCHAR(40),
	properties VARCHAR(200),
	product_price INTEGER,
	in_stock INTEGER,
	sale_percentage NUMERIC(2) DEFAULT 0,
	picture_url VARCHAR(100)
);

CREATE TABLE carts(
    product_id INTEGER,
    user_id INTEGER,
    quantity INTEGER DEFAULT 1,
    products_price INTEGER
);

CREATE TABLE transporters(
 	transporter_id SERIAL PRIMARY KEY,
    transporter_company VARCHAR(40),
 	transporter_price INTEGER,
 	zip_code_range_from NUMERIC(4),
    zip_code_range_to NUMERIC(4)
 );

CREATE TABLE orders(
	order_id SERIAL PRIMARY KEY,
	transporter_id INTEGER,
	total_price INTEGER,
    order_date DATE,
    status VARCHAR(20),
    completion_date DATE
);

ALTER TABLE carts
	ADD CONSTRAINT carts_fk_product_id FOREIGN KEY (product_id) REFERENCES products(product_id),
	ADD CONSTRAINT carts_fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id);

ALTER TABLE orders
	ADD CONSTRAINT orders_fk_transported_id FOREIGN KEY (transporter_id) REFERENCES transporters(transporter_id);

CREATE FUNCTION products_in_stock_check() RETURNS trigger
AS '
	BEGIN
		IF NEW.in_stock < 0  THEN
            RAISE EXCEPTION ''product in_stock cant be less than 0'';
        END IF;
        RETURN NEW;
	END; '
LANGUAGE plpgsql;

CREATE TRIGGER products_in_stock_check BEFORE INSERT OR UPDATE ON products
	FOR EACH ROW EXECUTE PROCEDURE products_in_stock_check();

CREATE FUNCTION carts_quantity_check() RETURNS trigger
AS '
	BEGIN
		IF NEW.quantity <= 0  THEN
            RAISE EXCEPTION ''product quantity in cart cant be less than 0 or equal to 0'';
        END IF;
        RETURN NEW;
	END; '
LANGUAGE plpgsql;

CREATE TRIGGER carts_quantity_check BEFORE INSERT OR UPDATE ON carts
	FOR EACH ROW EXECUTE PROCEDURE carts_quantity_check();

CREATE FUNCTION order_completion_date_check() RETURNS trigger
AS '
	BEGIN
		IF NEW.order_date > NEW.completion_date THEN
            RAISE EXCEPTION ''completion_date cant be sooner than order_date'';
        END IF;
        RETURN NEW;
	END; '
LANGUAGE plpgsql;

CREATE TRIGGER order_completion_date_check BEFORE INSERT OR UPDATE ON orders
	FOR EACH ROW EXECUTE PROCEDURE order_completion_date_check();

INSERT INTO users (user_name, password, email, role, country, zip_code, city, street) VALUES
	('Mr. A', crypt('a', gen_salt('bf', 9)), 'a', 'REGULAR', 'Hungary', 4000,'citya','Some street, 8'), --1
	('user2', crypt('pw2', gen_salt('bf', 9)), 'user2@user2.com', 'RETAILER', 'Hungary', 5000,'cityb','Cool street, 18'), --2
	('user1', crypt('pw2', gen_salt('bf', 9)), 'user1@user1.com', 'ADMIN', 'Hungary', 6000,'cityb','Cooler street, 81'); --3

INSERT INTO products (product_name, category, properties, product_price, in_stock, sale_percentage, picture_url) VALUES
    ('z-301 ', 'processor', 'small;fast', 1000, 10, 10,'https://cdn.pixabay.com/photo/2019/05/31/16/01/cpu-4242470_1280.jpg'), --1
    ('qh-3 ', 'processor', 'ultra-small;ultra-fast', 2000, 5, 20, 'https://cdn.pixabay.com/photo/2017/04/10/07/57/processor-2217771_1280.jpg'), --2
    ('mxm-2', 'processor', 'nano;hyper-fast', 2500, 10, 0, 'https://cdn.pixabay.com/photo/2017/12/09/11/16/electronics-3007664_1280.jpg'), --3
    ('srs2000', 'hdd', 'very-hard disk drive; very fast', 500, 11, 0, 'https://cdn.pixabay.com/photo/2013/07/12/19/16/hdd-154463_1280.png'); --4

INSERT INTO carts (product_id, user_id, quantity, products_price) VALUES
	(1, 1, 3, 4500), --1
	(2, 2, 1, 23000), --2
    (1, 3, 2, 45000); --3

INSERT INTO transporters (transporter_company, transporter_price, zip_code_range_from, zip_code_range_to) VALUES
	('Transport3000', 1000, 1000, 2000), --1
	('Mike-trans', 1500, 2000, 3000), --2
    ('Trans2000', 3000, 3000, 5000); --3

INSERT INTO orders (transporter_id, total_price, order_date, status, completion_date) VALUES
	(1, 33000, '2019.05.22', 'DELIVERING', null), --1
	(1, 12000, '2019.05.11', 'DELIVERING', null), --2
	(2, 5532, '2019.05.23', 'DELIVERING', null); --3
