create table if not exists tbl_consultation_session (
                                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                          doctor_id UUID not null,
                                          time TIMESTAMP not null,
                                          number_of_patients int
);
