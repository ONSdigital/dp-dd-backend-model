insert into hierarchy (id, name, type) values ('CL_0001552', 'Accounting Entry', 'classification');


insert into hierarchy_entry (id, hierarchy_id, code, parent, name, hierarchy_level_type_id, display_order) values ('b0f456d6-c1ce-40d0-9e50-eee06fdc572d', 'CL_0001552', 'CI_0006399', null, 'B - Balance (Credits minus Debits)', null, 0);
insert into hierarchy_entry (id, hierarchy_id, code, parent, name, hierarchy_level_type_id, display_order) values ('691a59a1-0175-471e-85f6-2007b1a1f0e2', 'CL_0001552', 'CI_0006408', null, 'C - Credit (Resources)', null, 2);
insert into hierarchy_entry (id, hierarchy_id, code, parent, name, hierarchy_level_type_id, display_order) values ('1a5f1ab3-77d0-4ad0-b3db-307bd4f593b5', 'CL_0001552', 'CI_0006571', null, 'D - Debit (Uses)', null, 3);
insert into hierarchy_entry (id, hierarchy_id, code, parent, name, hierarchy_level_type_id, display_order) values ('2d3a70f9-7592-4f97-90bb-b9b25bec968a', 'CL_0001552', 'CI_0042636', null, '_Z - Not applicable', null, 1);
