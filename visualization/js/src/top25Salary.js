
$(function () {
	$('#containerSalary').highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: 'Top 25 Payed Skills'
		},
		subtitle: {
			text: ''
		},
		xAxis: {
			type: 'category',
			labels: {
				rotation: -45,
				align: 'right',
				style: {
					fontSize: '13px',
					fontFamily: 'Verdana, sans-serif'
				}
			}
		},
		yAxis: {
			min: 0,
			gridLineWidth: 2,
			title: {
				text: 'Average Salary (dollar)'
			}
		},
		legend: {
			enabled: true
		},
		tooltip: {
			pointFormat: 'salary: <b>{point.y:.1f}</b>'
		},
		series: [{
			name: 'Salary',
			colorByPoint: true,
			data: [
				["statistical modeling",106032],
				["online advertising",105790],
				["computer vision",105438],
				["cloud computing",101735],
				["postgresql",101333],
				["bash",101332],
				["c/c++",101306],
				["php",101180],
				["eclipse",100013],
				["objective-c",99863],
				["device drivers",99649],
				["machine learning",99383],
				["hadoop",99313],
				["information retrieval",99271],
				["python",99226],
				[".net",99168],
				["distributed systems",99058],
				["ruby",99042],
				["ruby on rails",99019],
				["android",98980],
				["wireless networking",98938],
				["c",98932],
				["git",98931],
				["java",98923],
				["c++",98886]
			],
			dataLabels: {
				enabled: true,
				rotation: -90,
				color: '#FFFFFF',
				align: 'right',
				x: 4,
				y: 10,
				style: {
					fontSize: '13px',
					fontFamily: 'Verdana, sans-serif',
					textShadow: '0 0 3px black'
				}
			}
		}]
	});
});

