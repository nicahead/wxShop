var app = getApp();

function createOrder(openid, goodsList, addressData, meta, callback) {
  wx.request({
    url: app.globalData.api  + "/create_order.php",
    data: {
      openid: openid,
      apiKey: apiKey,
      goodsList: goodsList,
      addressData: addressData,
      meta: meta
    },
    method: "POST",
    header: {
      "content-Type": "application/x-www-form-urlencoded"
    },
    success: function (res) {
      if (200 == res.statusCode) {
        if (0 == res.data.errno) {
          wx.requestPayment({
            timeStamp: res.data.prepay.timeStamp,
            nonceStr: res.data.prepay.nonceStr,
            package: res.data.prepay.package,
            signType: "MD5",
            paySign: res.data.prepay.paySign,
            success: function (res2) {
              var rs = {
                data: {
                  errno: "0",
                  errmsg: "ok"
                }
              };
              callback(rs)
            },
            fail: function (res2) {
              if ("undefined" == typeof res2.err_code) {
                var rs = {
                  data: {
                    errno: 400061,
                    errmsg: "取消支付"
                  }
                }
              } else {
                var rs = {
                  data: {
                    errno: res2.err_code,
                    errmsg: res2.err_desc
                  }
                }
              }
              callback(rs)
            },
            complete: function (res2) {
              if ("requestPayment:cancel" == res2.errMsg) {
                var rs = {
                  data: {
                    errno: 400061,
                    errmsg: "取消支付"
                  }
                };
                callback(rs)
              }
            }
          })
        } else {
          callback(res)
        }
      }
    }
  })
}