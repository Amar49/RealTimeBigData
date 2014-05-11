-- Load LinkedIn data in JSON type.
glassdoor = LOAD '/Users/longyang/git/RealTimeBigData/data/glassdoor/CompanyIndustry.json' 
	USING JsonLoader('company: chararray, industry: chararray');
-- Load Glassdoor data in JSON type.
h1b = LOAD '/Users/longyang/git/RealTimeBigData/data/h1b/json/CompanyPositionSalary.json' 
	USING JsonLoader('position: chararray, company: chararray, state: chararray, salary: int');
-- Join the glass and h1b
joint = JOIN glassdoor by company, h1b by company;
cleanJoint = FOREACH joint GENERATE 
	glassdoor::company as company, glassdoor::industry as industry,
	h1b::position as position, h1b::state as state, h1b::salary as salary;
itCompanies = FILTER cleanJoint by industry MATCHES 'information technology';
h1bIT = FOREACH itCompanies GENERATE company, position, state, salary;
STORE itCompanies INTO '/Users/longyang/git/RealTimeBigData/data/test/IT_Companies' USING JsonStorage();
