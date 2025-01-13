CREATE TABLE IF NOT EXISTS videoprocessing(

    id SERIAL PRIMARY KEY,
    order_id SERIAL not null,
    client varchar(20),
    status varchar(20) not null,
    amount decimal not null,
    videoprocessing_at timestamp(6)
);

ALTER TABLE videoprocessing ADD CONSTRAINT fk_videoprocessing_order_id FOREIGN KEY (order_id) REFERENCES ordered(id);