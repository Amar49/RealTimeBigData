import json
import requests
from BeautifulSoup import BeautifulSoup as Soup, Tag

dest_path = 'company-position-skills.json'
source_path = 'company-position.json'
json_data = open(source_path)
data = json.load(json_data)
obj = open(dest_path, 'w')
obj.write('[')

for comp in data:
  urls = comp['urls']
  skills_matrix = []
  obj.write('{ "company": "' + comp['company'] + '", ')
  obj.write('"position": "' + comp['position'] + '", ')
  obj.write('"skills": [');
  for url in urls:
    skills = []
    response = requests.get(url)
    parsed_html = Soup(response.content)
    skillLists = parsed_html.body.find('ol', attrs={'id':'skills-list'})
    
    for skill in skillLists.contents:
      if isinstance(skill, Tag):
        skills.append(skill.text)
    if url == urls[-1]:
      obj.write('["' + '", "'.join(skills) + '"]')
    else:
      obj.write('["' + '", "'.join(skills) + '"],') 
    skills_matrix.append(skills)
  if comp == data[-1]:
    print 'true'
    obj.write('] } \n')
  else:
    obj.write('] },\n')
  obj.flush()
obj.write(']')
obj.close()
