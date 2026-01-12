CREATE TABLE IF NOT EXISTS tbl_consultation_session (
                                                        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                                        doctor_id UUID NOT NULL,
                                                        session_time TIMESTAMP NOT NULL,
                                                        max_patients INT NOT NULL DEFAULT 0,
                                                        booked_patients INT NOT NULL DEFAULT 0,
                                                        CONSTRAINT fk_consultation_doctor
                                                            FOREIGN KEY (doctor_id)
                                                                REFERENCES tbl_doctor(id)
);
