var util = require('../../../../utils/util.js');

let that;
Page({
  /**
   * 页面的初始数据
   */
  data: {
    time: '获取验证码',
    currentTime: 60,
    disabled: false,
    mobile:'',
    tip:'',
    modalHidden:true,
    pass:'',
    repass:'',
    code:'',
    user:{
      userId:''
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    that=this;
    var uid = wx.getStorageSync('uid') != '' ? wx.getStorageSync('uid') : 1;
    that.setData({
      'user.userId': uid
    });
  },
  bindKeyInputCode: function (e) {
    this.setData({
      'code': e.detail.value
    });
  },
  bindKeyInputPass: function (e) {
    this.setData({
      'pass': e.detail.value
    });
  },
  bindKeyInputRepass: function (e) {
    this.setData({
      'repass': e.detail.value
    });
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },
  submitPass: function () {
    that=this;
    if (that.data.code == null || that.data.code==''){
       util.commonTip('请输入验证码');
    } else if (that.data.pass == null || that.data.pass == ''){
       util.commonTip('请输入密码');
    } else if (that.data.repass == null || that.data.repass == '') {
      util.commonTip('请输入确认密码');
    } else if (that.data.pass != that.data.repass) {
      util.commonTip('两次输入密码不一致');
    }else{
      util.httpPost('/api/dinner/editUser',{
        id:that.data.user.userId,
        paypass: that.data.pass,
        mobile: that.data.mobile,
        code: that.data.code
      },function(result){
        if (result.data.code==0){
           util.commonTip('支付密码修改成功');
          setTimeout(function () {
            //要延时执行的代码
            wx.navigateBack({
              delta: 1
            })
          }, 3000) //延迟时间 这里是1秒
         }
      });
    }
  },
  getCode: function (options) {
    that = this;
    var currentTime = that.data.currentTime;
    console.log('====' + that.data.mobile);
    if (that.data.mobile == null || that.data.mobile==''){
      util.commonTip('请输入手机号码');
      return;
    }
    //获取手机验证码
    util.httpPost('/api/dinner/getMesCode', {
      mobile: that.data.mobile
    }, function (result) {
    });
    that.setData({
      time: currentTime + '秒',
      disabled:true
    })
    var interval = setInterval(function () {
      that.setData({
        time: (currentTime - 1) + '秒'
      })
      currentTime--;
      if (currentTime <= 0) {
        clearInterval(interval)
        that.setData({
          time: '重新获取',
          currentTime: 60,
          disabled: false
        })
      }
    }, 1000)
  },
  inputMobile:function(e){
    that=this;
    that.setData({
      'mobile': e.detail.value
    });
  }
})