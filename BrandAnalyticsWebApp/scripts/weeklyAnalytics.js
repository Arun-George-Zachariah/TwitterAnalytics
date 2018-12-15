function getWeeklyData() {

  var dataPoints = [];

  var chart = new CanvasJS.Chart("weekyAnalyticsContainer", {
    animationEnabled: true,
    theme: "light2",
    title: {
      text: "Weekly Analytics"
    },
    axisY: {
      title: "Tweets Count",
      titleFontSize: 24
    },
    data: [{
      type: "column",
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

  var req = ajax('http://134.193.128.69:9090/BrandAnalyticsDemo/WeeklyAnalytics','GET');
  req.onreadystatechange = function() {
    if (req.readyState == 4 && req.status == 200) {
      output = JSON.parse(req.responseText)
      addData(output);
    }
  };

}
