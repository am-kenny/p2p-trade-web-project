-- Insert data into bank table
INSERT INTO bank (name) VALUES
                            ('Bank of America'),
                            ('Chase Bank'),
                            ('Wells Fargo');

-- Insert data into currency table
INSERT INTO currency (name, code) VALUES
                                      ('US Dollar', 'USD'),
                                      ('Euro', 'EUR'),
                                      ('British Pound', 'GBP');

-- Insert data into user_verification table
INSERT INTO user_verification (name, surname, passport_number, passport_photo_reference, is_banned) VALUES
                                                                                                        ('John', 'Doe', '123456789', 'passport_john_doe.jpg', 0),
                                                                                                        ('Jane', 'Smith', '987654321', 'passport_jane_smith.jpg', 0),
                                                                                                        ('Alice', 'Johnson', '456789123', 'passport_alice_johnson.jpg', 1);

-- Insert data into user table
INSERT INTO user (username, email, password, user_verification_id, created_at) VALUES
                                                                                   ('johndoe', 'johndoe@example.com', 'password123', 1, NOW()),
                                                                                   ('janesmith', 'janesmith@example.com', 'password456', 2, NOW()),
                                                                                   ('alicejohnson', 'alicejohnson@example.com', 'password789', 3, NOW());

-- Insert data into bank_account table
INSERT INTO bank_account (card_number, bank_id, user_id, currency_id, cardholder_name) VALUES
                                                                                           (1234567890123456, 1, 1, 1, 'John Doe'),
                                                                                           (2345678901234567, 2, 2, 2, 'Jane Smith'),
                                                                                           (3456789012345678, 3, 3, 3, 'Alice Johnson');

-- Insert data into trade table
INSERT INTO trade (initiator_user_id, responder_user_id, is_seller, trade_currency_id, trade_currency_amount, exchange_currency_id, exchange_rate, status, is_confirmed_by_initiator, is_confirmed_by_responder, created_at, replied_at, closed_at) VALUES
                                                                                                                                                                                                                                                        (1, 2, 1, 1, 1000, 2, 0.85, 'open', 0, 0, NOW(), NULL, NULL),
                                                                                                                                                                                                                                                        (2, 1, 0, 2, 850, 1, 1.18, 'responded', 1, 0, NOW(), NOW(), NULL),
                                                                                                                                                                                                                                                        (3, NULL, 1, 3, 750, 1, 1.38, 'closed', 1, 1, NOW(), NOW(), NOW());

-- Insert data into trade_feedback table
INSERT INTO trade_feedback (author_user_id, trade_id, is_positive, text) VALUES
                                                                             (1, 1, 1, 'Great trade experience!'),
                                                                             (2, 2, 0, 'Not satisfied with the trade.'),
                                                                             (3, 3, 1, 'Smooth transaction.');

-- Insert data into trade_message table
INSERT INTO trade_message (user_id, trade_id, text, media, created_at) VALUES
                                                                           (1, 1, 'Looking forward to the trade.', NULL, NOW()),
                                                                           (2, 2, 'Can you confirm the details?', NULL, NOW()),
                                                                           (3, 3, 'Trade completed successfully.', 'trade_success.jpg', NOW());
