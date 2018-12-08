var output;

var ajax = function(url, type) {
  output = "";
  var req = new XMLHttpRequest();
  req.open(type, url, true);
  req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  req.send("");
  return req;
};
