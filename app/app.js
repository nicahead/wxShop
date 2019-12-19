//app.js
const Towxml = require('/towxml/main');     //引入towxml库
App({
  onLaunch: function () {
	
  },
  globalData: {
		userId: '',
    userInfo: null,
		api:"https://nichang.site/",
		IMG_URL:'https://nichang.site/upload/',
    addressId:null,
		appID:'',
		appSecret:'',
		banner: [],
		notice:'',
		about:'',
		contact:''
  },
  orderStatus:{
    //1待付款 6已付款  3待收货 4已完成 （已收货） 9交易完成（评价完）
    notpay:1,
    payed:6,
    notreceive:3,
    received:4,
    complete:9
  },
  towxml: new Towxml()
})