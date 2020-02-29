//在页面渲染成功后，使用layui的use方法按需加载需要用的各个模块。
layui.use([ 'table', 'form', 'util'  ], function() {
	// layui是基于jQuery的,启用$符号。
	var $ = layui.$;
	var form = layui.form;
	var table = layui.table;
	var util = layui.util;
	// 通过render方法开始渲染 talbe的数据
	table.render({
		elem : '#list_table', // 要绑定的页面元素
		url : 'car', // 数据接口 layui会自动封装成 role/{page}/{limit}
		where : $('#form_search').serialize(),// 模拟额外的参数#目前引入搜索表单的数据
		page : true,// 开启分页
		cols : [ [ // 表头
		{
			field : 'stallCode',
			title : '车位号',
			width : 180,
			sort : true,
			fixed : 'left'
		}, {
			field : 'carNum',
			title : '车牌号',
			width : 180
		}, {
			field : 'parkTime',
			title : '停车时间',
			templet : function(d) {
				return util.toDateString(d.parkTime);
			}
		}, {
			title : '操作',
			width : 200,
			templet : '#roleBtnTpl'
		} ] ]
	});
	
	   //注：tool 是工具条事件名，filter_table =<table lay-filter="filter_table">
	table.on('tool(filter_table)',function(obj){
		var $tr = $(obj.tr);//获得当前行 tr 的 DOM 对象（如果有的话）
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		//通过data将要修改的数据的主键 取出
		var rowId = data.rowId;
		switch (layEvent) {
		case 'delete':
			//让用户再进行一次确认
			layer.confirm('你确定取车吗？',function(index){
				//index = 弹出层的index，用于关闭的时候使用
				//点击弹出的确认会触发回调函数
				$.ajax({
					type:'post',
					url:'record/'+rowId,
					success:function(result){
						if(result){
							//请求table重新加载数据 list_table = <table id="list_table"/>
							table.reload('list_table');
							//尝试将弹出层再关闭一下。
							//layer.closeAll(); //疯狂模式，关闭所有层
							layer.close(index);
						}
					}
				});
			});
			break;
		default:
			break;
		}
	});

});