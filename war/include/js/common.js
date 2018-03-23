
//选择公司列出部门
function changeCompany(dept,comValue){
	$.post("Dept!selectDept.shtml",
			{'dept.parent':comValue},
			function(data) {
	            $(dept).ligerGetComboBoxManager().setData(data);
			},
			"json"
	);
}

//选择部门列出用户
/**
function changeDept(user,deptValue){
	$.post("Usr!selectUser.shtml",
			{'usr.deptId':deptValue},
			function(data) {
	            $(user).ligerGetComboBoxManager().setData(data);
			},
			"json"
	);
}
**/

//选择部门列出用户
function changeDept(user,deptValue){
	var dept= document.getElementById(deptValue).value;
	$.post("Usr!selectUser.shtml",
			{'usr.deptId':dept},
			function(data) {
	            $(user).ligerGetComboBoxManager().setData(data);
			},
			"json"
	);
}




//选择公司列出部门(不使用liger控件)
function setChgDept(from,to){
	var param = document.getElementById(from).value;
	var thisEle = document.getElementById(to);
	var deptid = thisEle.value;
	var $thisEle = $(thisEle);
	
	jQuery.ajax({
	       type:"post",  
	       url:"Dept!selectDept.shtml?dept.parent="+param,  
	       dataType:"json",
	       success: function fun1(jsonData) {  
	   			$thisEle.empty();
	   			alert(jsonData);
				diguiDepts(jsonData,$thisEle,deptid);
	       }
	});  
}

//选择部门列出用户(不使用liger控件)
function setChgUser(from,to){
	var param = document.getElementById(from).value;
	var thisEle=document.getElementById(to);
    var $thisEle=$(thisEle);
    
	$.post("Usr!selectUser.shtml?usr.deptId="+param,  
			function fun2(data) {  
				var jsonData = eval(data);	//POST方法必加，ajax方法会自动处理 
				$thisEle.empty();
				$.each(jsonData,function(i,item){
					$thisEle.append("<option value='"+item.id+"'>" + item.usrName+"</option>");  
				});
		    },
		    "json"
	);  
}

	function diguiDepts(jsonData,thisEle,value){
		   var str="";
			$.each(jsonData,function(i,item){
				str="";
				switch (item.leve-2) {
				case 0:
				str="";
					break;
				case 1:
				str="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					break;
				case 2:
				str="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					break;
				case 3:
				str="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					break;
				case 4:
				str="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					break;
				case 5:
				str="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					break;
				case 6:
				str="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					break;
				case 7:
				str="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					break;
				default:
					break;
				}
		      if(value==item.deptId){
					thisEle.append('<option value="'+item.deptId+'"   selected>' +str+ item.deptNm + '</option>');  
				}else{
					thisEle.append('<option value="'+item.deptId+'">' +str+ item.deptNm + '</option>');  
				}
				if(item.subDepts!=null){
					diguiDepts(item.subDepts,thisEle,value);
				}
			});   
	}

/**
function diguiDepts(jsonData,thisEle,value){
	   var str="";
		$.each(jsonData,function(i,item){
			str="";
			switch (item.leve-2) {
			case 0:
			str="";
				break;
			case 1:
			str="&nbsp;&nbsp;&nbsp;&nbsp;";
				break;
			case 2:
			str="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				break;
			case 3:
			str="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				break;
			case 4:
			str="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				break;
			case 5:
			str="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				break;
			case 6:
			str="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				break;
			case 7:
			str="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				break;
			default:
				break;
			}
	      if(value==item.deptId){
				thisEle.append('<option value="'+item.deptId+'"   selected>' +str+ item.deptNm + '</option>');  
			}else{
				thisEle.append('<option value="'+item.deptId+'">' +str+ item.deptNm + '</option>');  
			}
			if(item.subDepts!=null){
				diguiDepts(item.subDepts,thisEle,value);
			}
		});   
}
**/

//选择列出部门(不使用liger控件)
function chgbook(from,to){
	var param = document.getElementById(from).value;
	var thisEle = document.getElementById(to);
	var deptid = thisEle.value;
	var $thisEle = $(thisEle);
	
	jQuery.ajax({
	       type:"post",  
	       url:"AddrBook!selectAddrBook.shtml?addrBook.deptId="+param,
	       dataType:"json",
	       success: function fun1(jsonData) {
	   			$thisEle.empty();
	   			$.each(jsonData,function(i,item){
					$thisEle.append("<option value='"+item.mailAddr+"'>" +item.addrName + "&lt;"+item.mailAddr+"&gt;</option>");  
				});
	       }
	});  
}


