// Set the size for the main div
document.getElementById('container').style.width = $( window ).width();
document.getElementById('container').style.height = $( window ).height();

var election = new Datamap({
	element: document.getElementById('container'),
	scope: 'usa',
	geographyConfig: {
		highlightBorderColor: '#bada55',
		popupTemplate: function(geography, data) {
			var res = '<div class="hoverinfo">' + '<span class="stateName">' + geography.properties.name + '</span>'
								+ '<br/><br/>';
			var skills = data.skills;
			for (var index in skills) {
				var skill = skills[index];
				res += '<span class="skillName">' + skill.skill + '</span>' +
								" : " + '<span class="avgSalary">' + parseInt(skill.avgSalary) + '</span>' + '<br />';
			}

			res += '</div>';
			return res;
		},
		highlightBorderWidth: 3
	},

	fills: {
		'Republican': '#CC4731',
		'Democrat': '#306596',
		'Heavy Democrat': '#667FAF',
		'Light Democrat': '#A9C0DE',
		'Heavy Republican': '#CA5E5B',
		'Light Republican': '#EAA9A8',
		defaultFill: '#EDDC4E'
	},

	data:{
		"AZ": {
			"fillKey": "Republican",
			"skills" : [
				{"skill":"java enterprise edition","avgSalary":114638},
				{"skill":"java","avgSalary":114638},
				{"skill":"jaxb","avgSalary":114638},
				{"skill":"checkstyle","avgSalary":114638},
				{"skill":"objective-c","avgSalary":114638}
			]
		},
		"CO": {
			"fillKey": "Light Democrat",
			"skills" : [
				{"skill":"pmp","avgSalary":94567.0},
				{"skill":"strategic planning","avgSalary":94567.0},
				{"skill":"process improvement","avgSalary":94567.0},
				{"skill":"vendor management","avgSalary":82436.28571428571},
				{"skill":"resource management","avgSalary":82436.28571428571}
			]
		},
		"DE": {
			"fillKey": "Democrat",
			"skills" : []
		},
		"FL": {
			"fillKey": "UNDECIDED",
			"skills": [
				{"skill":"unix","avgSalary":74772},
				{"skill":"pl/sql","avgSalary":74772},
				{"skill":"testing","avgSalary":74772},
				{"skill":"databases","avgSalary":74772},
				{"skill":"data analysis","avgSalary":74772}
			]
		},
		"GA": {
			"fillKey": "Republican",
			"skills" : [
				{"skill":"business analysis","avgSalary":95610.25},
				{"skill":"business intelligence","avgSalary":95610.25},
				{"skill":"requirements analysis","avgSalary":95610.25},
				{"skill":"supply chain management","avgSalary":95610.25},
				{"skill":"erp","avgSalary":95610.25}
			]
		},
		"HI": {
			"fillKey": "Democrat",
			"skills" : []
		},
		"ID": {
			"fillKey": "Republican",
			"skills" : []
		},
		"IL": {
			"fillKey": "Democrat",
			"skills" : [
				{"skill":"c#","avgSalary":86868},
				{"skill":"sql","avgSalary":86868},
				{"skill":"vb.net","avgSalary":86868},
				{"skill":"microsoft access","avgSalary":86868},
				{"skill":"microsoft sql server","avgSalary":86868}
			]
		},
		"IN": {
			"fillKey": "Republican",
			"skills" : []
		},
		"IA": {
			"fillKey": "Light Democrat",
			"skills" : [
				{"skill":"asp.net","avgSalary":50814.0},
				{"skill":"c# 2.0/3.5","avgSalary":50814.0},
				{"skill":"rdbms","avgSalary":50814.0},
				{"skill":"javascript","avgSalary":50814.0},
				{"skill":"vb.net","avgSalary":50814.0}
			]
		},
		"KS": {
			"fillKey": "Republican",
			"skills" : []
		},
		"KY": {
			"fillKey": "Republican",
			"skills" : []
		},
		"LA": {
			"fillKey": "Republican",
			"skills" : []
		},
		"MD": {
			"fillKey": "Democrat",
			"skills" : [
				{"skill":"jsp","avgSalary":53223.608695652176},
				{"skill":"xml","avgSalary":53223.608695652176},
				{"skill":"microsoft sql server","avgSalary":53223.608695652176}
			]
		},
		"ME": {
			"fillKey": "Democrat",
			"skills" : [
				{"skill":"strategic planning","avgSalary":69098.0},
				{"skill":"team building","avgSalary":69098.0},
				{"skill":"customer service","avgSalary":69098.0},
				{"skill":"business analysis","avgSalary":69098.0},
				{"skill":"new business development","avgSalary":69098.0}
			]
		},
		"MA": {
			"fillKey": "Democrat",
			"skills" : [
				{"skill":"c#","avgSalary":81774},
				{"skill":"html","avgSalary":79477},
				{"skill":"javascript","avgSalary":79477},
				{"skill":"java","avgSalary":77143},
				{"skill":"mysql","avgSalary":76773}
			]
		},
		"MN": {
			"fillKey": "Democrat",
			"skills" : []
		},
		"MI": {
			"fillKey": "Democrat",
			"skills" : [
				{"skill":"pmp","avgSalary":91289.73869346734},
				{"skill":"java enterprise edition","avgSalary":91289.73869346734},
				{"skill":"software project management","avgSalary":91289.73869346734},
				{"skill":"spring","avgSalary":91289.73869346734},
				{"skill":"requirements analysis","avgSalary":91289.73869346734}
			]
		},
		"MS": {
			"fillKey": "Republican",
			"skills" : []
		},
		"MO": {
			"fillKey": "Republican",
			"skills" : [
				{"skill":"javascript","avgSalary":92495.6},
				{"skill":"xml","avgSalary":78672.75},
				{"skill":"filenet","avgSalary":78672.75},
				{"skill":"enterprise content management","avgSalary":78672.75},
				{"skill":"business process management","avgSalary":78239.20132013201}
			]
		},
		"MT": {
			"fillKey": "Republican",
			"skills" : []
		},
		"NC": {
			"fillKey": "Light Republican",
			"skills" : [
				{"skill":"spring","avgSalary":71112.85106382979},
				{"skill":"struts","avgSalary":71112.85106382979},
				{"skill":"hibernate","avgSalary":71112.85106382979},
				{"skill":"software development","avgSalary":71112.85106382979},
				{"skill":"java enterprise edition","avgSalary":71112.85106382979}
			]
		},
		"NE": {
			"fillKey": "Republican",
			"skills" : []
		},
		"NV": {
			"fillKey": "Heavy Democrat",
			"skills" : [
				{"skill":"c","avgSalary":59530.0},
				{"skill":"c++","avgSalary":59530.0},
				{"skill":"haskell","avgSalary":59530.0},
				{"skill":"software development","avgSalary":59530.0},
				{"skill":"software engineering","avgSalary":59530.0}
			]
		},
		"NH": {
			"fillKey": "Light Democrat",
			"skills" : []
		},
		"NJ": {
			"fillKey": "Democrat",
			"skills" : [
				{"skill":"ms project","avgSalary":93850},
				{"skill":"sql server","avgSalary":93850},
				{"skill":"microsoft sql server","avgSalary":64912},
				{"skill":".net","avgSalary":62843},
				{"skill":"oracle","avgSalary":62843},
				{"skill":"requirements analysis","avgSalary":62843},
				{"skill":"pl/sql","avgSalary":60339},
				{"skill":"spring","avgSalary":60333},
				{"skill":"struts","avgSalary":60333},
				{"skill":"weblogic","avgSalary":60333},
				{"skill":"core java","avgSalary":60333},
				{"skill":"sql","avgSalary":59054},
				{"skill":"c++","avgSalary":55266},
				{"skill":"ssis","avgSalary":55266},
				{"skill":"t-sql","avgSalary":55266},
				{"skill":"adp","avgSalary":52541},
				{"skill":"hris","avgSalary":52541}
			]
		},
		"NY": {
			"fillKey": "Democrat",
			"skills" : [
				{"skill":"cpa","avgSalary":95555},
				{"skill":"ant","avgSalary":93494},
				{"skill":"c#","avgSalary":92944},
				{"skill":"soa","avgSalary":88894},
				{"skill":"c++","avgSalary":87876},
				{"skill":"video games","avgSalary":87714},
				{"skill":"r","avgSalary":87714},
				{"skill":"git","avgSalary":87714},
				{"skill":"mvc","avgSalary":87714},
				{"skill":"php","avgSalary":87714},
				{"skill":"sql","avgSalary":87714},
				{"skill":"vba","avgSalary":87714},
				{"skill":"html","avgSalary":87714},
				{"skill":"perl","avgSalary":87714},
				{"skill":"rtos","avgSalary":87714}
			]
		},
		"ND": {
			"fillKey": "Republican",
			"skills" : []
		},
		"NM": {
			"fillKey": "Democrat",
			"skills" : []
		},
		"OH": {
			"fillKey": "UNDECIDED",
			"skills" : [
				{"skill":"agile methodologies","avgSalary":81064.53333333334},
				{"skill":"xml","avgSalary":69893.4},
				{"skill":"sql","avgSalary":64312.57894736842},
				{"skill":"asp.net","avgSalary":64143.833333333336},
				{"skill":"silverlight","avgSalary":64143.833333333336}
			]
		},
		"OK": {
			"fillKey": "Republican",
			"skills" : []
		},
		"OR": {
			"fillKey": "Democrat",
			"skills" : [
				{"skill":"ajax","avgSalary":79975.66666666667},
				{"skill":"business analysis","avgSalary":79975.66666666667},
				{"skill":"vb.net","avgSalary":79975.66666666667},
				{"skill":"asp.net","avgSalary":79975.66666666667},
				{"skill":"erp","avgSalary":79975.66666666667}
			]
		},
		"PA": {
			"fillKey": "Democrat",
			"skills" : [
				{"skill":"matlab","avgSalary":73342.11764705883},
				{"skill":"optimizations","avgSalary":73342.11764705883},
				{"skill":"computer science","avgSalary":73342.11764705883},
				{"skill":"machine learning","avgSalary":73342.11764705883},
				{"skill":"signal processing","avgSalary":73342.11764705883}
			]
		},
		"RI": {
			"fillKey": "Democrat",
			"skills" : []
		},
		"SC": {
			"fillKey": "Republican",
			"skills" : [
				{"skill":"c++","avgSalary":67000.0},
				{"skill":"sql","avgSalary":67000.0},
				{"skill":".net","avgSalary":67000.0},
				{"skill":"java","avgSalary":67000.0},
				{"skill":"creative writing","avgSalary":67000.0}
			]
		},
		"SD": {
			"fillKey": "Republican",
			"skills" : []
		},
		"TN": {
			"fillKey": "Republican",
			"skills" : []
		},
		"TX": {
			"fillKey": "Republican",
			"skills" : [
				{"skill":"javascript","avgSalary":116394.18181818182},
				{"skill":"web services","avgSalary":93337.04545454546},
				{"skill":"xml","avgSalary":92474.20512820513},
				{"skill":"filenet","avgSalary":92474.20512820513},
				{"skill":"enterprise content management","avgSalary":92474.20512820513}
			]
		},
		"UT": {
			"fillKey": "Republican",
			"skills" : [
				{"skill":"php","avgSalary":61830.5},
				{"skill":"django","avgSalary":61830.5},
				{"skill":"heroku","avgSalary":61830.5},
				{"skill":"python","avgSalary":61830.5},
				{"skill":"postgresql","avgSalary":61830.5}
			]
		},
		"WI": {
			"fillKey": "Democrat",
			"skills" : [
				{"skill":"c","avgSalary":55887.653333333335},
				{"skill":"c#","avgSalary":55887.653333333335},
				{"skill":"c++","avgSalary":55887.653333333335},
				{"skill":"sql","avgSalary":55887.653333333335},
				{"skill":"sdlc","avgSalary":55887.653333333335}
			]
		},
		"VA": {
			"fillKey": "Light Democrat",
			"skills" : [
				{"skill":"python","avgSalary":78519.26923076923},
				{"skill":"linux","avgSalary":76862.125},
				{"skill":"devops","avgSalary":76862.125},
				{"skill":"django","avgSalary":76862.125},
				{"skill":"unix","avgSalary":76786.16}
			]
		},
		"VT": {
			"fillKey": "Democrat",
			"skills" : []
		},
		"WA": {
			"fillKey": "Democrat",
			"skills" : [
				{"skill":"jquery mobile","avgSalary":90147.05555555556},
				{"skill":"mobile web development","avgSalary":90147.05555555556},
				{"skill":"software development","avgSalary":87858.42857142857},
				{"skill":"jquery","avgSalary":87132.35},
				{"skill":"javascript","avgSalary":86777.45454545454}
			]
		},
		"WV": {
			"fillKey": "Republican",
			"skills" : []
		},
		"WY": {
			"fillKey": "Republican",
			"skills" : []
		},
		"CA": {
			"fillKey": "Democrat",
			"skills" : [
				{"skill":"statistical modeling","avgSalary":106032},
				{"skill":"computer vision","avgSalary":105438},
				{"skill":"php","avgSalary":101228},
				{"skill":"objective-c","avgSalary":99846},
				{"skill":"web development","avgSalary":99749},
				{"skill":"machine learning","avgSalary":99408},
				{"skill":"hadoop","avgSalary":99315},
				{"skill":"information retrieval","avgSalary":99273},
				{"skill":"python","avgSalary":99239}
			]
		},
		"CT": {
			"fillKey": "Democrat",
			"skills" : [
				{"skill":"it strategy","avgSalary":199576.0},
				{"skill":"it management","avgSalary":199576.0},
				{"skill":"vendor management","avgSalary":199576.0},
				{"skill":"program management","avgSalary":199576.0},
				{"skill":"business intelligence","avgSalary":199576.0}
			]
		},
		"AK": {
			"fillKey": "Republican",
			"skills" : []
		},
		"AR": {
			"fillKey": "Republican",
			"skills" : []
		},
		"AL": {
			"fillKey": "Republican",
			"skills" : []
		}
	}
});
election.labels();