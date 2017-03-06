insert into hierarchy (id, name, type) values ('CL_0001579', 'Unit of Measurement', 'classification');


insert into hierarchy_entry (id, hierarchy_id, code, parent, name, hierarchy_level_type_id, display_order) values ('61f78b31-3765-4123-bf61-4635917f1124', 'CL_0001579', 'CI_0042555', null, 'XDC - Domestic currency, £m', null, 0);
insert into hierarchy_entry (id, hierarchy_id, code, parent, name, hierarchy_level_type_id, display_order) values ('890d90cd-020e-4116-95ed-969676831d1a', 'CL_0001579', 'CI_0042640', null, 'HW - Hours worked, thousands', null, 1);
insert into hierarchy_entry (id, hierarchy_id, code, parent, name, hierarchy_level_type_id, display_order) values ('abb83ffb-6845-442c-8a65-8af667ec3ef9', 'CL_0001579', 'CI_0042641', null, 'PS - Persons', null, 2);
