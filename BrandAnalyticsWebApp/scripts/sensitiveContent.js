function getSenstiveContentDist() {
  var dataPoints = [];

  var chart = new CanvasJS.Chart("sensitiveCont", {
    animationEnabled: true,
    title:{
      text: "Sensitive Content Statistics",
      horizontalAlign: "left"
    },
    data: [{
      type: "doughnut",
      startAngle: 60,
      //innerRadius: 60,
      indexLabelFontSize: 17,
      indexLabel: "{label} - #percent%",
      toolTipContent: "<b>{label}:</b> {y} (#percent%)",
      dataPoints: dataPoints
    }]
  });

  var req = ajax('http://134.193.128.69:9090/BrandAnalyticsDemo/SensitiveTweets','GET');
  req.onreadystatechange = function() {
    if (req.readyState == 4 && req.status == 200) {
      output = JSON.parse(req.responseText)
      addData(output);
    }
  };

  function addData(data) {
    Object.keys(data).forEach(function(key) {
      dataPoints.push({
        label: key,
        y: Number(data[key])
      });
    })
    chart.render();
  }
}
