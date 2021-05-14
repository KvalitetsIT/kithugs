-- * organisation *
INSERT INTO organisation (id, organisation_id, name, pool_size) VALUES (1, 'medcom', 'company name 2', 10);
INSERT INTO organisation (id, organisation_id, name, pool_size) VALUES (2, 'kit', 'KvalitetsIT', 10);

-- * meeting_users *
INSERT INTO meeting_users (id, organisation_id, email) VALUES (101,  1, 'me@me101.dk');

-- * meetings *
INSERT INTO meetings (id, uuid, subject, organisation_id, created_by, start_time, end_time , description, project_code, organized_by, updated_by, updated_time, created_time, short_id) VALUES (1, '7cc82183-0d47-439a-a00c-38f7a5a01fc3', 'TestMeeting-xyz8', 1, 101, '2019-10-02 15:00:00', '2030-10-02 16:00:00', 'Mødebeskrivelse 8', 'PRJCDE1', 101, NULL, NULL, NULL, '00000009');

-- * scheduling_template *
INSERT INTO scheduling_template (id, organisation_id, conferencing_sys_id, uri_prefix, uri_domain, host_pin_required, host_pin_range_low, host_pin_range_high, guest_pin_required, guest_pin_range_low, guest_pin_range_high, vmravailable_before, max_participants, end_meeting_on_end_time, uri_number_range_low, uri_number_range_high, ivr_theme)
VALUES (1, 1, 22, 'abc', 'test.dk', 1, 1, 91, 0, 100, 991, 15, 10, 1, 1000, 9991, '/api/admin/configuration/v1/ivr_theme/10/');

