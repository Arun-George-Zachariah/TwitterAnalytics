$(document).ready(function(){
  $( function() {
    $( "#accordion" ).accordion();
  } );

  getTweetCount();
  getGeoLocation();
  getMaxRetweeted();
  getUserTweetDates();
  getTotalUsers();
  getWeeklyData();
  getLangDiversity();
  getTweetSentiment();
  getSenstiveContentDist();
  getVerifiedUsers();
  getFriendsCount();
});
