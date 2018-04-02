$(function(){
	$("#select").click(function(){
		$(".all_permissions option:selected").appendTo(".selected_permissions");
	});
	
	$("#selectAll").click(function(){
		$(".all_permissions option").appendTo(".selected_permissions");
	});
	
	$("#deselect").click(function(){
		$(".selected_permissions option:selected").appendTo(".all_permissions");
	});
	
	$("#deselectAll").click(function(){
		$(".selected_permissions option").appendTo(".all_permissions");
	});
	
	$("#editForm").submit(function(){
		if($(".selected_permissions option:selected").size()!=$(".selected_permissions option")){
			$(".selected_permissions option").prop("selected",true);
		}
	});
	
	if($(".selected_permissions option").size()>0){
		var arr=$.map($(".selected_permissions option"),function(option){
			return $(option).attr("value");
		});
		$(".all_permissions option").filter(function(index){
			return $.inArray($(this).attr("value"),arr)>=0;
		}).remove();
	}
});