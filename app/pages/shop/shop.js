// pages/shop/shop.js
var app = getApp();
var util = require("../../utils/util.js");
let that;

Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		shop:{},
		itemList:[]
	},

	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad(options) {
		that = this;
		that.getShopDetail(options.shopId);
		that.getItemList(options.shopId);
	},

	getShopDetail(shopId) {
		util.okHttp('/api/getShop', {
			shopId: shopId
		}, function (state, data) {
			console.log(data)
			that.setData({
				shop: data
			})
		});
	},
	getItemList(shopId) {
		util.okHttp('/api/getList', {
			shopId: shopId
		}, function (state, data) {
			console.log(data)
			that.setData({
				itemList: data
			})
		});
	},

	gotodetail: function (e) {
		var id = e.currentTarget.dataset.id;
		wx.navigateTo({
			url: '/pages/item/item?itemId=' + id,
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

	}
})