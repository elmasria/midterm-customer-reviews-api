CREATE TABLE tbl_products
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    description TEXT NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tbl_reviews
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    owner VARCHAR(30) NOT NULL,
    title VARCHAR(50) NOT NULL,
    review TEXT NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT product_fk FOREIGN KEY (product_id) REFERENCES tbl_products(id)
);

CREATE TABLE tbl_comments
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    review_id INT NOT NULL,
    owner VARCHAR(30) NOT NULL,
    comment TEXT NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT review_fk FOREIGN KEY (review_id) REFERENCES tbl_reviews(id)
);
