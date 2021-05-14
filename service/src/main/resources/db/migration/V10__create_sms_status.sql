CREATE TABLE sms_status (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  organisation_code varchar(30) not null,
  reference_id varchar(36) not null,
  status varchar(20) not null,
  sms_gateway_reference varchar(36),
  PRIMARY KEY (id)
)
;
