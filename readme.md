` create user sourav with createdb password 'password';`

` create database sourav_test with owner sourav;`

` create table employee(
  id BIGSERIAL PRIMARY KEY,
  name varchar(100),
  department varchar(10),
  created      TIMESTAMP WITH TIME ZONE  NOT NULL,
  modified     TIMESTAMP WITH TIME ZONE  NOT NULL
); `

`grant all privileges on all tables in schema PUBLIC to sourav;`

`GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO sourav;`
