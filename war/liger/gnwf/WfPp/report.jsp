<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String projects=(String)request.getAttribute("wfPpReports");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>金立CRM</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link href="./include/js/codebase/report01.css" rel="stylesheet" type="text/css" />
	<!-- <link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />  --> 
  
    <script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
	   <script src="./include/liger/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	  

	
	<script src="./include/js/jquery-1.8.1.min.js"></script>
    
	<script >
	  var urlPath='<%=request.getContextPath()%>';
	  $(function () {
	   var projects=eval(<%=projects%>);
	    if(projects&&projects.data&&projects.data.length>0){
	       var datas= projects.data;
	        var arr=[];
	        var arrName=[];
	        var detia=[];
	        var StaDate;
	        for(var i=0,n=datas.length,k=0;i<n;i++){
	          if(i==0){
	            StaDate=datas[i].stadatey;
	            arrName.push(StaDate);
	            detia.push({name:datas[i]});
	            arr.push(detia);
	          }else{
	            if(StaDate==datas[i].stadatey){
	              detia.push({name:datas[i]});
	            }else{
	              StaDate=datas[i].stadatey;
	              arrName.push(StaDate);
	              detia=new  Array();
	              detia.push({name:datas[i]});
	              arr.push(detia);
	            }
	          }
	        } 
	        //对左边的树查行数据添加 
	        if(arr.length>0){
	          var tab='';
	           for(var i=0;i<arr.length;i++){
	               var  detia=arr[i];
	               tab=tab+"<li class='treeLayout01 ";
	               //处理第一个展开
	               if(i==0)tab=tab+" open01 "
	               tab=tab+"'><a class='ico01 childrenPrent01'"+
	               "href='javascript:void(0)'>"+arrName[i]+"<em>("+detia.length+"个)</em></a>";
	               tab=tab+"<ul class='treeBox02'>";
	               for(var j=0;j<detia.length;j++){
	                 tab=tab+"<li><a id='onclik'";
	                 if(j==0&&i==0){
	                   tab=tab+" class='hov' ";
	                   initPro(detia[j].name.prjtno);
	                    initPro(detia[j].name.prjtnm);
	                  }
	                // alert(detia[j]);
	                  tab=tab+" val="+detia[j].name.prjtno+" >"+detia[j].name.prjtnm+"</a></li>";
	               }
	               tab=tab+" </ul></li>";
	           }
	           $("#proajdbS").append($(tab));
	        }else{
	         }
	    }
	    


	   
	    //$(this).attrs("val")
	    $("#onclik").live("click",function(){
             var ProNo=$(this).attr("val");
             var ProNm=$(this).attr("val");
             if(ProNm){
                initPro(ProNo);
                initPro(ProNm);
                $("li .hov").removeClass("hov");
                $(this).addClass("hov");
             }else{
               alert("获取编码失败");
             }
         });
	    function w1(){
			var w = $(window).width()-213;
			$('.conRight').width(w);
		};w1();
		$(window).resize(w1);
		$('.childrenPrent01').live("click",function(){
		    var b = $(this).parent().hasClass('open01');
			if(b){
			    $(this).parent().removeClass('open01');
			}else{
			    $(this).parent().addClass('open01');
			};
		});
		
		$('td .ico01').live("click",function(){
		    var b = $(this).hasClass('open02');
			if(b){
			    var ths = $(this);
			    ths.removeClass('open02');
				var b2 = ths.parent().parent().next(),
				    l = b2.has('.ico01').length;
				while(l == 0){
						 b2 = b2.hide().next();
				    l = b2.has('.ico01').length;
					if(b2.html() == undefined){l=1;};
				};
			}else{
			    var ths = $(this);
			    ths.addClass('open02');
				var b2 = ths.parent().parent().next(),
				    l = b2.has('.ico01').length;
				while(l == 0){
						 b2 = b2.show().next();
				    l = b2.has('.ico01').length;
					if(b2.html() == undefined){l=1;};
				};
			}
		});
	   });
	   //查询项目
	   function initPro(ProNo){
		  
	  /*    if(!ProNo)
	     {
	      alert("找不1111到项目");return;
	     } */
	     $.ajax({
				type: "GET",
				url: urlPath + "/wfppReport!findWfPpRepByPrjtNo.shtml?prjtNo="+ProNo,
				data: '',
				dataType:'json',
				success	: function(msg){
				  if(msg&&msg.data){
				     var datas=msg.data;
				     var project =datas.project[0];
				     //openSumQuesReport(project)
				     setProjectTitDetail(project)
				     var projectFengXian =datas.projectFengXian[0];
				     setfengXian(projectFengXian);
				     jindumx(datas.jdMx)
				     jingduFun(datas.jingdu);
				   }
				}
		});
	   }
	   //对风险统计添加数据
	
	   
	   function setfengXian(fengXian){
	      if(fengXian){
	        if(fengXian.zuofenxian){
	          $("#zuofenxian").text(fengXian.zuofenxian);
	        }else{
			   $("#zuofenxian").text("0");
			}
	        if(fengXian.sumwfques){
	          $("#sumWfQues").text(fengXian.sumwfques);
	        }else{
			   $("#sumWfQues").text("0");
			}
	        if(fengXian.gubifenxian){
	          $("#gubifenxian").text(fengXian.gubifenxian);
	        }else{
			 $("#gubifenxian").text("0");
			}
	        if(fengXian.fendxian){
	          $("#fendXian").text(fengXian.fendxian);
	        }else{
			 $("#fendXian").text("0");
			}
	        if(fengXian.weiguanbi){
	          $("#weiguanbi").text(fengXian.weiguanbi);
	        }else{
			 $("#weiguanbi").text("0");
			}
	        if(fengXian.weiguanfenxian){
	          $("#weiguanfenxian").text(fengXian.weiguanfenxian);
	        }else{
			  $("#weiguanfenxian").text("0");
			 }
	        if(fengXian.guanbi){
	          $("#guanbi").text(fengXian.guanbi);
	        }else{
			  $("#guanbi").text("0");
			}
	      }else{
		     $("#guanbi").text("0");
			 $("#zuofenxian").text("0");
			 $("#weiguanfenxian").text("0");
			 $("#fendXian").text("0");
			 $("#gubifenxian").text("0");
			 $("#weiguanbi").text("0");
			 $("#sumWfQues").text("0");
		  }            
	   }
	   //项目进度总览
	   function setProjectTitDetail(project){
	     if(project&&project.prjtnm){
	       $("#projectTitDetai2").text(project.prjtnm+"   项目进度总览  ");
	       $("#projectTitDetai21").text(project.prjtno);
	       if(project.remdbyuser){
	          $("#projectTitDetai3").text("取消收藏");
	           $("#projectTitDetai3").attr("shou",'1');
	       }else{
	          $("#projectTitDetai3").text("收藏");
	          $("#projectTitDetai3").attr("shou",'0'); 
	       }
	       $("#projectTitDetai3").attr("val",project.prjtno); 
	     }else{
	       $("#projectTitDetai2").text("找不到项目");
	     }
	   }
	   //收藏
	   $("#projectTitDetai3").live("click",function(){
	      var text=$(this).attr("shou");
	      var val=$(this).attr("val");
	      if(!val){
	        alert("找不到收藏的项目！");
	        return;
	      }
	      if(text==1){
	         shouAction(val,'quxian','取消收藏失败','收藏','0');
	      }else{
	         shouAction(val,'add','收藏失败','已收藏','1');
	      }
	   });
	   var NamMap={"预研":"yuyan","预立项":"yulixian","开发":"kaifa","试产":"shichange","量产":"lingchang"};
	   var imgPath={no1:"<%=request.getContextPath()%>/include/js/codebase/imgs/step03.png",no2:"<%=request.getContextPath()%>/include/js/codebase/imgs/step02.png",no3:"<%=request.getContextPath()%>/include/js/codebase/imgs/step01.png"};
	    //对对总进度添加数据
	   function jingduFun(jingdu){
	      if(jingdu.length){
	       	$("#zhongjingdu").empty();;
	        for(var i=0;i<jingdu.length;i++){
	        //tatus/状态  0：未配置  1：未启动  2：进行中  3：已结束;
	         var plahDay=jingdu[i].planstadate;
	         var tab="<div class='processSection'><img id='gaiNian' src='<%=request.getContextPath()%>/include/js/codebase/imgs/";
	           if(jingdu[i].status==2){
	            tab=tab+"step02.png";
	           }else{
	              if(jingdu[i].status==3){
	               tab=tab+"step01.png";
	              }else{
	                tab=tab+"step03.png";
	              }
	           }
	         tab=tab+"' /><div class='PSTit'>"+jingdu[i].tasknm+"<br />"
	         tab=tab+((jingdu[i].planstadate||'')+"～"+(jingdu[i].planovedate||''));
	         tab=tab+" </div></div>";
	        $("#zhongjingdu").append(tab);
	   
	      }
	     }else{
	      	$("#zhongjingdu").empty();
	     }
	   }
	
		
	    //根据WfNo获取该任务的进度详细框
	   function openWfRd(element){
		   var  wfNo = $(element).prev().val();
		   //alert(project.prjtno);
		  //wfNo = "B3114300001";
		  //alert(wfNo);
			//alert($(element).prev().val());
			if(wfNo != ' '){
	      var url = "./WfRdView!wfRdView.shtml?wfRd.wfNo=" + wfNo + "&isfromPrjtDef=1";
	      window.open("./WfRdView!wfRdView.shtml?wfRd.wfNo=" + wfNo + "&isfromPrjtDef=1"); 
		   /* $.ligerDialog.open(
		{ height: 400, url: url, width: 1000, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title:wfNo, slide: false}); 
			 */
			 }else{
				alert("该任务未开启！");
			}
	    
	    }  
	   //打开对于问题"明细报表"
		  function openSumQuesReport(){
		   		//弹出新页面
			  window.open("./WfQues!wfQuesManager.shtml?PrjtNo="+$("span.ic0o02").html());
			  /*弹出窗口
			  $.ligerDialog.open(
		      	   { height: 400, url: url, width: 1000, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: title, slide: false
		      });*/  
		  }
	   
		//打开对于风险的"明细报表"
		  function openSumRickReport(){
		   		//alert($("span.ic0o02").html());
		   		
			 // window.open("./WfQues!wfQuesManager.shtml?PrjtNo="+$("span.ic0o02").html());
			 
		  }
	   //对进度明细表格添加数据
	   function jindumx(jindumx){
		  $("#jdmx").empty();;
	     if(jindumx){
	       var k=0;
	       var tab="";
	      
	       
	       for(var i=0;i<jindumx.length;i++){
	         if(jindumx[i].parent==-1){
	           tasknmNo=jindumx[i].tasknm;
	           
	           tab=tab+"<tr id='showAndHide' val='"+tasknmNo+"' ><td colspan='8' class='txL'><a  class='ico01 open02' >"+jindumx[i].tasknm+"</a></td></tr>";
	           k=0;
	         }else{
	          k++;
	          tab=tab+"<tr id='"+tasknmNo+"'><td class='p40 txL'><input type='hidden' id='wfNo' value='"+jindumx[i].wfno+"'/><a onmouseover=this.style.cursor='pointer' onclick='openWfRd(this)'>"+k+"."+jindumx[i].tasknm+"</a></td><td>";
	          if(jindumx[i].status==2){
	        	
	          tab=tab+"<font color='blue'>进行中</font>"
	          ;}
	          else if(jindumx[i].status==3){
	        	  
	             tab=tab+"<font color='green'>已结束</font>";
	          }else{
	        	
	             tab=tab+"未启动";
	          }
	          
	           tab=tab+"</td><td>"+(jindumx[i].planduration||'')+"</td> <td>";
	           tab=tab+(jindumx[i].planstadate||'')+"～ "+(jindumx[i].planovedate||'')+"</td><td>";
	           tab=tab+(jindumx[i].stadate||'')+"～ "+(jindumx[i].ovedate||'')+"</td><td>";
	           
	         if(jindumx[i].yanqi&&jindumx[i].yanqi>0){
	           tab=tab+"<font color='red'>是</font></td>";
	         }else{
	           tab=tab+"<font color='green'>否</font></td>";
	         }
	         tab=tab+"<td>"+jindumx[i].wenti+"</td><td>"+jindumx[i].fengxian+"</td>";
	        }
	      }
	      $("#jdmx").append(tab);
	     }        
	   }
	   //控制明细隐藏
	  $("#showAndHide").live("click",function(){
	    /*var tNo=$(this).attr("val");
	      var b=$("#"+tNo+" td").hasClass('open02');
	      alert(b);
	      if(b)$("#"+tNo+" td").removeClass('open02');
	      else
	       $("#"+tNo+" td").addClass('open02');
	      $("#"+tNo).toggle();
	      */
	  });
	  //对收藏操作
	   function shouAction(ProNo,type,faMsg,wenzhi,shouVal){
	     $.ajax({
				type: "GET",
				url: urlPath + "/wfppReport!shouChang.shtml?prjtNo="+ProNo+"&actionType="+type,
				data: '',
				dataType:'json',
				success	: function(msg){
				  if(msg&&msg.data){
				     $("#projectTitDetai3").text(wenzhi);
				     $("#projectTitDetai3").attr("shou",shouVal);
				     if(shouVal==0){
				       alert("取消收藏成功！");
				     }else{
				       alert("收藏成功！");
				     }
				   }else{
				     alert(faMsg);
				   }
				}
		});
	   }
	 
	  
	</script >
  </head>
  
  <body>

 <div  >
    <div class="conLeft" style="width:auto ">
        <ul id='proajdbS' class="treeBox01"  >
        </ul>
   </div>
   <div class="conRight" style="width:auto ">
        <div class="tit" id='projectTitDetail' >
        	
            <h2 class="ico01" ><span id='projectTitDetai2' class="ic0o01" style="font-weight:bold;"></span> 
             <span id='projectTitDetai21' style=display:none; class="ic0o02" ></span>
            </h2>
            
          <!--   <p  style=display:none; id='projectTitDetai21' class="ic0o02 ></p> -->
            <a class="but" onmouseover=this.style.cursor='pointer' ><span id='projectTitDetai3' val='' shou=''>收藏</span></a>
       <!-- <span  style=display:none; id='projectTitDetai21' class="ic0o02 ></span>  -->
        </div>
        <div class="conLef" style="width:auto ">
            <table width="100%">
                <tbody>
                    <tr>
                        <th class="bigTit" style="padding:10px 0; width:72px;table-layout:fixed;">总进度</th>
                        
                        <td>
                        
                          <div class="processPicture" id='zhongjingdu'>
                          </di>
                        </td>
                    </tr>
                </tbody>
            </table>
            
            <table class="outsideLine" width="100%" style="margin:20px 0;">
                <tbody>
                    <tr>
                        <th class="bigTit" style="padding:10px 0; width:72px;table-layout:fixed;border-right:#ccc solid 1px;">问题</th>
                        <td style="width:90px;table-layout:fixed;">问题共<a><span id='sumWfQues'>0</span></a>个</td>
                        <td style="width:90px;table-layout:fixed;">已关闭<a><span id='guanbi'>0</span></a>个</td>
                        <td style="width:90px;table-layout:fixed;">未关闭<a ><span id='weiguanbi'>0</span></a>个 </td>
                        <td style="width:90px;table-layout:fixed;">走风险<a ><span id='zuofenxian'>0</span></a>个</td>
                        <td colspan="2"><a class="but" type="button" id = 'sumReport' onmouseover=this.style.cursor='pointer' onclick="openSumQuesReport()">明细报表</a></td>
                     	<!--    <input type="button" value="按钮" onmouseover="this.style.cursor='hand'">-->
						<!-- './WfQues!quesManager.shtml?PrjtNo='+ProNo -->
                    </tr>
                    
        
                    <tr>
                        <th class="bigTit" style="padding:10px 0; width:72px;table-layout:fixed;border-right:#ccc solid 1px;">风险</th>
                        <td>风险共<a ><span id='fendXian'>0</span></a>个</td>
                        <td>已决策<a><span id='gubifenxian'>0</span></a>个</td>
                        <td>未决策<a><span id='weiguanfenxian'>0</span></a>个 </td>
                        
                       
                       <td colspan="2"><a class="but" type="button" id = 'sumReport' onmouseover=this.style.cursor='pointer' onclick="openSumRickReport()">明细报表</a></td>
                    </tr>
                </tbody>
            </table>
            <table width="100%" class="tdTxC">
              <thead>
                  <tr><th colspan="8" class="bigTit" style="text-align:left;">进度统计</th></tr>
                  <tr class="tit03">
                      <th>任务名称</th>
                      <th>状态</th>
                      <th>工期</th>
                      <th>计划日期</th>
                      <th>实际日期</th>
                      <th>是否延迟</th>
                      <th>问题<span class="ftSz">(已关闭/未关闭)</span></th>
                      <th>风险<span class="ftSz">（已决策/未决策）</span></th>
                  </tr>
              </thead>
              <tbody id="jdmx">
                  
              </tbody>
            </table>
           

           
        </div>
    </div>

</div>
  </body>
</html>
