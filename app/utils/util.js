var app = getApp();
var httpGet = function(url, callback) {
  http(url, callback);
}
var httpPost = function(url,data,callback) {
  http(url, callback, "POST",data);
}
var httpPut = function(url,data, callback) {
  http(url, callback, "PUT", data);
}
var httpDelete = function(url, callback) {
  http(url, callback, "DELETE");
}
var commonTip=function(content){
  wx.showToast({
    title: content,
    icon: 'none',
    duration: 3000
  });
}
var commonSuccTip = function (content) {
  wx.showToast({
    title: content,
    icon: 'success',
    image: '/images/successful.png',
    duration: 3000
  });
}
var commonWarnTip = function (content) {
  wx.showToast({
    title: content,
    icon: 'success',
    image: '/images/warn.png',
    duration: 3000
  });
}
var commonErrorTip = function (content) {
  wx.showToast({
    title: content,
    icon: 'success',
    image: '/images/error-1.png',
    duration: 3000
  });
}

var http = function(url, callback, method,data) {
  wx.showLoading({
    title: '加载中...',
  })
  wx.request({
    url: app.globalData.api + url,
    method: !method ? "GET" : method,
    dataType: "json",
    data: !data ? null : data,
    header: {
      'content-type': 'application/x-www-form-urlencoded'
    },
    success: function(response) {
      //console.log(response);
      wx.hideLoading();
      callback(response);
    },
    error: function (response){
      wx.hideLoading();
      wx.showToast({
        title: '请检查服务器连接状态',
        icon: 'loading',
        duration: 3000
      });
    }
  })
}

var httpJsonPost = function (url, data, callback) {
  httpJson(url, callback, "POST", data);
}

var httpJson = function (url, callback, method, data) {
  wx.request({
    url: app.globalData.api + url,
    method: !method ? "GET" : method,
    dataType: "json",
    data: !data ? null : data,
    header: {
      'content-type': 'application/json'
    },
    success: function (response) {
      console.log(response);
      callback(response);
    },
    error: function (response) {
      wx.showToast({
        title: '请检查服务器连接状态',
        icon: 'loading',
        duration: 3000
      });
    }
  })
}

var okHttp = function (url,args, done) {
  wx.request({
    method: 'POST',
    url: app.globalData.api +url,
    data: args,
    header: {
      'content-type': 'application/x-www-form-urlencoded'
    },
    success: function (res) {
      typeof done == "function" && done(true, res.data)
    },
    fail: function (res) {
      typeof done == "function" && done(false, '请求失败')
    }
  })
}

module.exports = {
  httpGet: httpGet,
  httpPost: httpPost,
  httpPut: httpPut,
  httpDelete: httpDelete,
  http: http,
  okHttp: okHttp,
  commonTip: commonTip,
  commonSuccTip: commonSuccTip,
  commonWarnTip: commonWarnTip,
  commonErrorTip: commonErrorTip,
  httpJsonPost: httpJsonPost,
  httpJsonPost: httpJsonPost
}