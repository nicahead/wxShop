// pages/test/index.js
var app = getApp();
var util = require("../../../utils/util.js");
let that;
Page({

  /**
   * 页面的初始数据
   */
  data: {
		id:'',
		address:{},
		region: ""
  },
	bindRegionChange(e) {
		let { value } = e.detail;
		// console.log("地区改变:", value);
		this.setData({
			region: value
		})
	},
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
		that = this
		util.okHttp('/api/getAddress', {
			'id': options.id
		}, function (state, data) {
			that.setData({
				id: options.id,
				address:data,
				region: [data.province,data.city,data.area]
			})
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
	formSubmit: function (e) {
		if (e.detail.value.receiver.length == 0 || e.detail.value.phone.length == 0
			|| that.data.region.length == 0 || e.detail.value.info.length == 0) {
			wx.showToast({
				title: '信息填写不完整',
				icon: 'loading',
				duration: 2000
			})
		} else {
			// console.log(e.detail.value)
			// console.log(that.data.userId)
			util.okHttp('/api/updateAddress', {
				'id':that.data.id,
				'receiver': e.detail.value.receiver,
				'tel': e.detail.value.phone,
				'province': that.data.region[0],
				'city': that.data.region[1],
				'area': that.data.region[2],
				'detail': e.detail.value.info
			}, function (state, data) {
				if (data != 0) {
					wx.navigateTo({
						url: '/pages/address/list/index'
					})
				} else {
					wx.showToast({
						title: '无法修改',
						duration: 2000
					})
				}
			});
		}
	}
})