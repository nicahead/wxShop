// pages/test/index.js
var app = getApp();
var util = require("../../utils/util.js");
let that;

Page({

  /**
   * 页面的初始数据
   */
	data: {
		item:{},
		order:{},  //订单信息
		defaultAddress:'',
		addressData:[],
		addressStatus:false
	},

  /**
   * 生命周期函数--监听页面加载
   */
	onLoad: function (options) {
		that = this
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
		//读取购物车数据
		wx.getStorage({
			key: 'item',
			success: function (res) {
				that.setData({
					item: res.data
				})
			},
		});

		wx.getStorage({
			key: 'defaultAddress',
			success: function (res) {
				that.setData({
					defaultAddress: res.data,
					addressStatus: true
				});
				util.okHttp('/api/getAddress', {
					'id': res.data
				}, function (state, data) {
					console.log(data)
					that.setData({
						addressData: data
					})
				});
			},
		});
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

	//提交外卖单
	settlement: function (e) {
		util.okHttp('/api/addorder', {
			'userId': app.globalData.userId,
			'addressId': that.data.defaultAddress,
			'detailstr': JSON.stringify(that.data.item),
			'type': 'buyNow'
		}, function (state, data) {
			if (data != 0) {
				//清缓存
				wx.removeStorageSync('item');
				wx.navigateTo({
					url: '../topay/topay?type=list&orderls=' + JSON.stringify(data)
				})
			} else {
				wx.showToast({
					title: '无法下单',
					duration: 2000
				})
			}
		});
	},

	// 添加收货地址
	addAddress: function () {
		wx.navigateTo({
			url: '../address/add/index'
		})
	},
	selectAddress:function(){
		wx.navigateTo({
			url: '../address/select/index'
		})
	}
})