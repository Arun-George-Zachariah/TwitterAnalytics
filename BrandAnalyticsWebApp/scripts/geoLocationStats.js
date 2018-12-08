function getGeoLocation() {
  var items = [
    ['Country', 'Tweet Count'],
  ];

  google.charts.load('current', {
    'packages':['geochart'],
    'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'
  });
  google.charts.setOnLoadCallback(drawRegionsMap);

  var req = ajax('http://localhost:8080/BrandAnalyticsDemo/GeoLocationStats','GET');
  req.onreadystatechange = function() {
    if (req.readyState == 4 && req.status == 200) {
      output = JSON.parse(req.responseText);
      Object.keys(output).forEach(function(key) {
        items.push([key,Number(output[key])])
        drawRegionsMap(items)
      })
    }
  };
}

function drawRegionsMap(items) {
  var data = google.visualization.arrayToDataTable(items);
  var options = {};
  var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));
  chart.draw(data, options);
}
