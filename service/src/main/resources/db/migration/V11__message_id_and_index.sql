alter table sms_status
 add column message_id varchar(36) null after status;

alter table sms_status
 add column meeting_uuid varchar(36) not null after organisation_code;

alter table sms_status modify status varchar(100);

create index message_id on sms_status(message_id);

create index meeting_uuid on sms_status(meeting_uuid);

