// pages/test/index.js
var app = getApp();
var util = require("../../../utils/util.js");
let that;
Page({

  /**
   * 页面的初始数据
   */
  data: {
		userId: '',
		addressList: [],
		defaultAddress: '0'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
		that = this;
		const value = wx.getStorageSync('defaultAddress')
		if (value) {
			that.setData({
				defaultAddress: value
			})
		}
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
		wx.getStorage({
			key: 'userId',
			success: function (res) {
				util.okHttp('/api', {
					'method': 'getAddressls',
					'userId': res.data
				}, function (state, data) {
					that.setData({
						addressList: data
					})
				})
			},
		})
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
	toAdd: function () {
		wx.navigateTo({
			url: '/pages/address/add/index'
		})
	},
	defaultAddress: function (e) {
		wx.setStorage({
			key: 'defaultAddress',
			data: e.currentTarget.dataset.id
		}),
			that.setData({
				defaultAddress: e.currentTarget.dataset.id
			})
		// console.log(e.currentTarget.dataset.id)
	}
})