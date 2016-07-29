CREATE TABLE category
(
  id bigint NOT NULL,
  name character varying(255),
  CONSTRAINT category_pkey PRIMARY KEY (id)
);

CREATE TABLE article
(
  id bigint NOT NULL,
  description character varying(2056),
  name character varying(255),
  price double precision,
  stock bigint,
  category_id bigint,
  CONSTRAINT article_pkey PRIMARY KEY (id),
  CONSTRAINT category_fk FOREIGN KEY (category_id)
      REFERENCES category (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE customer
(
  id bigint NOT NULL,
  email character varying(255),
  first_name character varying(255),
  last_name character varying(255),
  CONSTRAINT customer_pkey PRIMARY KEY (id)
);

CREATE TABLE shopping_cart
(
  id bigint NOT NULL,
  customer_id bigint,
  timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT shopping_cart_pkey PRIMARY KEY (id)
);

CREATE TABLE shopping_cart_item
(
  id bigint NOT NULL,
  amount bigint,
  article_id bigint,
  CONSTRAINT shopping_cart_item_pkey PRIMARY KEY (id)
);

CREATE TABLE shopping_cart_items
(
  shopping_cart_id bigint NOT NULL,
  items_id bigint NOT NULL,
  CONSTRAINT shopping_cart_items_pkey PRIMARY KEY (shopping_cart_id, items_id),
  CONSTRAINT shopping_card_item_fk FOREIGN KEY (items_id)
      REFERENCES shopping_cart_item (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT shopping_card_fk FOREIGN KEY (shopping_cart_id)
      REFERENCES shopping_cart (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT items_id_unique_constraint UNIQUE (items_id)
);