CREATE TABLE IF NOT EXISTS tbl_billing_account (
                                                   id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                                   patient_id UUID NOT NULL UNIQUE, -- from patient-service
                                                   status VARCHAR(20) NOT NULL,      -- ACTIVE, SUSPENDED, CLOSED
                                                   balance DECIMAL(12,2) NOT NULL DEFAULT 0,
                                                   currency VARCHAR(10) DEFAULT 'LKR',
                                                   created_at TIMESTAMP NOT NULL
);
