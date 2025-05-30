-- Import planning parameters (from Planning Parameter sheet)
INSERT INTO planning_parameters VALUES
(1, 0, 3250, 40, TRUE, 40, 10);

-- Import materials (from Material sheet)
INSERT INTO materials (mother_batch_number, parent_batch_number, batch_number, grade, product_family, 
                      thickness, width, weight, length, age, date, stock_type, coil_status)
VALUES 
('C1', 'C1', 'C1', 'A', 'A', 0.15, 1150, 20000, 14769.69, 1, '2020-05-09', 'COIL', 'RM'),
('C2', 'C2', 'C2', 'A', 'A', 0.15, 1540, 25000, 13786.64, 1, '2020-05-09', 'COIL', 'RM'),
('C3', 'C3', 'C3', 'A', 'A', 0.15, 1440, 23000, 13564.52, 1, '2020-05-09', 'COIL', 'RM');

-- Import orders (from Order sheet)
INSERT INTO orders VALUES
(DEFAULT, 'O1', 10, 'A', 'A', '2016-03-09', 0.15, 800, 45000, 45000, 45000, 5000, 5000, 
 47770.7, 47770.7, 47770.7, 5307.86, 5307.86, 'COIL', '2016-03-09'),
(DEFAULT, 'O2', 10, 'A', 'A', '2016-03-09', 0.15, 300, 55000, 55000, 55000, 5000, 5000, 
 155697.1, 155697.1, 155697.1, 14154.28, 14154.28, 'COIL', '2016-03-09'),
(DEFAULT, 'O3', 10, 'A', 'A', '2016-03-09', 0.15, 1100, 25000, 25000, 25000, 5000, 5000, 
 19301.29, 19301.29, 19301.29, 3860.26, 3860.26, 'COIL', '2016-03-09'),
(DEFAULT, 'O4', 10, 'A', 'A', '2016-03-09', 0.15, 1500, 35000, 35000, 35000, 5000, 5000, 
 19815.99, 19815.99, 19815.99, 2830.86, 2830.86, 'COIL', '2016-03-09');

-- Import CPO constraints (from CPO Constraint sheet)
INSERT INTO cpo_constraints VALUES
(DEFAULT, 1, 'A', 'A', 0.15, 800, 2400, 2000, 3506, 1500, 65000, 5000, 12000, 1450);