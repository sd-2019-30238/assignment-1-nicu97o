insert into user (id, mail, password, userType, username) values (null, 'tuturuganicu@gmail.com', '12345', 'CLIENT', 'tuturuga');
insert into user (id, mail, password, userType, username) values (null, 'nicu.tuturuga@yahoo.com', 'abcdefg', 'STAFF', 'nicu');

insert into furniture (id, description, name) values (null, 'Strong material', 'Chair');
insert into furniture (id, description, name) values (null, 'Strong material', 'Table');

insert into deal (id, available, dealType, name, price, furnitureId, availableQuantity) values (null, true, 'EACH_PRODUCT_REDUCTION_5_PERCENT', 'Chair', 10, 1, 1);
insert into deal (id, available, dealType, name, price, furnitureId, availableQuantity) values (null, true, 'NORMAL', 'Chair 2', 1, 1, 1);

insert into clientOrder (id, approved, paymentMethod, totalPrice, userId, finished) values (null, false, 'CASH', 10, 1, true);
insert into clientOrder (id, approved, paymentMethod, totalPrice, userId, finished) values (null, false, 'CASH', 10, 1, false);
insert into clientOrder (id, approved, paymentMethod, totalPrice, userId, finished) values (null, false, 'CASH', 20, 2, false);

insert into orderHistory (id, orderPlaceDateTime, orderState, orderId) values (null, '2019-01-01 00:00:00', 'PLACED', 1);
insert into orderHistory (id, orderPlaceDateTime, orderState, orderId) values (null, '2019-01-01 00:00:00', 'PIKING', 2);

insert into feedbackMessage (id, messageBody, title, orderHistoryId) values (null, 'Good product', 'Feedback', 1);

insert into boughtFurniture (id, boughtQuantity, price, orderId, furnitureId) values (null, 1, 10, 1, 1);
