-- db/migration/V2__create_store_table.sql

-- Create the Store table
CREATE TABLE store (
  store_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  store_status VARCHAR(15) NOT NULL,
  user_id VARCHAR(40) NOT NULL,
  name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  title VARCHAR(50) NOT NULL,
  icon_path VARCHAR(255) NOT NULL,
  store_address BIGINT NOT NULL,
  banner_path VARCHAR(255),
  story_title VARCHAR(80) NOT NULL,
  announcement_title VARCHAR(50),
  announcement_description VARCHAR(255),
  message_to_buyers VARCHAR(100) NOT NULL,
  order_customization_allowed BOOLEAN NOT NULL,
  story_description VARCHAR(255) NOT NULL,
  vacation_mode BOOLEAN NOT NULL,
  vacation_auto_replay VARCHAR(255) NOT NULL,
  FOREIGN KEY (store_address) REFERENCES address (address_id)
);


-- Create the Address table
CREATE TABLE address (
  address_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  street VARCHAR(100) NOT NULL,
  city VARCHAR(50) NOT NULL,
  state VARCHAR(50) NOT NULL
);
