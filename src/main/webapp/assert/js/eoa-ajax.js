/**
 * 重写jQuery的ajax方法,将一些通用的方法,配置放在此处统一书写。
 * @param $
 */
(function($) {
	// '严格模式'（strict mode）。顾名思义,这种模式使得Javascript在更严格的条件下运行。
	'use strict';
	// 备份jquery的ajax方法
	var _ajax = $.ajax;
	// 重写jquery的ajax方法
	$.ajax = function(opt) {
		// 备份opt中error,beforeSend,complete方法
		var fn = {
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			},
			beforeSend : function(XMLHttpRequest) {
			},
			complete : function(XMLHttpRequest, textStatus) {
			},
		};
		if (opt.error) {
			fn.error = opt.error;
		}
		if (opt.beforeSend) {
			fn.beforeSend = opt.beforeSend;
		}
		if (opt.complete) {
			fn.complete = opt.complete;
		}
		if (opt.type) {
			fn.type = opt.type;
		} else {
			opt.type = 'POST';// 默认ajax的提交方式为POST
		}
		if (opt.cache) {
			fn.cache = opt.cache;
		} else {
			opt.cache = 'false';// 默认true,不缓存
		}
		if (opt.dataType) {
			fn.dataType = opt.dataType;
		} else {
			opt.dataType = 'json';// 默认的数据格式为'json'
		}
		// 扩展增强处理
		var _opt = $.extend({
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				// 错误方法增强处理
				if (XMLHttpRequest.status == '500') {// 处理500错误
					//返回的是错误信息的页面直接展示即可。
					var responseText = XMLHttpRequest.responseText;
					if (responseText != null) {
						//$('.page-content').html(responseText);
						//Layout.initContent(); // init metronic core componets
					}
					
				} else if (XMLHttpRequest.status == '404') {// 处理404错误
					//XApp.initHTML('404');
				}
			},
			beforeSend : function(XMLHttpRequest) {
				// 请求之前方法增强处理 ,显示遮罩层
				 // update the block message
				$.blockUI({ message: '<img src="assert/images/loading.gif"/>请求加载中...' });
			
			},
			complete : function(XMLHttpRequest, textStatus) {
				// 请求结束方法增强处理 ,隐藏遮罩层
				// unblock when remote call returns
                $.unblockUI();
			},
			statusCode : {
				900 : function() {//自定义AJAX状态码(900:无权限)
					//XApp.initHTML('noauth');
				},
				800 :function(){//自定义AJAX状态码(800:重复提交请求)
					alert('请不要重复提交你的请求!');
				},
				700 :function(){//自定义AJAX状态码(700:未登录)
					window.location.href='login';
				}
			}
		}, opt);
		return _ajax(_opt);
	};
})(jQuery);