-- Load LinkedIn data in JSON type.
linkedin = LOAD '/Users/longyang/git/RealTimeBigData/data/linkedin/companyPositionSkill.json' 
	USING JsonLoader('position: chararray, skill: chararray, company: chararray');
-- Load Glassdoor data in JSON type.
glassdoor = LOAD '/Users/longyang/git/RealTimeBigData/data/glassdoor/companyPositionSalary.json' 
	USING JsonLoader('position: chararray, company: chararray, salary: int');
-- Filter the glassdoor data with limitation of salary not more than 500,000
glassdoor = FILTER glassdoor BY salary < 500000;
-- Join linkedin and glassdoor data 
joint = JOIN glassdoor BY (position, company), linkedin BY (position, company);
-- Clear the joint data
cleanJoint = FOREACH joint GENERATE  
	linkedin::company AS company, linkedin::position AS position, 
	glassdoor::salary AS salary, linkedin::skill AS skill;
-- Group the data by skill
grouped = GROUP cleanJoint BY skill;
-- Clean up the grouped data
cleanGrouped = FOREACH grouped GENERATE group AS skill, 
	cleanJoint AS (cleanJoint: {info: tuple(company: chararray, position: chararray, salary: int, skill: chararray)});
-- Filter the groups with less than 5 entities
cleanGrouped = FILTER  cleanGrouped BY COUNT(cleanJoint) > 5;
-- Generate the only skill-salarys relation
skillSalarys = FOREACH cleanGrouped GENERATE skill, 
	cleanJoint.salary as (salarys: {info: tuple(salary: int)});
-- Generate skill and average salary pair.
skillAverageSalary = FOREACH skillSalarys GENERATE skill, AVG(salarys.salary) as averageSalary;
-- Sort the skill based on average salary
sortedSkillAverageSalary = ORDER skillAverageSalary BY averageSalary DESC;

-- Compute the counts for each skills
skillCounts = FOREACH cleanGrouped GENERATE skill, COUNT(cleanJoint) AS count;
-- Sort the skills with counts in descending order.
sortedSkillCounts = ORDER skillCounts BY count DESC;

-- Store the data
STORE cleanGrouped INTO '/Users/longyang/git/RealTimeBigData/data/test/grouped' USING JsonStorage();
STORE skillSalarys INTO '/Users/longyang/git/RealTimeBigData/data/test/skillSalarys' USING JsonStorage();
STORE sortedSkillCounts INTO '/Users/longyang/git/RealTimeBigData/data/test/sortedSkillCounts' USING JsonStorage();
STORE sortedSkillAverageSalary INTO '/Users/longyang/git/RealTimeBigData/data/test/sortedSkillAverageSalary' USING JsonStorage();