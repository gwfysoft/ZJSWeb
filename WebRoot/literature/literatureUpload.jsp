<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/taglibs.jsp"%>
<%@ include file="/commons/jsp/common_head_for_jsp.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title>文献上传管理</title>
<style type="text/css">
</style>
</head>
<body>
	<section class="container" id="container">
		<div class="subnav">
			<ul class="nav nav-pills">
				<li class=""><a href="/index.jsp">首页</a></li>
				<li class="active"><a>文献上传管理</a></li>
			</ul>
		</div>
		<div class="row" style="height:100%;min-height:600px;margin-top:20px;text-align: center">
                    <div class="span12">
                    <ul id="tab" class="nav nav-tabs">
                            <li class="active">
                                <a href="#home">文献上传管理</a>
                            </li>
                    </ul>
                    </div>
                    <div class="span12">
                                                文献期数:<select style="width:100px;">
                             <option>2012-12</option>  
                             <option>2012-11</option>  
                             <option>2012-10</option>  
                             <option>2012-09</option> 
                             <option>2012-08</option> 
                             <option>2012-07</option>                      
                          </select>
                                                    本期上传文献名称:<input type="text" style="width:100px;"/>                   
                    </div>
                    <div class="span12">
                                                            文献A上传:
                        <input type="file" name="fileToUpload" multiple="multiple"  />
                    </div>
                    <div class="span12">
                                                            文献B上传:
                        <input type="file" name="fileToUpload" multiple="multiple"  />
                    </div>
                    <div class="span12">
                                                            文献C上传:
                        <input type="file" name="fileToUpload" multiple="multiple"  />
                    </div>
                    <div class="span12">
                                                            文献D上传:
                        <input type="file" name="fileToUpload" multiple="multiple"  />
                    </div>
                    <div class="span12">
                                                            文献E上传:
                        <input type="file" name="fileToUpload" multiple="multiple"  />
                    </div>
                    <div class="span12">
                                                            文献F上传:
                        <input type="file" name="fileToUpload" multiple="multiple"  />
                    </div>
                    <div class="span12">
                                                            文献G上传:
                        <input type="file" name="fileToUpload" multiple="multiple"  />
                    </div>
                    <div class="span12">
                                                            文献H上传:
                        <input type="file" name="fileToUpload" multiple="multiple"  />
                    </div>
                    <div class="span12">
                                                            文献I上传:
                        <input type="file" name="fileToUpload" multiple="multiple"  />
                    </div>
                    <div class="span12">
                                                            文献J上传:
                        <input type="file" name="fileToUpload" multiple="multiple"  />
                    </div>
                    <div class="span12">
                                                            产品文献上传:
                        <input type="file" name="fileToUpload" multiple="multiple"  />
                    </div>
                    <div class="span12" style="text-align:center">
                      <a class="btn" href="#literatureTabmkb">确认添加</a>
                    </div>
             </div>
	</section>
	<%@ include file="/commons/jsp/common_js.jsp"%>
</body>
</html>
