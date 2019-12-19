var util = require("../../../utils/util.js");
var that;

Page({
  /**
   * 页面的初始数据
   */
  data: {
    orderId:'',//订单id
    detail:null
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    that=this;
    that.setData({ orderId: options.id})
    util.httpPost('/api/order/getById',
      { id: that.data.orderId }, function (res) {
        if (res.data.code == 0) {
          //成功
          that.setData({ detail: res.data.data })
        } else {
          util.commonErrorTip('订单信息加载失败');
        }
      });
  },
  toAddOrder:function(){
    wx.switchTab({
      url:'/pages/site/site'
    });
  },
  toMyOrder:function(e){
    wx.redirectTo({
      url: '/pages/my/order/index/index'
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