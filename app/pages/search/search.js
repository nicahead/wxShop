// pages/test/index.js
var app = getApp();
var util = require("../../utils/util.js");
let that;
Page({

  /**
   * 页面的初始数据
   */
  data: {
		itemList:[],
		key:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
		that = this;   
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
	getKey:function(e){
		that.setData({
			key:e.detail.value
		})
	},
	searchItem:function(){
		util.okHttp('/api/getList', {
			'itemName': that.data.key
		}, function (state, data) {
			that.setData({
				itemList: data
			})
		})
	},
	gotoitem: function (e) {
		var itemId = e.currentTarget.dataset.id;
		wx.navigateTo({
			url: '/pages/item/item?itemId=' + itemId,
		})
	}
})