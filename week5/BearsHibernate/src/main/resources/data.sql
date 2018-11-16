set schema 'bears';

INSERT INTO cave (sq_footage, caveType)	
	VALUES (5000, 'Limestone'),
	(100, 'Diamond'),
	(1000, 'Wood');

INSERT INTO bears (cave_id,	color, breed, legs)
	VALUES 
	((SELECT cave_id FROM cave WHERE sq_footage = 5000), 'brown', 'brown', 4),
	((SELECT cave_id FROM cave WHERE sq_footage = 5000), 'maroon', 'brown', 4),
	((SELECT cave_id FROM cave WHERE sq_footage = 5000), 'white', 'polar', 4),
	((SELECT cave_id FROM cave WHERE sq_footage = 5000), 'black', 'black', 4),
	((SELECT cave_id FROM cave WHERE sq_footage = 100), 'brown', 'brown', 4),
	((SELECT cave_id FROM cave WHERE sq_footage = 100), 'black and white', 'panda', 4),
	((SELECT cave_id FROM cave WHERE sq_footage = 100), 'brown', 'teddy', 3);
	

INSERT INTO parent_cub (parent_id, cub_id)
	VALUES (1, 2),
	(2, 3),
	(1, 4),
	(6, 7);

