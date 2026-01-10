CREATE TABLE tbl_doctor (
                            id UUID PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
);

CREATE TABLE doctor_specializations (
                                        doctor_id UUID REFERENCES tbl_doctor(id) ON DELETE CASCADE,
                                        specialization VARCHAR(255)
);