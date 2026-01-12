CREATE TABLE tbl_appointment (
                                  id UUID primary key DEFAULT gen_random_uuid(),
                                  session_id UUID NOT NULL,
                                  patient_id UUID NOT NULL,
                                  created_at TIMESTAMP,
                                  appointment_status VARCHAR(10),
                                  appointment_date_time TIMESTAMP

);


