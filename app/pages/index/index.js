var app = getApp();
var util = require("../../utils/util.js");
let that;
Page({
	data: {
		indicatorDots: true, // 是否显示面板指示点
		autoplay: true, // 是否自动切换
		interval: 3000, // 自动切换时间间隔
		duration: 800, // 滑动动画时长
		indicatorColor: '#fff',
		banner:[],
		newList: []
	},
	onLoad: function () {
		console.log(app.globalData)
		that = this;
		that.setData({
			banner: app.globalData.banner
		});
		that.loadNewList();
	},
	loadNewList: function () {
		util.okHttp('/api/getNew', {
		}, function (state, data) {
			console.log(data)
			that.setData({
				newList: data
			})
		});
	},
	gotodetail: function (e) {
		var id = e.currentTarget.dataset.id;
		wx.navigateTo({
			url: '/pages/item/item?itemId=' + id,
		})
	}
});