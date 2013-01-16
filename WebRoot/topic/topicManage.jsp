<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/taglibs.jsp"%>
<%@ include file="/commons/jsp/common_head_for_jsp.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title>题目管理</title>
<style type="text/css">
</style>
</head>
<body>
	<section class="container" id="container">
		<div class="subnav">
			<ul class="nav nav-pills">
				<li class=""><a href="/index.jsp">首页//</a></li>
				<li class="active"><a>题目管理</a></li>
			</ul>
		</div>
		 <div class="row" style="height:100%;min-height:600px;margin-top:20px;text-align: center;">
                    <div class="span12">
                    <ul id="tab" class="nav nav-tabs">
                            <li class="active">
                                <a href="#home">题目管理</a>
                            </li>
                    </ul>
                    </div>
                    <div class="span12">
                                                题目所属文献类别 : <select style="width:100px;">
                             <option>A</option>  
                             <option>B</option>  
                             <option>C</option>  
                             <option>D</option> 
                             <option>E</option> 
                             <option>F</option>   
                             <option>G</option> 
                             <option>H</option> 
                             <option>I</option> 
                             <option>J</option> 
                             <option>弥可保</option> 
                             <option>奥力宝</option>  
                             <option>快如妥</option>
                             <option>产品文献答案</option>                    
                          </select>
                                                                    题目类型 : <select style="width:100px;">
                             <option>单选题</option>
                             <option>问答题</option>                    
                          </select>        
                            题目总分 : <input type="text" style="width:100px;"/>
                                                                    关键字总分 : <input type="text" style="width:100px;"/>                                     
                    </div>
                    
                    <div class="span12" >
                                                问题内容 : <textarea autofocus="true" style="width:50%;height:160px;">  
                        </textarea>
                    </div>
                     <div class="span12" >
                                                问题答案 : <textarea autofocus="true" style="width:50%;height:160px;">  
                        </textarea>
                    </div>
                    <div class="span12" style="text-align:center">
                      <a class="btn" href="#literatureTabmkb">确认添加</a>
                    </div>
            </div>
	</section>
	<%@ include file="/commons/jsp/common_js.jsp"%>
</body>
</html>
