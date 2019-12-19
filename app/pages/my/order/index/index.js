// pages/test/index.js
var app = getApp();
var util = require("../../../../utils/util.js");
let that;
Page({

  /**
   * 页面的初始数据
   */
  data: {
		activeIndex: 0,
		tabs:['待付款','待发货','待收货','待评价','已完成'],
		orderList:[],
		grade:5,
		sliderLeft:0,
		sliderOffset:0,
		content:''
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
		let state = 1;
		switch (that.data.activeIndex) {
			case '0':
				state = 1;
				break;
			case '1':
				state = 2;
				break;
			case '2':
				state = 3;
				break;
			case '3':
				state = 4;
				break;
			case '4':
				state = 5;
				break;
		};
		// console.log(status);
		util.okHttp('/api/orderls', {
			'userId': app.globalData.userId,
			'state':state
		}, function (state, data) {
			that.setData({
				orderList: data
			})
			console.log(that.data.orderList)
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
	tabClick: function (e) {
		// console.log(e);
		that.setData({
			activeIndex: e.currentTarget.id,
			sliderLeft: e.currentTarget.id*20
		});
		that.onShow()
	},
	goPay:function(e){
		// console.log(e.currentTarget.id)
		util.okHttp('/api/getOrderById', {
			'id': e.currentTarget.id
		}, function (state, data) {
			var order = JSON.stringify(data);
			wx.navigateTo({
				url: '../../../topay/topay?type=single&orderls=' + order
			})
		})
	},
	goDelete:function(e){
		var orderid = e.currentTarget.id;
		wx.showModal({
			title: '提示',
			content: '确定要删除吗？',
			success: function (sm) {
				if (sm.confirm) {
					util.okHttp('/api/getOrderById', {
						'id': e.currentTarget.id
					}, function (state, data) {
						var order = JSON.stringify(data);
						util.okHttp('/api/orderOp', {
							'orderstr': order,
							'state': 0,
							'type':'single'
						}, function (state, data) {
							wx.showToast({
								title: '已删除',
								duration: 2000
							});
							that.setData({
								activeIndex: '0'
							});
							that.onShow();
						})
					})
				} else if (sm.cancel) {
					
				}
			}
		})
	},
	changeGrade: function (e) {
		console.log(e.detail.value)
		that.setData({
			grade: e.detail.value
		})
	},
	bindInputContent:function(e){
		console.log(e.detail.value)
		that.setData({
			content: e.detail.value
		})
	},
	evaluate:function(e){
		util.okHttp('/api/evaluate', {
			'userId': app.globalData.userId,
			'orderId': e.currentTarget.id,
			'grade': that.data.grade,
			'content':that.data.content
		}, function (state, data) {
			util.okHttp('/api/getOrderById', {
				'id': e.currentTarget.id
			}, function (state, data) {
				var order = JSON.stringify(data);
				util.okHttp('/api/orderOp', {
					'orderstr': order,
					'state': 5,
					'type': 'single'
				}, function (state, data) {
					wx.showToast({
						title: '评价成功',
						duration: 4000
					});
					that.setData({
						activeIndex: '4',
						sliderLeft: 80
					});
					that.onShow();
				})
			})
		})
	},
	deliveryGood:function(e){
		util.okHttp('/api/getOrderById', {
			'id': e.currentTarget.id
		}, function (state, data) {
			var order = JSON.stringify(data);
			util.okHttp('/api/orderOp', {
				'orderstr': order,
				'state': 4,
				'type': 'single'
			}, function (state, data) {
				wx.showToast({
					title: '已确认',
					duration: 4000
				});
				that.setData({
					activeIndex: '3',
					sliderLeft: 60
				});
				that.onShow();
			})
		})
	}
})