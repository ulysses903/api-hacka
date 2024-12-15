CREATE TABLE IF NOT EXISTS ordered(

    id SERIAL PRIMARY KEY,
    received_at timestamp(6),
    finished_at timestamp(6),
    status varchar(20) not null
)
