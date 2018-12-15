function getVerifiedUsers() {
  google.charts.load('visualization', '1', {'packages': ['geochart']});
  google.charts.setOnLoadCallback(drawVisualization);

  function drawVisualization() {var data = new google.visualization.DataTable();
    data.addColumn('number', 'Lat');
    data.addColumn('number', 'Long');
    data.addColumn('number', 'Value');
    data.addColumn({type:'string', role:'tooltip'});

    var req = ajax('http://134.193.128.69:9090/BrandAnalyticsDemo/VerifiedUsers','GET');
    req.onreadystatechange = function() {
      if (req.readyState == 4 && req.status == 200) {
        output = JSON.parse(req.responseText);
        Object.keys(output).forEach(function(key) {
          var coorodinates = output[key].replace("WrappedArray(","").replace(")","").split(", ");
          console.log("lat :: " + Number(coorodinates[0]));
          console.log("long :: " + Number(coorodinates[1]));
          data.addRows([[Number(coorodinates[0]),Number(coorodinates[1]),0,key]]);
        });
        chart.draw(data, options);
      }
    };

    var options = {
      colorAxis:  {minValue: 0, maxValue: 0,  colors: ['#6699CC']},
      legend: 'none',
      backgroundColor: {fill:'transparent',stroke:'#FFF' ,strokeWidth:0 },
      datalessRegionColor: '#f5f5f5',
      displayMode: 'markers',
      enableRegionInteractivity: 'true',
      resolution: 'countries',
      sizeAxis: {minValue: 1, maxValue:1,minSize:5,  maxSize: 5},
      region:'world',
      keepAspectRatio: true,
      width:1000,
      height:500,
      tooltip: {textStyle: {color: '#444444'}}
    };
    var chart = new   google.visualization.GeoChart(document.getElementById('VerifiedUsers'));

  }
}
