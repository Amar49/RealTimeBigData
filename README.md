# RTBD - XYZ Team

## Overview
Repository for "Real Time and Big Data Analytics" course project: 

## Team Member
- [Jing <b>X</b>ia](https://github.com/xiajingcici)
- [Long <b>Y</b>ang](https://github.com/LongYang0806)
- [ZhaoHui <b>Z</b>hang](https://github.com/zhaohuizh)

## Project Details
- <i>Course website</i>: [Realtime and Big Data Analytics CSCI-GA.3033-008](http://cs.nyu.edu/courses/spring14/CSCI-GA.3033-008/index.html)

- <i>Project Name</i>: LinkedIn, Glassdoor Skill and Salary Analysis

- <i>Project data sets source</i>: [Glassdoor](http://www.glassdoor.com), [LinkedIn](http://www.linkedin.com) (got approves, but applying for more quota), [Twitter](http://www.twitter.com) (optional)

- <i>Main flow</i> :
  - <i>Main working flow</i>
    - Use Python crawler program get JSON data from Glassdoor, and you can find example data in 
    [Example data for Glassdoor](data/glassdoor/company.json), at first stage, we will first focus 
    on certain Industry, such as "Information Technology". In the future, we will sort the data from
    Glassdoor based on the appearance frequency of each industry.
    - Use the data got from Glassdoor <Company, title> to do [People Search](http://developer.linkedin.com/documents/people-search-api) on LinkedIn to get
    <company&title, skill set> pairs.
    - Do ranking for skill sets inside company&title based on the appearance frequency of each skill, 
    such as: java, c++, HTML/JavaScript. And we just use the top 5 skills for each company&title. To
    get the <Company&Title, top 5 skills>. In the future, we want to do ranking on skills inside each
    industry.
    - Pair the <Company&Title, salary> with <Company&Title, top 5 skills> and compute average range
    for each skill to give recommendations to college students when choosing skills to learn.
    - Based on the ranking of skills inside each industry and data from Twitter([reference](http://twitter4j.org/en/index.html)) to rank the skills
    hotness inside each industry.
     
  - <i>Flow Graph</i>
![alt tag](https://dl.dropboxusercontent.com/u/108110380/RTBD-XYZ/Project's%20Work%20and%20Data%20Flow.png)


