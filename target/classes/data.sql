-- =============================
-- Publishers
-- =============================
INSERT INTO publishers (name) VALUES
('Bloomsbury'),
('Bantam Books'),
('HarperCollins'),
('Houghton Mifflin'),
('Doubleday');

-- =============================
-- Authors
-- =============================
INSERT INTO authors (name, biography, birth_year) VALUES
('J.K. Rowling', 'Author of Harry Potter series', 1965),
('George R.R. Martin', 'Author of A Song of Ice and Fire', 1948),
('Agatha Christie', 'Famous mystery writer', 1890),
('J.R.R. Tolkien', 'Author of The Lord of the Rings', 1892),
('Dan Brown', 'Author of The Da Vinci Code', 1964);

-- =============================
-- Books
-- =============================
INSERT INTO books (title, isbn, publisher_id) VALUES
('Harry Potter and the Sorcerer''s Stone', '9780439708180', 1),
('A Game of Thrones', '9780553103540', 2),
('Murder on the Orient Express', '9780062693662', 3),
('The Lord of the Rings', '9780544003415', 4),
('The Da Vinci Code', '9780385504201', 5);

-- =============================
-- Book_Authors (many-to-many)
-- =============================
INSERT INTO book_authors (book_id, author_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- =============================
-- Genres
-- =============================
INSERT INTO genres (name) VALUES
('Fantasy'),
('Adventure'),
('Mystery'),
('Thriller'),
('Science Fiction');

-- =============================
-- Book_Genres (many-to-many)
-- =============================
INSERT INTO book_genres (book_id, genre_id) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 3),
(4, 1),
(5, 4);

-- =============================
-- Members
-- =============================
INSERT INTO members (name, email, membership_type, membership_date) VALUES
('Alice', 'alice@example.com', 'REGULAR', '2023-01-10'),
('Bob', 'bob@example.com', 'PREMIUM', '2023-03-15'),
('Charlie', 'charlie@example.com', 'REGULAR', '2023-05-20'),
('David', 'david@example.com', 'PREMIUM', '2023-07-05'),
('Eve', 'eve@example.com', 'REGULAR', '2023-08-01');


INSERT INTO inventory_copies (barcode, status, book_id) VALUES
('BC101', 'AVAILABLE', 1),
('BC102', 'AVAILABLE', 2),
('BC103', 'AVAILABLE', 3),
('BC104', 'AVAILABLE', 4),
('BC105', 'AVAILABLE', 5);


-- =============================
-- Borrow Records
-- =============================
INSERT INTO borrow_records (borrow_date, due_date, return_date, fine, member_id, copy_id) VALUES
('2025-08-01', '2025-08-15', '2025-08-14', 0.00, 1, 1),
('2025-08-03', '2025-08-17', NULL, 0.00, 2, 2),
('2025-08-05', '2025-08-19', '2025-08-20', 5.00, 3, 3),
('2025-08-10', '2025-08-24', NULL, 0.00, 4, 4),
('2025-08-12', '2025-08-26', '2025-08-28', 2.50, 5, 5);


-- =============================
-- reservations
-- =============================
INSERT INTO reservations (reservation_date, status, book_id, member_id) VALUES
('2025-08-01', 'QUEUED', 1, 1),
('2025-08-02', 'NOTIFIED', 2, 2),
('2025-08-03', 'FULFILLED', 3, 3),
('2025-08-04', 'CANCELLED', 4, 4),
('2025-08-05', 'QUEUED', 5, 5);
