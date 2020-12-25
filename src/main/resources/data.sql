-- ADMIN
INSERT INTO ADMIN(id, first_name, last_name, email, username, password)
VALUES (1, 'Graeme', 'Lee', 'james@email.com', 'jam22xx', 'pass');

-- PUBLISHER
INSERT INTO PUBLISHER(id, name, description)
VALUES (1, 'Wiley Production', 'Lots of IT reference books');

INSERT INTO PUBLISHER(id, name, description)
VALUES (2, 'Oreilly Media', 'IT reference books');

INSERT INTO PUBLISHER(id, name, description)
VALUES (3, 'Audible publishing', 'Novels and stuff');

-- CATEGORY
INSERT INTO CATEGORY(id, name)
VALUES(1, 'INFORMATION TECHNOLOGY');

INSERT INTO CATEGORY(id, name)
VALUES(2, 'LOVE AND ROMANCE');

INSERT INTO CATEGORY(id, name)
VALUES(3, 'HEALTHY LIVING');

-- BOOK
INSERT INTO BOOK(id, title, short_summary, description, pages, publisher_id, isbn, price)
VALUES (1, 'Intro to Java book', '2nd edition', 'Dive in to learning java 8', 435, 1, '23423JIJI2', 20);

INSERT INTO BOOK(id, title, description, pages, publisher_id, isbn, price)
VALUES (2, 'Jungle book', 'book about a boy', 334, 3, '443JJDFDF', 20);

-- BOOK_CATEGORY
INSERT INTO BOOK_CATEGORY(book_id, category_id)
VALUES(1, 1);

INSERT INTO BOOK_CATEGORY(book_id, category_id)
VALUES(2, 1);

INSERT INTO BOOK_CATEGORY(book_id, category_id)
VALUES(1, 2);


-- CONSUMER
INSERT INTO CONSUMER(id, first_name, last_name, email, username, password)
VALUES (1, 'Dominik', 'Selchert', 'dominiksch@email.com', 'dee123', 'pass');

INSERT INTO CONSUMER(id, first_name, last_name, email, username, password)
VALUES (2, 'Sarah', 'Frank', 'sarry@email.com', 'sarah', 'pass');

-- HOLDING_REQUEST
INSERT INTO HOLDING_REQUEST(id, member_id, book_of_interest_id, requested_date)
VALUES (1, 1, 2, '2019-11-10');

INSERT INTO HOLDING_REQUEST(id, member_id, book_of_interest_id, requested_date)
VALUES (2, 2, 2, '2019-12-02');

-- COMMENTS
INSERT INTO COMMENTS(id, book_id, consumer_id, comment, commented_time, admin_reply)
VALUES (1, 2, 2, 'This was a fabulous book. 5 stars!!!', '2020-11-19', 'Thank you for your review!');

-- BOOK_SHELF
INSERT INTO BOOK_SHELF(id, book_id, admin_id, isle, shelf_column, shelf_row)
VALUES (1, 1, 1, 'non-fiction', 'SHELF-COLUMN#12', 'ROW#5');
