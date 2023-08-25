create table token(
token_id serial primary key,
user_id integer references users(user_id),
token VARCHAR(255) NOT NULL,
created_at timestamp NOT NULL,
expires_at timestamp NOT NULL,
confirmed_at timestamp
)