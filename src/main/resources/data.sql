-- Insert tournaments
INSERT INTO tournament (id, name, start_date, end_date, location, entry_fee, cash_prize_amount) VALUES
  (1, 'Autumn Open Updated', '2024-10-01', '2024-10-03', 'St. Andrews', 175.00, 2500.00),
  (2, 'Spring Masters', '2025-04-15', '2025-04-17', 'Pebble Beach', 200.00, 3000.00);

-- Insert members
INSERT INTO member (id, name, address, email, membership_type, phone_number, membership_start_date, membership_duration_months) VALUES
  (1, 'Charlie Updated', '789 Pine Rd', 'charlie@example.com', 'Bronze', '555-9999', '2024-04-04', 12),
  (2, 'Jordan Smith', '123 Oak Ave', 'jordan@example.com', 'Silver', '555-8888', '2024-05-01', 24);

-- Link members to tournaments
INSERT INTO member_tournament (member_id, tournament_id) VALUES
  (1, 1),
  (1, 2),
  (2, 2),
  (2, 1);