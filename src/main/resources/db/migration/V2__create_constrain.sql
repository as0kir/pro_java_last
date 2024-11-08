ALTER TABLE IF EXISTS limits
    ADD CONSTRAINT user_id_unique UNIQUE (user_id);