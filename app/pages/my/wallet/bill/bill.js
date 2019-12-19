var app = getApp();
var util = require("../../../../utils/util.js");
var that;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //清单
    billList: [],
    searchPageNum: 1,   // 设置加载的第几次，默认是第
    pageSize:10,
    searchLoading: false, //"加载更多"的变量，默认false，隐藏
    searchLoadingComplete: false,  //“没有数据”的变量，默认false，隐藏
    isFromSearch:true
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  onShow: function () {
    that = this;
    that.fetchSearchList();
  },
  fetchSearchList: function () {
    let that = this;
    let searchPageNum = that.data.searchPageNum;//把第几次加载次数作为参数
    var uid = wx.getStorageSync('uid') != '' ? wx.getStorageSync('uid') : 1;
    //访问网络
    util.httpPost("/api/bizbill/listPost", {
      page: searchPageNum,
      pageSize: that.data.pageSize,
      userid: uid,
      shopid: app.globalData.shopId
    }, function (result) {
      //判断是否有数据，有则取数据
      if (result.data.list.length != 0 && result.data.page < result.data.totalpage) {
        let searchList = [];
        //如果isFromSearch是true从data中取出数据，否则先从原来的数据继续添加
        that.data.isFromSearch ? searchList = result.data.list : searchList = that.data.billList.concat(result.data.list)
        that.setData({
          billList: searchList, //获取数据数组
          searchLoading: true,   //把"上拉加载"的变量设为false，显示
          searchLoadingComplete: false, //把“没有数据”设为true，显示
        });
        //没有数据了，把“没有数据”显示，把“上拉加载”隐藏
      } else if (result.data.list.length != 0 && result.data.page == result.data.totalpage) {
        let searchList = [];
        //如果isFromSearch是true从data中取出数据，否则先从原来的数据继续添加
        that.data.isFromSearch ? searchList = result.data.list : searchList = that.data.billList.concat(result.data.list)
        that.setData({
          billList: searchList, //获取数据数组
          searchLoading: false,   //把"上拉加载"的变量设为false，显示
          searchLoadingComplete: true, //把“没有数据”设为true，显示
        });
        console.log("::" + that.data.billList);
      } else {
        that.setData({
          searchLoadingComplete: true, //把“没有数据”设为true，显示
          searchLoading: false  //把"上拉加载"的变量设为false，隐藏
        });
      }
    });
  },
  //滚动到底部触发事件
  searchScrollLower: function () {
    let that = this;
    if (that.data.searchLoading && !that.data.searchLoadingComplete) {
      that.setData({
        searchPageNum: that.data.searchPageNum + 1,  //每次触发上拉事件，把searchPageNum+1
        isFromSearch: false  //触发到上拉事件，把isFromSearch设为为false
      });
      that.onShow();
    }
  },
  onPullDownRefresh: function () {
    var that = this;
    wx.showNavigationBarLoading() //在标题栏中显示加载
    that.setData({
      searchPageNum: 1,   //第一次加载，设置1
      billList: [],  //放置返回数据的数组,设为空
      isFromSearch: true,  //第一次加载，设置true
      searchLoading: false,  //把"上拉加载"的变量设为true，显示
      searchLoadingComplete: false //把“没有数据”设为false，隐藏
    });
    that.onShow();
    wx.hideNavigationBarLoading() //完成停止加载
    wx.stopPullDownRefresh() //停止下拉刷新
  }
})