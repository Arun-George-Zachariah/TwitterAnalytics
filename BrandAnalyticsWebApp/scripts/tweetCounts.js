function getTweetCount() {
  var dataPoints = [];


  var chart = new CanvasJS.Chart("tweetCountContainer", {
  	animationEnabled: true,
  	title: {
  		text: "Tweet Distribution Among Brands."
  	},
  	data: [{
  		type: "pie",
  		startAngle: 240,
  		indexLabel: "{label} : {y}",
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
    console.log(dataPoints)
    chart.render();
  }

  var req = ajax('http://localhost:8080/BrandAnalyticsDemo/BrandTweets','GET');
  req.onreadystatechange = function() {
    if (req.readyState == 4 && req.status == 200) {
      output = JSON.parse(req.responseText)
      addData(output);
    }
  };

}
