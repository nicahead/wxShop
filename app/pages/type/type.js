var app = getApp();
var util = require("../../utils/util.js");

let that;

Page({

  /**
   * 页面的初始数据
   */
	data: {
		checkid: 0,   //记录当前选中类别
		typeList: [], //类别集合
		itemList: []  //商品集合
	},

  /**
   * 生命周期函数--监听页面加载
   */
	onLoad: function (options) {
		that = this;
	},
  /**
   * 加载所有的类别
   */
	loadType: function () {
		util.okHttp('/api/typels', {
			
		}, function (state, data) {	
			that.setData({
				typeList: data
			})
		});
	},
	loadItem: function (typeId) {
		util.okHttp('/api/getList', {
			'typeId': typeId
		}, function (state, data) {
			that.setData({
				itemList: data
			})
		});
	},
	gotoitem: function (e) {
		var itemId = e.currentTarget.dataset.id;
		wx.navigateTo({
			url: '/pages/item/item?itemId=' + itemId,
		})
	},
	onReady: function () {

	},
	onShow: function () {
		//加载类别
		that.loadType();
		//加载所有商品
		that.loadItem('0');
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
	//切换分类显示对应商品列表
	onCateChange: function (e) {
		that.setData({
			checkid: e.currentTarget.dataset.id
		});
		if (e.currentTarget.dataset.id == '0') {
			that.loadItem('0');
		}
		else {
			that.loadItem(e.currentTarget.dataset.id);
		}
	},
	gotoSearch: function () {
		wx.navigateTo({
			url: '/pages/search/search'
		})
	}
})
