create table limits
(
    id bigserial primary key,
    user_id bigint,
    sum_limit numeric(15, 2)
);

alter sequence limits_id_seq owner to postgres;
alter sequence limits_id_seq owned by limits.id;
grant select, update, usage on sequence limits_id_seq to tst_user;

alter table limits owner to postgres;
grant delete, insert, select, update on limits to tst_user;



