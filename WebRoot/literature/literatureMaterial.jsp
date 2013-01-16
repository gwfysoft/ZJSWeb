<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/taglibs.jsp"%>
<%@ include file="/commons/jsp/common_head_for_jsp.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title>文献资料管理</title>
<style type="text/css">
</style>
</head>
<body>
	<section class="container" id="container">
		<div class="subnav">
			<ul class="nav nav-pills">
				<li class=""><a href="/index.jsp">首页</a></li>
				<li class="active"><a>文献资料管理</a></li>
			</ul>
		</div>
		 <div class="row" style="height:100%;min-height:600px;margin-top:20px;">
                    <div class="span12">
                    <ul id="tab" class="nav nav-tabs">
                            <li class="active">
                                <a href="#home">文献资料管理</a>
                            </li>
                    </ul>
                    </div>
                    <div class="span4">
                                                文献标题:<input type="text" style="width:300px;"/>                   
                    </div>
                    <div class="span2">
                                                文献类别:<select style="width:100px;">
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
                    </div>
                    <div class="span2">
                                                文献期数:<select style="width:100px;">
                             <option>2012-12</option>  
                             <option>2012-11</option>  
                             <option>2012-10</option>  
                             <option>2012-09</option> 
                             <option>2012-08</option> 
                             <option>2012-07</option>                      
                          </select>
                    </div>
                    <div class="span2">
                                                排序位置: <input type="text" style="width:100px;"/>
                    </div>
                    <div class="span4">
                                                            文献资料上传:
                        <input type="file" name="fileToUpload" id="fileToUpload"  multiple="multiple"  />
                    </div>
                    <div class="span11" >
                                                文献内容:<textarea autofocus="true" style="width:85%;height:380px;">
                            
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
