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
			{"name":"statistical modeling","value":106032.34423076923},
			{"name":"online advertising","value":105790.09589041096},
			{"name":"computer vision","value":105438.23966165414},
			{"name":"cloud computing","value":101735.65653153154},
			{"name":"postgresql","value":101333.175},
			{"name":"bash","value":101332.32329842931},
			{"name":"c/c++","value":101306.6747141042},
			{"name":"php","value":101180.15754189945},
			{"name":"eclipse","value":100013.15366972476},
			{"name":"objective-c","value":99863.37301366044},
			{"name":"device drivers","value":99649.83302238806},
			{"name":"machine learning","value":99383.57899120296},
			{"name":"hadoop","value":99313.21537108126},
			{"name":"information retrieval","value":99271.9743002128},
			{"name":"python","value":99226.72202724768},
			{"name":".net","value":99168.84190231362},
			{"name":"distributed systems","value":99058.60992379059},
			{"name":"ruby","value":99042.79091730311},
			{"name":"ruby on rails","value":99019.85831960461},
			{"name":"android","value":98980.55007949125},
			{"name":"wireless networking","value":98938.63209326247},
			{"name":"c","value":98932.19998600028},
			{"name":"git","value":98931.52005303281},
			{"name":"java","value":98923.88157797624},
			{"name":"c++","value":98886.22021027413}
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
				var num = 300 - 0.8 * (d.value - 95000) / 50;
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