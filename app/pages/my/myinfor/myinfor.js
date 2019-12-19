// pages/test/index.js
var app = getApp();
var util = require("../../../utils/util.js");
let that;
Page({

  /**
   * 页面的初始数据
   */
  data: {
		userId:'',
		region: "",
		userInfo:{},
		birthday: '',
		username:'',
		sex: ''
  },
	bindRegionChange(e) {
		let { value } = e.detail;
		// console.log("地区改变:", value);
		this.setData({
			region: value
		})
	},
	bindDateChange(e){
		let { value } = e.detail;
		// console.log("地区改变:", value);
		this.setData({
			birthday:value
		})
	},
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
		that = this,
		wx.getStorage({
		key: 'userInfo',
			success: function (res) {
				console.log(res.data)
				that.setData({
					userInfo: res.data
				})
			},
		}),
			wx.getStorage({
			key: 'userId',
				success: function (res) {
					util.okHttp('/api', {
						'method': 'getUser',
						'userId': res.data
					}, function (state, data) {
						if (data != 0) {
							if (data.ADDRESS!=null){
								that.setData({
									region: data.ADDRESS.split(","),
									birthday: "2019-01-09",
									username: data.USERNAME,
									sex: data.SEX
								})
							}
						} else {
						}
					});
					// console.log(res.data)
					that.setData({
						userId: res.data
					})
				},
			})
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
		
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
	formSubmit:function(e){
		util.okHttp('/api', {
			'method': 'updateUser',
			'userId': that.data.userId,
			'username': e.detail.value.username,
			'sex': e.detail.value.sex,
			'address': that.data.region,
			'birthday': e.detail.value.birthday
		}, function (state, data) {
			if (data != 0) {
				wx.showToast({
					title: "保存成功",
					duration: 2000
				});
				setTimeout(function () {
					wx.switchTab({
						url: '/pages/my/my'
					})
				}, 2000)
			} else {
				wx.showToast({
					title: '保存失败',
					duration: 2000
				})
			}
		});
	}
})