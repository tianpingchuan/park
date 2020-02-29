$(document).ready(function(){
	$('#left_menu').on('click','a',function(){
		var href = $(this).attr('href');
		//alert(href);
		$.ajax({
			url:href,
			dataType:'html',
			success:function(htmldata){
				$('#container-admin').html(htmldata);
			},
			beforeSend:function(){//在发送请求之前调用
				 // update the block message
				$.blockUI({ message: '<img src="assert/images/loading.gif"/>请求加载中...' });
			},
			complete:function(){//当请求完成之后调用这个函数，无论成功或失败。
				// unblock when remote call returns
                $.unblockUI();
			}
		});
		
		return false;
	});
	
	$('#left_login').on('click','a',function(){
		var href = $(this).attr('href');
		//alert(href);
		$.ajax({
			url:href,
			dataType:'html',
			success:function(htmldata){
				$('#container-admin').html(htmldata);
			},
			beforeSend:function(){//在发送请求之前调用
				 // update the block message
				$.blockUI({ message: '<img src="assert/images/loading.gif"/>请求加载中...' });
			},
			complete:function(){//当请求完成之后调用这个函数，无论成功或失败。
				// unblock when remote call returns
                $.unblockUI();
			}
		});
		
		return false;
	});
	
});