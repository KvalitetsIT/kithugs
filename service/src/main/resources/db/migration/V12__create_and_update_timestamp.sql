alter table sms_status
 add column created_time timestamp not null default now();

alter table sms_status
 add column updated_time timestamp not null default now();
