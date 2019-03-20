insert into user (id, mail, password, userType, username) values (null, 'tuturuganicu@gmail.com', '12345', 'CLIENT', 'tuturuga');

insert into furniture (id, description, name) values (null, 'Strong material', 'Chair');

insert into deal (id, available, dealType, name, price, furnitureId, availableQuantity) values (null, true, 'REDUCED', 'Chair', 10, 1, 1);
insert into deal (id, available, dealType, name, price, furnitureId, availableQuantity) values (null, true, 'REDUCED', 'Chair 2', 1, 1, 1);

insert into clientOrder (id, approved, paymentMethod, totalPrice, userId) values (null, false, 'CASH', 10, 1)
insert into clientOrder (id, approved, paymentMethod, totalPrice, userId) values (null, false, 'CASH', 10, 1)

insert into orderHistory (id, orderPlaceDateTime, orderState, orderId) values (null, '2019-01-01 00:00:00', 'PLACED', 1);

insert into feedbackMessage (id, messageBody, title, orderHistoryId) values (null, 'Good product', 'Feedback', 1);

insert into boughtFurniture (id, boughtQuantity, price, orderId, furnitureId) values (null, 1, 10, 1, 1);
