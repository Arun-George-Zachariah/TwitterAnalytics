function getUserTweetDates() {
  var dataPoints = [];

  var myConfig = {
    type: 'calendar',
    options: {
      year: {
        text: '2018',
        visible: false
      },
      startMonth: 1,
      endMonth: 6,
      palette: ['none', '#4CAF50'],
      month: {
        item: {
          fontColor: 'gray',
          fontSize: 9
        }
      },
      weekday: {
        values: ['','M','','W','','F',''],
        item:{
          fontColor: 'gray',
          fontSize:9
        }
      },
      values: dataPoints
    },
    labels: [
      { //Lefthand Label (container portion)
        borderColor: 'gray',
        borderWidth: 1,
        x: '28%',
        y: '60%',
        width: '40%',
        height: '30%'
      },
      { //Lefthand Label (top portion)
        text: 'Daily Contribution',
        fontColor: '#212121',
        textAlign: 'center',
        x: '30%',
        y:'65%',
        width: '36%'
      },
      { //Lefthand Label (middle portion)
        text: '%plot-value',
        fontColor: '#4CAF50',
        fontFamily: 'Georgia',
        fontSize: 35,
        textAlign: 'center',
        x: '30%',
        y: '68%',
        width: '36%'
      }
    ],

    tooltip : { //Lefthand Label (bottom portion)
      text: '%data-day',
      backgroundColor: 'none',
      borderColor: 'none',
      fontColor: '#212121',
      padding: 2,
      //textAlign: 'center',
      align: 'center',
      sticky: true,
      timeout: 30000,
      x: '30%',
      y: '80%',
      width: '36%'
    },

    plotarea: {
      marginTop: '15%',
      marginBottom:'55%',
      marginLeft: '8%',
      marginRight: '8%'
    }
  };

  var req = ajax('http://134.193.128.69:9090/BrandAnalyticsDemo/UserTweetDates','GET');
  req.onreadystatechange = function() {
    if (req.readyState == 4 && req.status == 200) {
      output = JSON.parse(req.responseText);
      Object.keys(output).forEach(function(key) {
        dataPoints.push([key,Number(output[key])])
      });
      zingchart.loadModules('calendar', function(){
        zingchart.render({
          id : 'UserTweetDates',
          data : myConfig,
          height: '100%',
          width: '100%'
        });
      });
    }
  };
}
