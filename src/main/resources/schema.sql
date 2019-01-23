DROP TABLE public.entity_with_range;

CREATE TABLE IF NOT EXISTS public.entity_with_range (
    id uuid NOT NULL,
    "name" varchar(255) NULL,
    "range" tstzrange NULL,
    CONSTRAINT entity_with_range_pkey PRIMARY KEY (id)
);
