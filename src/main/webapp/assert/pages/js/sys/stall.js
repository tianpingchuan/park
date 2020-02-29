//在页面渲染成功后，使用layui的use方法按需加载需要用的各个模块。
layui.use([ 'table', 'form' ], function() {
	// layui是基于jQuery的,启用$符号。
	var $ = layui.$;
	var form = layui.form;
	var table = layui.table;
	// 通过render方法开始渲染 talbe的数据
	table.render({
		elem : '#list_table', // 要绑定的页面元素
		url : 'stallclient', // 数据接口 layui会自动封装成  role/{page}/{limit}
		where : $('#form_search').serialize(),// 模拟额外的参数#目前引入搜索表单的数据
		page : true,// 开启分页
		cols: [[ //表头
		      {field: 'stallCode', title: '车位号', width:180, sort: true, fixed: 'left'}
		      ,{field: 'stallState', title: '车位状态',templet:'#stallTpl'}
		      ,{title: '操作',templet:'#BtnTpl'}
		    ]]
	});
	
	//注：tool 是工具条事件名，filter_table =<table lay-filter="filter_table">
	table.on('tool(filter_table)',function(obj){
		var $tr = $(obj.tr);//获得当前行 tr 的 DOM 对象（如果有的话）
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		//通过data将要修改的数据的主键 取出
		var rowId = data.rowId;
		switch (layEvent) {
		case 'edit':
			//尝试取出修改时需要进行回调的方法名称,不是所有的页面都有。
			var callback4Edit = $tr.find('a[lay-event="edit"]').data('callback');
			//打开通用的layer弹层
			var url = $('#hideURL').val()+'/form';
			var title = '停车';
			//调用通用的弹出层的方法，成功后会回调done方法
			openBaseLayer(url,title).done(function(){
				//给表单赋值 form_add_edit = <form lay-filter="form_add_edit">
				form.val("form_add_edit",data);
				// 让form表单渲染一下。 form_add_edit = <form lay-filter="form_add_edit">
				form.render(null, 'form_add_edit');
				
				//判断如果 修改form页面弹出后，需要回调的方法名称不为空。
				if(callback4Edit){
					//尝试调用一下额外配置的为修改使用的回调函数 
					eval(callback4Edit);
				}
			});
			break;
		default:
			break;
		}
	});
	
	form.on('submit(car_submit)', function(data) {
		//data.field //当前容器的全部表单字段，名值对形式：{name: value}
		//默认为新增
		var type = 'post';
		$.ajax({
			type:type,
			url:'car',
			data:$(data.form).serialize(),
			success:function(result){
				if(result){
					// 关闭弹出层
					//layer.close(layer.index);
					layer.msg("停车成功");
					layer.closeAll(); //疯狂模式，关闭所有层
					//请求table重新加载数据 list_table = <table id="list_table"/>
					table.reload('list_table');
				}
			}
		});
		
		//不用忘记 return false ,讲按钮原来的功能给屏蔽掉。
		return false;
	});
});