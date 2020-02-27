INSERT INTO Route(route_id, city_id, route_begin, route_end, price, driver_id) VALUES (1, 100, ST_SetSRID( ST_Point( -71.104, 42.315), 4326), ST_SetSRID( ST_Point( -71.104, 42.315), 4326), 1000, 5);

INSERT INTO Route(route_id, city_id, route_begin, route_end, price, driver_id) VALUES (2, 100, ST_Point(30.3250575, 59.9174455), ST_Point(30.3250575, 59.9171455), 500, 6);

INSERT INTO Route(route_id, city_id, route_begin, route_end, price, driver_id) VALUES (3, 100, ST_Point(30.3250575, 59.9174455), ST_Point(30.3250575, 59.5174455), 300, 5);

INSERT INTO Route(route_id, city_id, route_begin, route_end, price, driver_id) VALUES (4, 100, ST_Point(30.3250575, 59.9174455), ST_Point(30.3250575, 59.9172455), 800, 5);

INSERT INTO Route(route_id, city_id, route_begin, route_end, price, driver_id) VALUES (5, 100, ST_Point(30.3250575, 59.9174455),ST_Point(30.3250575, 59.9114455), 1500, 6);
