insert into hierarchy (id, name, type) values ('CL_0001575', 'Consolidation Status', 'classification');


insert into hierarchy_entry (id, hierarchy_id, code, parent, name, hierarchy_level_type_id, display_order) values ('912693f5-1557-4e33-a566-e758395e3022', 'CL_0001575', 'CI_0034771', null, 'C - Consolidated', null, 3);
insert into hierarchy_entry (id, hierarchy_id, code, parent, name, hierarchy_level_type_id, display_order) values ('07b79ec7-fc39-409c-805d-d7627f411bb9', 'CL_0001575', 'CI_0036443', null, 'N - Non-Consolidated', null, 1);
insert into hierarchy_entry (id, hierarchy_id, code, parent, name, hierarchy_level_type_id, display_order) values ('a47cc817-1607-454f-b390-9b32daaad17d', 'CL_0001575', 'CI_0042605', null, 'P - Partially consolidated or aggregate containing both consolidated and non-consolidated items', null, 2);
insert into hierarchy_entry (id, hierarchy_id, code, parent, name, hierarchy_level_type_id, display_order) values ('5d588596-a50e-4c8b-abf5-23a589441140', 'CL_0001575', 'CI_0042620', null, '_Z - Not applicable', null, 0);
