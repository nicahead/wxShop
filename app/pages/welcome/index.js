var app = getApp();
var util = require("../../utils/util.js");
var self;
let that;

//获取全局APP对象
Page({
  /**
   * 页面的初始数据
   */
  data: {
    title: "欢迎进入八方汇学子小铺!"
  },
	onLoad: function () {
		that = this;
		util.okHttp('/admin/sys/get', {
		}, function (state, data) {
			// console.log(data)
			app.globalData.appID = data.appID,
			app.globalData.appSecret = data.appSecret,
			app.globalData.banner = [data.pic1, data.pic2, data.pic3],
			app.globalData.notice = data.notice,
			app.globalData.about = data.about,
			app.globalData.contact = data.contact
		});

		//如果能获取用户信息，说明已经完成授权（登录），不再显示引导页，直接进入系统首页
		// if (wx.getStorageSync("userInfo")) {
		// 	wx.switchTab({
		// 		url: '/pages/index/index'
		// 	})
		// }
		wx.login({
			success: function (res) {
				// console.log(res.code)
				that.getOpenId(res.code)
			}
		})
	},
	getOpenId: function (code) {
		// console.log(code);
		// console.log(app.globalData.appID);
		// console.log(app.globalData.appSecret);
		wx.request({
			url: "https://api.weixin.qq.com/sns/jscode2session?appid=" + app.globalData.appID + "&secret=" + app.globalData.appSecret + "&js_code="+ code +"&grant_type=authorization_code",
			data: {},
			method: 'GET',
			success: function (res) {
				console.log(res)
				app.globalData.userId = res.data.openid;
				// console.log(res.data.openid)
			},
			fail: function () {
				// fail
			},
			complete: function () {
				// complete
			}
		})
	},
  bindGetUserInfo: function(e) {
    //同意授权后设置用户信息全局变量，并写入缓存
    if (e.detail.errMsg == 'getUserInfo:ok') {
      app.globalData.userInfo = e.detail.userInfo;
      wx.setStorage({
        key: 'userInfo',
        data: e.detail.userInfo
      })
    };
		console.log(e.detail.userInfo)
		util.okHttp('/api/adduser', {
			"id": app.globalData.userId,
			'nickname': e.detail.userInfo.nickName,
			'avatar': e.detail.userInfo.avatarUrl
		}, function (state, data) {
			//跳转首页
			wx.switchTab({
				url: '/pages/index/index',
			})
		});
  }
})