$(document).ready(function(){
  $( function() {
    $( "#accordion" ).accordion();
  } );

  getTweetCount();
  getGeoLocation();
  getMaxRetweeted();
  getUserTweetDates();
  getVerifiedUsers();
  getWeeklyData();
  getLangDiversity();
  getSenstiveContentDist();
  getTotalUsers();
});
