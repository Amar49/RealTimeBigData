var m = [50, 20, 20, 150],
w = $( window ).width() - 2 * m[1] - 2 * m[3],
h = 0.9 * $( window ).height() - m[0] - m[2];

var format = d3.format(",.0f");

var x = d3.scale.linear().range([0, w]),
y = d3.scale.ordinal().rangeRoundBands([0, h], .1);

var xAxis = d3.svg.axis().scale(x).orient("top").tickSize(-h),
yAxis = d3.svg.axis().scale(y).orient("left").tickSize(0);

var svg = d3.select("body").append("svg")
						.attr("width", w + m[1] + m[3])
						.attr("height", h + m[0] + m[2])
						.append("g")
						.attr("transform", "translate(" + m[3] + "," + m[0] + ")");

var data = [
	{"name":"java","value":108392},
	{"name":"c++","value":93497},
	{"name":"python","value":82062},
	{"name":"javascript","value":48066},
	{"name":"machine learning","value":38422},
	{"name":"distributed systems","value":36216},
	{"name":"linux","value":31181},
	{"name":"sql","value":30470},
	{"name":"c","value":28572},
	{"name":"matlab","value":24174},
	{"name":"git","value":24136},
	{"name":"data mining","value":17312},
	{"name":"latex","value":17262},
	{"name":"perl","value":13107},
	{"name":"c#","value":12606},
	{"name":"hadoop","value":12504},
	{"name":"ruby","value":12177},
	{"name":"jquery","value":11731},
	{"name":"html","value":11689},
	{"name":"natural language processing","value":11643},
	{"name":"mapreduce","value":11522},
	{"name":"image processing","value":11498},
	{"name":"network programming","value":11498}
];

// Parse numbers, and sort by value.
data.forEach(function(d) { d.value = +d.value; });
data.sort(function(a, b) { return b.value - a.value; });

// Set the scale domain.
x.domain([0, d3.max(data, function(d) { return d.value; })]);
y.domain(data.map(function(d) { return d.name; }));

var bar = svg.selectAll("g.bar")
							.data(data)
							.enter().append("g")
							.attr("class", "bar")
							.attr("transform", function(d) { return "translate(0," + y(d.name) + ")"; });

bar.append("rect")
		.attr("width", function(d) { return x(d.value)})
		.attr("height", y.rangeBand())
		.attr("fill", function(d) {
				var num = 255 - 1.5 * d.value / 1000;
				var color = "rgb(" + 0 + "," + parseInt(num) + "," + parseInt(num) + ")";
				console.log(color);
				return color;
				});

bar.append("text")
		.attr("class", "value")
		.attr("x", function(d) { return x(d.value); })
		.attr("y", y.rangeBand() / 2)
		.attr("dx", -3)
		.attr("dy", ".35em")
		.attr("text-anchor", "end")
		.text(function(d) { return format(d.value); });

svg.append("g")
		.attr("class", "x axis")
		.call(xAxis);

svg.append("g")
		.attr("class", "y axis")
		.call(yAxis);