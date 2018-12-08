function getLangDiversity() {

  var dataPoints = [];

  var chart = new CanvasJS.Chart("languageDist", {
    animationEnabled: true,

    title:{
      text:"Distribution of Tweet Languages"
    },
    axisX:{
      interval: 1
    },
    axisY2:{
      interlacedColor: "rgba(1,77,101,.2)",
      gridColor: "rgba(1,77,101,.1)",
      title: "Tweet Count"
    },
    data: [{
      type: "bar",
      name: "companies",
      axisYType: "secondary",
      color: "#014D65",
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

  var req = ajax('http://localhost:8080/BrandAnalyticsDemo/DistLang','GET');
  req.onreadystatechange = function() {
    if (req.readyState == 4 && req.status == 200) {
      output = JSON.parse(req.responseText);
      addData(output);
    }
  };

};
