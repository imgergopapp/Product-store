DROP TRIGGER IF EXISTS products_in_stock_check ON products;
DROP TRIGGER IF EXISTS carts_quantity_check ON carts;
DROP TRIGGER IF EXISTS order_completion_date_check ON orders;

DROP FUNCTION IF EXISTS products_in_stock_check;
DROP FUNCTION IF EXISTS carts_quantity_check;
DROP FUNCTION IF EXISTS order_completion_date_check;

DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS sale CASCADE;
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
	main_category VARCHAR(40),
	sub_category VARCHAR(40),
	properties VARCHAR(200),
	product_company VARCHAR(40),
	product_price INTEGER,
	in_stock INTEGER,
	is_on_sale BOOLEAN,
	picture_url VARCHAR(100)
);

CREATE TABLE sale(
    product_id INTEGER,
    sale_percent NUMERIC(2),
    date_from DATE,
    date_until DATE

);

CREATE TABLE carts(
	cart_id SERIAL PRIMARY KEY,
    product_id INTEGER,
    user_id INTEGER,
    quantity INTEGER,
    products_price MONEY
);

CREATE TABLE transporters(
 	transporter_id SERIAL PRIMARY KEY,
    transporter_company VARCHAR(40),
 	transporter_price MONEY,
 	zip_code_range_from NUMERIC(4),
    zip_code_range_to NUMERIC(4)
 );

CREATE TABLE orders(
	order_id SERIAL PRIMARY KEY,
    cart_id INTEGER,
	transporter_id INTEGER,
	total_price MONEY,
    order_date DATE,
    status VARCHAR(20),
    completion_date DATE
);

ALTER TABLE carts
	ADD CONSTRAINT carts_fk_product_id FOREIGN KEY (product_id) REFERENCES products(product_id),
	ADD CONSTRAINT carts_fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id);

ALTER TABLE orders
	ADD CONSTRAINT orders_fk_cart_id FOREIGN KEY (cart_id) REFERENCES carts(cart_id),
	ADD CONSTRAINT orders_fk_transported_id FOREIGN KEY (transporter_id) REFERENCES transporters(transporter_id);

ALTER TABLE sale
	ADD CONSTRAINT sale_fk_product_id FOREIGN KEY (product_id) REFERENCES products(product_id);

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

INSERT INTO products (product_name, main_category, sub_category, properties, product_company, product_price, in_stock, is_on_sale, picture_url) VALUES
    ('z-301 ', 'it', 'processor', 'small;fast', 'That company', 1000, 10, true,'https://cdn.pixabay.com/photo/2019/05/31/16/01/cpu-4242470_1280.jpg'), --1
    ('qh-3 ', 'it', 'processor', 'ultra-small;ultra-fast', 'This company', 2000, 5, false, 'https://cdn.pixabay.com/photo/2017/04/10/07/57/processor-2217771_1280.jpg'), --2
    ('mxm-2', 'it', 'processor', 'nano;hyper-fast', 'That company', 2500, 10, false, 'https://cdn.pixabay.com/photo/2017/12/09/11/16/electronics-3007664_1280.jpg'), --3
    ('srs2000', 'it', 'hdd', 'very-hard disk drive; very fast', 'One man company', 500, 11, false, 'https://cdn.pixabay.com/photo/2013/07/12/19/16/hdd-154463_1280.png'), --4
    ('qqq-111', 'sport', 'bike', 'medium;lightweight;21 speed', 'Random bycicle company', 15000, 2, false, 'https://cdn.pixabay.com/photo/2013/07/13/13/46/bicycle-161524_1280.png'); --5

INSERT INTO sale (product_id, sale_percent, date_from, date_until) VALUES
    (1,10,'2019-01-01','2019-02-01');

INSERT INTO carts (product_id, user_id, quantity, products_price) VALUES
	(1, 1, 3, 4500), --1
	(2, 2, 1, 23000), --2
    (1, 3, 2, 45000); --3

INSERT INTO transporters (transporter_company, transporter_price, zip_code_range_from, zip_code_range_to) VALUES
	('Transport3000', 1000, 1000, 2000), --1
	('Mike-trans', 1500, 2000, 3000), --2
    ('Trans2000', 3000, 3000, 5000); --3

INSERT INTO orders (cart_id, transporter_id, total_price, order_date, status, completion_date) VALUES
	(1, 1, 33000, '2019.05.22', 'DELIVERING', null), --1
	(2, 1, 12000, '2019.05.11', 'DELIVERING', null), --2
	(3, 2, 5532, '2019.05.23', 'DELIVERING', null); --3
