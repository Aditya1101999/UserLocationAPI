CREATE SEQUENCE user_location_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE user_location (
  id BIGINT DEFAULT NEXTVAL('user_location_id_seq') PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   latitude DOUBLE PRECISION NOT NULL,
  longitude DOUBLE PRECISION NOT NULL
);

INSERT INTO user_location (name, latitude, longitude) VALUES
('User 1', 40.748817, -73.985428),
('User 2', 40.748718, -73.985647);
