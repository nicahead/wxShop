var app = getApp();
var self;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //当前用户信息
    userInfo:null,
  },
  editUser: function (options){
      wx.navigateTo({
        url: '/pages/my/myinfor/myinfor',
      })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    self = this;
    //从缓存中取当前用户信息
    self.setData({userInfo:wx.getStorageSync("userInfo")});
  }
})