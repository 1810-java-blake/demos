set schema 'bears';

CREATE TABLE cave (
	cave_id SERIAL PRIMARY KEY,
	sq_footage DOUBLE PRECISION NOT NULL,
	caveType TEXT
);

CREATE TABLE bears (
	bear_id SERIAL PRIMARY KEY,
	cave_id INTEGER REFERENCES cave(cave_id),
	color TEXT NOT NULL,
	breed TEXT NOT NULL,
	legs INTEGER
);

CREATE TABLE parent_cub (
	parent_id INTEGER REFERENCES bears(bear_id) NOT NULL,
	cub_id INTEGER REFERENCES bears(bear_id) CHECK (parent_id != cub_id) NOT NULL,
	CONSTRAINT pk_parent_cub PRIMARY KEY (parent_id, cub_id)
);

