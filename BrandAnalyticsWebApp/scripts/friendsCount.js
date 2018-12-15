function getFriendsCount() {
	var dataPoints = [];

	var chart = new CanvasJS.Chart("friendsCountContainer", {
		animationEnabled: true,
		exportEnabled: true,
		title:{
			text: "Top 10 Users Based On Number Of Friends "
		},
		data: [{
			type: "pyramid",
			indexLabelFontSize: 18,
			valueRepresents: "area",
			showInLegend: true,
			legendText: "{indexLabel}",
			toolTipContent: "<b>{indexLabel}:</b> {y}%",
			dataPoints: dataPoints
		}]
	});

	function addData(data) {
    Object.keys(data).forEach(function(key) {
      dataPoints.push({
        label: key,
        y: Number(data[key])
      });
    })
    chart.render();

  }

  var req = ajax('http://134.193.128.69:9090/BrandAnalyticsDemo/FriendsCount','GET');
  req.onreadystatechange = function() {
    if (req.readyState == 4 && req.status == 200) {
      output = JSON.parse(req.responseText)
      addData(output);
    }
  };
}
