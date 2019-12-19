var app = getApp();
var util = require("../../utils/util.js");
var wxParse = require("../../wxParse/wxParse.js");
let that;

Page({

  /**
   * 页面的初始数据
   */
	data: {
		nav_select: false, // 快捷导航
		floorstatus: false, // 返回顶部
		item: {}, // 商品详情信息
		detail: {},
		goods_num: 1, // 商品数量
		cartList: [], // 购物车集合
		cart_total_num: 0, // 购物车商品总数量
		goodsComment:[]
	},


  /**
   * 生命周期函数--监听页面加载
   */
	onLoad(options) {
		that = this;
		// 获取商品信息
		that.getGoodsDetail(options.itemId);
		that.getEvaluatels(options.itemId);
	},

	onShow: function () {
		//读取购物车 
		const value = wx.getStorageSync('cart')
		if (value) {
			that.setData({
				cartList: value,
				cart_total_num: value.length
			})
		}
	},

  /**
   * 获取商品信息
   */
	getGoodsDetail(itemId) {
		util.okHttp('/api/itemDetail', {
			id: itemId
		}, function (state, data) {
			that.setData({
				item: data,
				detail: wxParse.wxParse('detail', 'html', data.detail, that, 0)
			})
		});
	},

	getEvaluatels(itemId) {
		util.okHttp('/api/getEvaluatels', {
			itemId: itemId
		}, function (state, data) {
			that.setData({
				goodsComment: data,
			})
		});
	},


  /**
   * 返回顶部
   */
	goTop(t) {
		this.setData({
			scrollTop: 0
		});
	},

  /**
   * 显示/隐藏 返回顶部按钮
   */
	scroll(e) {
		this.setData({
			floorstatus: e.detail.scrollTop > 200
		})
	},

  /**
   * 增加商品数量
   */
	up() {
		this.setData({
			goods_num: ++this.data.goods_num
		})
	},

  /**
   * 减少商品数量
   */
	down() {
		if (this.data.goods_num > 1) {
			this.setData({
				goods_num: --this.data.goods_num
			});
		}
	},

  /**
   * 跳转购物车页面
   */
	flowCart: function () {
		wx.switchTab({
			url: '/pages/cart/cart'
		})
	},

	gotoShop: function (e) {
		var shopId = e.currentTarget.dataset.id;
		wx.navigateTo({
			url: '/pages/shop/shop?shopId=' + shopId,
		})
	},

  /**
   * 加入购物车and立即购买
   */
	submit(e) {
		that.data.item.num = that.data.goods_num;
		let submitType = e.currentTarget.dataset.type;
		if (submitType === 'buyNow') {
			wx.setStorage({
				key: 'item',
				data: that.data.item
			});
			// 立即购买
			wx.navigateTo({
				url: '/pages/buy/index'
			})
		} else if (submitType === 'addCart') {
			// 加入购物车
			that.setCart(that.data.item)
			wx.showToast({
				title: '添加成功',
				duration: 2000
			});
		}	
	},

	//向购物车添加或减少商品
	setCart: function (goods) {
		//用于判断新添加的商品是否已在购物车中存在的标识
		let exists = false;
		for (let i in that.data.cartList) {
			//如果已经在购物车中，更新数量
			if (goods.id == that.data.cartList[i].id) {
				exists = true;
				that.data.cartList[i].num = that.data.cartList[i].num + goods.num;
				break;
			}
		}
		//如果购物车中不存在本商品并且是添加操作时，将当前商品加入购物车
		if (!exists) {
			that.data.cartList.push(JSON.parse(JSON.stringify(goods)));
		}
		//重置购物车列表
		that.setData({
			cartList: that.data.cartList,
			cart_total_num: that.data.cartList.length
		});
		// console.log(that.data.cartList);
		//将购物车放入本地缓存
		wx.setStorage({
			key: 'cart',
			data: that.data.cartList
		})
	}

})	