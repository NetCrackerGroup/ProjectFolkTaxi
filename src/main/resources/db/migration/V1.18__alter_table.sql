ALTER TABLE journeys DROP COLUMN driver_id;
UPDATE Driver_Rating SET Average_Mark = 0;
ALTER TABLE Driver_Rating ADD Number_Of_Votes NUMERIC DEFAULT 0;
