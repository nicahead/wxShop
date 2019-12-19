// pages/test/index.js
var app = getApp();
var util = require("../../utils/util.js");
let that;
Page({

  /**
   * 页面的初始数据
   */
  data: {
		orderls:[],
		type:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
	onLoad: function (options) {
		that = this;
		that.setData({
			type: options.type,
			orderls: options.orderls
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
	pay:function(){
		util.okHttp('/api/orderOp', {
			'orderstr': that.data.orderls,
			'state':2,
			'type': that.data.type
		}, function (state, data) {
			wx.showToast({
				title: '支付成功',
				duration: 2000
			});
			setTimeout(function () {
				wx.switchTab({
					url: '/pages/index/index'
				})
			}, 2000)
		});
	}
})