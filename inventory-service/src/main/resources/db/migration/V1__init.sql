CREATE TABLE IF NOT EXISTS inventories (
    id BIGINT NOT NULL AUTO_INCREMENT,
    sku_code VARCHAR(50) NOT NULL,
    quantity INT NOT NULL CHECK (quantity >= 0),
    PRIMARY KEY (id)
);