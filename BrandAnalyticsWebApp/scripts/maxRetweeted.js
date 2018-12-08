function getMaxRetweeted() {
  var output;

  var ajax = function(url, type) {
    output = "";
    var req = new XMLHttpRequest();
    req.open(type, url, true);
    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    req.send("");
    return req;
  };

  var tweetURL;
  var req = ajax('http://localhost:8080/BrandAnalyticsDemo/MaxRetweeted','GET');
  req.onreadystatechange = function() {
  	if (req.readyState == 4 && req.status == 200) {
  		output = JSON.parse(req.responseText);
  		Object.keys(output).forEach(function(key) {
  			tweetURL="https://twitter.com/" + key + "/status/" + output[key]
  			console.log(tweetURL);
  			$("#retweeted").append("<blockquote class='twitter-tweet'><a href=" + tweetURL + "></a></blockquote><br><script async src='https://platform.twitter.com/widgets.js' charset='utf-8'></" + "script>");
  		});
  	}
  };
}
