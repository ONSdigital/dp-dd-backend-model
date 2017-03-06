insert into hierarchy (id, name, type) values ('CL_0001554', 'Reference Area', 'classification');


insert into hierarchy_entry (id, hierarchy_id, code, parent, name, hierarchy_level_type_id, display_order) values ('ea5bc168-09e3-4fe5-b4b4-f18ea980e14b', 'CL_0001554', 'CI_0036444', null, 'W0 - World (all entities including Rest of the World)', null, 0);
insert into hierarchy_entry (id, hierarchy_id, code, parent, name, hierarchy_level_type_id, display_order) values ('4baa81d8-5bcd-4b6b-b439-bcbbdffbfb58', 'CL_0001554', 'CI_0036493', null, 'W1 - Rest of the World', null, 1);
insert into hierarchy_entry (id, hierarchy_id, code, parent, name, hierarchy_level_type_id, display_order) values ('77d46c82-bc76-4288-89a5-b06b4365f08b', 'CL_0001554', 'CI_0036497', null, 'W2 - Domestic (home or reference area)', null, 3);
insert into hierarchy_entry (id, hierarchy_id, code, parent, name, hierarchy_level_type_id, display_order) values ('b73f9109-63b3-40c6-83ad-b516392c618a', 'CL_0001554', 'CI_0042608', null, '4Y - All European Community Institutions, Organs and Organisms, including ECB and ESM', null, 2);
