function getTotalUsers() {
  var req = ajax('http://localhost:8080/BrandAnalyticsDemo/TotalCount','GET');
  req.onreadystatechange = function() {
    if (req.readyState == 4 && req.status == 200) {
      output = JSON.parse(req.responseText);
      Object.keys(output).forEach(function(key) {
        console.log(output[key]);
        $("#totalUsers").append(document.createTextNode("Total Users Reached : " + output[key]));
      })

    }
  };
}
