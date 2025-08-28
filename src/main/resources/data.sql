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
('George R.R. Martin', 'A Song of Ice and Fire', 1948),
('Agatha Christie', 'Famous mystery writer', 1890),
('J.R.R. Tolkien', 'Author of The Lord of the Rings', 1892),
('Dan Brown', 'Author of The Da Vinci Code', 1964),
('Suzanne Collins', 'Author of The Hunger Games', 1962),
('Stephen King', 'Horror and suspense novels', 1947),
('Ernest Hemingway', 'American novelist', 1899),
('F. Scott Fitzgerald', 'Author of The Great Gatsby', 1896),
('Leo Tolstoy', 'Author of War and Peace', 1828);

-- =============================
-- Books
-- =============================
INSERT INTO books (title, isbn, publisher_id) VALUES
('Harry Potter and the Sorcerer''s Stone', '9780439708180', 1),
('A Game of Thrones', '9780553103540', 2),
('Murder on the Orient Express', '9780062693662', 3),
('The Lord of the Rings', '9780544003415', 4),
('The Da Vinci Code', '9780385504201', 5),
('The Hunger Games', '9780439023528', 1),
('The Shining', '9780307743657', 3),
('The Old Man and The Sea', '9780684801223', 4),
('The Great Gatsby', '9780743273565', 5),
('War and Peace', '9780199232765', 2);

-- =============================
-- Book_Authors (many-to-many)
-- =============================
INSERT INTO book_authors (book_id, author_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

-- =============================
-- Genres
-- =============================
INSERT INTO genres (name) VALUES
('Fantasy'),
('Adventure'),
('Mystery'),
('Thriller'),
('Science Fiction'),
('Horror'),
('Classic');

-- =============================
-- Book_Genres (many-to-many)
-- =============================
INSERT INTO book_genres (book_id, genre_id) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 3),
(4, 1),
(5, 4),
(6, 1),
(6, 2),
(7, 6),
(8, 7),
(9, 7),
(10, 7);

-- =============================
-- Members
-- =============================
INSERT INTO members (name, email, membership_type, membership_date) VALUES
('Alice', 'alice@example.com', 'REGULAR', '2023-01-10'),
('Bob', 'bob@example.com', 'PREMIUM', '2023-03-15'),
('Charlie', 'charlie@example.com', 'REGULAR', '2023-05-20'),
('David', 'david@example.com', 'PREMIUM', '2023-07-05'),
('Eve', 'eve@example.com', 'REGULAR', '2023-08-01'),
('Frank', 'frank@example.com', 'REGULAR', '2023-08-10'),
('Grace', 'grace@example.com', 'PREMIUM', '2023-08-15'),
('Hannah', 'hannah@example.com', 'REGULAR', '2023-08-18'),
('Ivy', 'ivy@example.com', 'PREMIUM', '2023-08-20'),
('Jack', 'jack@example.com', 'REGULAR', '2023-08-25');

-- =============================
-- Inventory Copies
-- =============================
INSERT INTO inventory_copies (barcode, status, book_id) VALUES
('BC101', 'AVAILABLE', 1),
('BC102', 'AVAILABLE', 2),
('BC103', 'AVAILABLE', 3),
('BC104', 'AVAILABLE', 4),
('BC105', 'AVAILABLE', 5),
('BC106', 'BORROWED', 6),
('BC107', 'AVAILABLE', 7),
('BC108', 'AVAILABLE', 8),
('BC109', 'RESERVED', 9),
('BC110', 'LOST', 10);

-- =============================
-- Borrow Records
-- =============================
INSERT INTO borrow_records (borrow_date, due_date, return_date, fine, member_id, copy_id) VALUES
('2025-08-01', '2025-08-15', '2025-08-14', 0.00, 1, 1),
('2025-08-03', '2025-08-17', NULL, 0.00, 2, 2),
('2025-08-05', '2025-08-19', '2025-08-20', 5.00, 3, 3),
('2025-08-10', '2025-08-24', NULL, 0.00, 4, 4),
('2025-08-12', '2025-08-26', '2025-08-28', 2.50, 5, 5),
('2025-08-15', '2025-08-29', NULL, 0.00, 6, 6),
('2025-08-16', '2025-08-30', '2025-08-31', 0.00, 7, 7),
('2025-08-18', '2025-09-01', NULL, 0.00, 8, 8),
('2025-08-20', '2025-09-03', NULL, 0.00, 9, 9),
('2025-08-22', '2025-09-06', '2025-09-07', 1.50, 10, 10);

-- =============================
-- Reservations (20 dummy records)
-- =============================
INSERT INTO reservations (reservation_date, status, book_id, member_id) VALUES
('2025-08-01', 'QUEUED', 1, 1),
('2025-08-02', 'NOTIFIED', 2, 2),
('2025-08-03', 'FULFILLED', 3, 3),
('2025-08-04', 'CANCELLED', 4, 4),
('2025-08-05', 'QUEUED', 5, 5),
('2025-08-06', 'NOTIFIED', 6, 6),
('2025-08-07', 'FULFILLED', 7, 7),
('2025-08-08', 'CANCELLED', 8, 8),
('2025-08-09', 'QUEUED', 9, 9),
('2025-08-10', 'NOTIFIED', 10, 10),
('2025-08-11', 'FULFILLED', 1, 2),
('2025-08-12', 'CANCELLED', 2, 3),
('2025-08-13', 'QUEUED', 3, 4),
('2025-08-14', 'NOTIFIED', 4, 5),
('2025-08-15', 'FULFILLED', 5, 6),
('2025-08-16', 'CANCELLED', 6, 7),
('2025-08-17', 'QUEUED', 7, 8),
('2025-08-18', 'NOTIFIED', 8, 9),
('2025-08-19', 'FULFILLED', 9, 10),
('2025-08-20', 'CANCELLED', 10, 1);
