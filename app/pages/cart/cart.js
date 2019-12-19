// pages/test/index.js
var app = getApp();
var util = require("../../utils/util.js");

let that;
Page({

  /**
   * 页面的初始数据
   */
  data: {
		cartList: [], //购物车集合
		goodsCount:0,
		count: 0, //数量
		amount: 0//总金额
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
		//读取购物车 
		const value = wx.getStorageSync('cart')
		if (value) {
			that.setData({
				cartList: value,
				goodsCount: value.length
			})
			//重新计算
			that.getTotal();
		}
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
	//点商品添加
	addCount: function (e) {
		let index = e.currentTarget.dataset.index;
		that.data.cartList[index].num++;
		//重置购物车列表
		that.setData({
			cartList: that.data.cartList
		});
		// console.log(that.data.cartList.length);
		//将购物车放入本地缓存
		wx.setStorage({
			key: 'cart',
			data: that.data.cartList
		});
		//更新总价
		that.getTotal();
	},
	delCount: function (e) {
		let index = e.currentTarget.dataset.index;
		that.data.cartList[index].num--;
		//商品数量减为0时，清除该商品
		if (that.data.cartList[index].num == 0) {
			that.data.cartList.splice(index, 1);
			that.setData({
				goodsCount: that.data.cartList.length
			})
		}
		//重置购物车列表
		that.setData({
			cartList: that.data.cartList
		});
		// console.log(that.data.cartList.length);
		//将购物车放入本地缓存
		wx.setStorage({
			key: 'cart',
			data: that.data.cartList
		});
		//更新总价
		that.getTotal();
	},

	//总价/总数计算
	getTotal: function () {
		let amount = 0;
		//遍例购物车商品，以商品单价*单商品总数相加取最终购物商品总价
		for (let i in that.data.cartList) {
			let price = parseFloat(that.data.cartList[i].price);
			amount += price * parseInt(that.data.cartList[i].num);
		}
		that.setData({
			amount: amount,
			count: that.data.cartList.length
		});
	},
	//下单
	addOrder: function () {
		if (that.data.cartList.length == 0) {
			//未选中商品时直接返回，不能下单
			util.commonWarnTip('购物车为空');
			return;
		}
		wx.navigateTo({
			url: '/pages/confirm/index'
		})
	}
})