// pages/test/index.js
var app = getApp();
var util = require("../../../utils/util.js");
let that;
Page({

  /**
   * 页面的初始数据
   */
  data: {
		addressList:[],
		defaultAddress:''
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
		util.okHttp('/api/getAddressls', {
			'userId': app.globalData.userId
		}, function (state, data) {
			that.setData({
				addressList: data
			})
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
	editAddress:function(e){
		// console.log(e.target.dataset.id)
		wx.navigateTo({
			url: '/pages/address/edit/index?id='+e.target.dataset.id
		})
	},
	deleteAddress: function (e) {
		wx.showModal({
			title: '提示',
			content: '确定要删除吗？',
			success: function (sm) {
				if (sm.confirm) {
					util.okHttp('/api/delAddress', {
						'id': e.target.dataset.id
					}, function (state, data) {
						that.onShow()
					})
				} else if (sm.cancel) {
					
				}
			}
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