-- * scheduling_info *
INSERT INTO scheduling_info (id, host_pin, guest_pin, vmravailable_before, vmrstart_time, max_participants, end_meeting_on_end_time, uri_with_domain, uri_without_domain, scheduling_template_id, provision_status, provision_status_description, provision_timestamp,  provisionvmrid, portal_link, ivr_theme, created_by, created_time, updated_by, updated_time, organisation_id, uuid)
VALUES (201, 1001, 2001, 15, '2018-10-02 14:45:00', 10, 1, '1231@test.dk', '1231', 1, 'PROVISIONED_OK', 'all ok', null, null, 'https://portal-test.vconf.dk/?url=1238@test.dk&pin=2001&start_dato=2018-10-02T15:00:00', '/api/admin/configuration/v1/ivr_theme/10/', 101, NULL, NULL, NULL, 1, '7cc82183-0d47-439a-a00c-38f7a5a01fc3');
INSERT INTO scheduling_info (id, host_pin, guest_pin, vmravailable_before, vmrstart_time, max_participants, end_meeting_on_end_time, uri_with_domain, uri_without_domain, scheduling_template_id, provision_status, provision_status_description, provision_timestamp,  provisionvmrid, portal_link, ivr_theme, created_by, created_time, updated_by, updated_time, organisation_id, uuid)
VALUES (202, 1001, 2001, 15, '2018-10-02 14:45:00', 10, 1, '1232@test.dk', '1232', 1, 'PROVISIONED_OK', 'all ok', null, null, 'https://portal-test.vconf.dk/?url=1238@test.dk&pin=2001&start_dato=2018-10-02T15:00:00', '/api/admin/configuration/v1/ivr_theme/10/', 101, NULL, NULL, NULL, 1, '7cc82183-0d47-439a-a00c-38f7a5a01fc4');
INSERT INTO scheduling_info (id, host_pin, guest_pin, vmravailable_before, vmrstart_time, max_participants, end_meeting_on_end_time, uri_with_domain, uri_without_domain, scheduling_template_id, provision_status, provision_status_description, provision_timestamp,  provisionvmrid, portal_link, ivr_theme, created_by, created_time, updated_by, updated_time, organisation_id, uuid)
VALUES (203, 1001, 2001, 15, '2018-10-02 14:45:00', 10, 1, '1233@test.dk', '1233', 1, 'PROVISIONED_OK', 'all ok', null, null, 'https://portal-test.vconf.dk/?url=1238@test.dk&pin=2001&start_dato=2018-10-02T15:00:00', '/api/admin/configuration/v1/ivr_theme/10/', 101, NULL, NULL, NULL, 1, '7cc82183-0d47-439a-a00c-38f7a5a01fc5');
INSERT INTO scheduling_info (id, host_pin, guest_pin, vmravailable_before, vmrstart_time, max_participants, end_meeting_on_end_time, uri_with_domain, uri_without_domain, scheduling_template_id, provision_status, provision_status_description, provision_timestamp,  provisionvmrid, portal_link, ivr_theme, created_by, created_time, updated_by, updated_time, organisation_id, uuid)
VALUES (204, 1001, 2001, 15, '2018-10-02 14:45:00', 10, 1, '1234@test.dk', '1234', 1, 'PROVISIONED_OK', 'all ok', null, null, 'https://portal-test.vconf.dk/?url=1238@test.dk&pin=2001&start_dato=2018-10-02T15:00:00', '/api/admin/configuration/v1/ivr_theme/10/', 101, NULL, NULL, NULL, 1, '7cc82183-0d47-439a-a00c-38f7a5a01fc6');
INSERT INTO scheduling_info (id, host_pin, guest_pin, vmravailable_before, vmrstart_time, max_participants, end_meeting_on_end_time, uri_with_domain, uri_without_domain, scheduling_template_id, provision_status, provision_status_description, provision_timestamp,  provisionvmrid, portal_link, ivr_theme, created_by, created_time, updated_by, updated_time, organisation_id, uuid)
VALUES (205, 1001, 2001, 15, '2018-10-02 14:45:00', 10, 1, '1235@test.dk', '1235', 1, 'PROVISIONED_OK', 'all ok', null, null, 'https://portal-test.vconf.dk/?url=1238@test.dk&pin=2001&start_dato=2018-10-02T15:00:00', '/api/admin/configuration/v1/ivr_theme/10/', 101, NULL, NULL, NULL, 1, '7cc82183-0d47-439a-a00c-38f7a5a01fc7');
INSERT INTO scheduling_info (id, host_pin, guest_pin, vmravailable_before, vmrstart_time, max_participants, end_meeting_on_end_time, uri_with_domain, uri_without_domain, scheduling_template_id, provision_status, provision_status_description, provision_timestamp,  provisionvmrid, portal_link, ivr_theme, created_by, created_time, updated_by, updated_time, organisation_id, uuid)
VALUES (206, 1001, 2001, 15, '2018-10-02 14:45:00', 10, 1, '1236@test.dk', '1236', 1, 'PROVISIONED_OK', 'all ok', null, null, 'https://portal-test.vconf.dk/?url=1238@test.dk&pin=2001&start_dato=2018-10-02T15:00:00', '/api/admin/configuration/v1/ivr_theme/10/', 101, NULL, NULL, NULL, 1, '7cc82183-0d47-439a-a00c-38f7a5a01fc8');
INSERT INTO scheduling_info (id, host_pin, guest_pin, vmravailable_before, vmrstart_time, max_participants, end_meeting_on_end_time, uri_with_domain, uri_without_domain, scheduling_template_id, provision_status, provision_status_description, provision_timestamp,  provisionvmrid, portal_link, ivr_theme, created_by, created_time, updated_by, updated_time, organisation_id, uuid)
VALUES (207, 1001, 2001, 15, '2018-10-02 14:45:00', 10, 1, '1237@test.dk', '1237', 1, 'PROVISIONED_OK', 'all ok', null, null, 'https://portal-test.vconf.dk/?url=1238@test.dk&pin=2001&start_dato=2018-10-02T15:00:00', '/api/admin/configuration/v1/ivr_theme/10/', 101, NULL, NULL, NULL, 1, '7cc82183-0d47-439a-a00c-38f7a5a01fc9');

-- * scheduling_status *

-- * meeting_labels *
