CREATE TABLE slot (
	id int8 NOT NULL,
	start_time timestamp NULL,
	end_time timestamp NULL,
    created_at timestamp NULL,
	modified_at timestamp NULL,
	CONSTRAINT slot_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE slot_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;