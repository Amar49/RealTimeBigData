// data
var generateRandomData = function() {
	var data = [];
	for(var i = 0; i < 50; i++) {
		var newNumber = Math.round(Math.random() * 30);
		data.push(newNumber);
	}
	return data;
};

var data = generateRandomData();

d3.select("body").selectAll("div")
		.data(data)
		.enter()
		.append("div")
		.attr('class', 'bar')
		.style('height', function(d) {
			var height = d * 10;
			return height + 'px';
		})
		.style('margin-right', '2px');

console.log(typeof d3.selectAll('p'));
console.log(d3.selectAll('p'));

