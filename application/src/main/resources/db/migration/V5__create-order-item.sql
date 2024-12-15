CREATE TABLE IF NOT EXISTS order_item(

    id SERIAL PRIMARY KEY,
    order_id SERIAL not null,
    product_id SERIAL not null
);

ALTER TABLE order_item ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES ordered(id);
ALTER TABLE order_item ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product(id);
