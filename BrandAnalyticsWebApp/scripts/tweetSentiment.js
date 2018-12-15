function getTweetSentiment() {
  var req = ajax('http://134.193.128.69:9090/BrandAnalyticsDemo/TweetText','GET');
  req.onreadystatechange = function() {
    if (req.readyState == 4 && req.status == 200) {
      output = JSON.parse(req.responseText);
      Object.keys(output).forEach(function(key) {
        $('#tweetSent').append("<div><p>The Most Retweeted tweet : " + key + "</p></div>");
        if(output[key] == 'pos') {
          console.log("Up")
          $('#up').css('display','block');
        } else if (output[key] == 'neutral') {
          $('#neutral').css('display','block');
        } else {
          $('#down').css('display','block');
        }
      });
    }
  };
}
