<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>hivideo</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/video-js.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/video.min.js"></script>
	
</head>
<body>

   
        <video height="300" width="200" autoplay="autoplay" loop="loop" controls="controls">
            <source src="${pageContext.request.contextPath}/video/pgy.mp4" type="video/mp4">
        </video>
   
        <video  height="300" width="200" loop="loop" controls="controls" autoplay="true" isrotate="false" autoHide="true">
            <source src="http://vjs.zencdn.net/v/oceans.mp4" type="video/mp4">
        </video>
   
</body>
</html>