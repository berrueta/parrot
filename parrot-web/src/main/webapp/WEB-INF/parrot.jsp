<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="custom-functions.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> 
<head> 
<title>Parrot, a RIF and OWL documentation service</title> 
<meta http-equiv="Content-Type" content="application/xhtml+xml; charset=UTF-8" /> 
<meta name="description" content="parrot" /> 
<meta name="keywords" content="parrot, documentation, tool, rif, rdf" /> 
<link rel="shortcut icon" type="image/png" href="images/favicon.ico"  />
<!-- standalone -->
<!-- <link rel="stylesheet" type="text/css" media="screen,projection" href="standalone/ajax.googleapis.com/ajax/libs/jqueryui/1.8.11/themes/cupertino/jquery-ui.css" /> -->
<link rel="stylesheet" type="text/css" media="screen,projection" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.10/themes/cupertino/jquery-ui.css" /> 
<link rel="stylesheet" type="text/css" href="css/style.css"	media="screen,projection,print" /> 
</head> 

<body>
	<div class="all"> 

	<jsp:include page="header.jsp" />


		<c:if test="${ ! empty errorsGeneral}" >    	
    	<div class="inputData" id="inputDataReport">
			<h2 class="error">Errors</h2>
			<div class="inputDataReport">
		    	<ul class="error">
					<c:forEach var="line" items="${errorsGeneral}">
		         		<li class="error"><span><c:out value="${line}"/></span></li>
		      		</c:forEach>
				</ul>
			</div>
		</div>
		</c:if>

		<div class="inputData"> 
		<div id="tabs"> 
		    <ul> 
		        <li><a href="#tabs-1">by URL</a></li> 
		        <li><a href="#tabs-2">by direct input</a></li> 
		        <li><a href="#tabs-3">by file upload</a></li>
		        <li><a href="#tabs-4">by existing report</a></li>  
		    </ul>            
		        <div id="tabs-1"> 
		        	<h2>by URL</h2> 
					<form method="get" action="">
		        	<c:choose>
    					<c:when test='${not empty paramValues.documentUri}'>
				        	<%-- For every String[] item of paramValues... --%>
		      				<c:forEach var='uri' items='${paramValues.documentUri}' varStatus='uriStatus'> 
				        	<p class="uri-input<c:if test="${fn:contains(errorsUri, uri)}"> error</c:if>">
								<label <c:if test='${uriStatus.first}'>for="documentUri"</c:if> title="URL of the page containing the OWL/RIF document" class="uri">URL: </label>
				        		<input <c:if test='${uriStatus.first}'>id="documentUri"</c:if> name="documentUri" value="<c:out value="${uri}" escapeXml="true"/>" type="text" size="100" />
							<select name="mimetype">
								<option value="default" <c:if test="${paramValues.mimetype[uriStatus.index] eq 'default'}">selected="selected"</c:if>>Allow content negotiation</option>
								<jsp:include page="mimetypes.jsp">
									<jsp:param name="mimetypeSimple" value="false"/>
									<jsp:param name="mimetypeObtained" value="${paramValues.mimetype[uriStatus.index]}" />
								</jsp:include>
							</select>							 
							<span class="removeURI">remove</span>
							<c:if test='${uriStatus.first}'>
								<%-- Only for the first input --%>
								<span class="uriHint">(for example: <tt id="example">http://ontorule-project.eu/resources/steel-30.owl</tt>)</span>
							</c:if>
							<c:if test="${fn:contains(errorsUri,uri)}">
								<span class="error">${fn:getMessage(errorsUri, uri)}</span>
							</c:if>
							</p>
		 			        </c:forEach>
					    </c:when>
					    <c:otherwise>
				        	<p class="uri-input"><label title="URL of the page containing the OWL/RIF document"
							for="documentUri" class="uri">URL: </label><input id="documentUri" name="documentUri" value="http://" type="text" size="100" />
							<select name="mimetype">
								<option value="default" selected="selected">Allow content negotiation</option>
								<jsp:include page="mimetypes.jsp">
									<jsp:param name="mimetypeSimple" value="true"/>  
								</jsp:include> 
							</select>
							<span class="removeURI">remove</span>
							<span class="uriHint">(for example: <tt id="example">http://ontorule-project.eu/resources/steel-30.owl</tt>)</span>
							</p>
						</c:otherwise>
					</c:choose>

					<p id="addURI"><span id="addURILink">add another URL</span></p>
					<jsp:include page="more-options.jsp"/>
					<div class="buttons">
						<button type="submit" class="generate">Generate documentation</button>
					</div>
					
					</form>
		       	</div>
			
		        <div id="tabs-2"> 
		        	<h2>by direct input</h2> 
					<form method="post" action="?">
					
		        	<c:choose>
    					
    					<c:when test='${not empty paramValues.documentText}'>
				        	<%-- For every String[] item of paramValues... --%>
		      				<c:forEach var='text' items='${paramValues.documentText}' varStatus='textStatus'>
		      					<div class="direct-input"><label title="Text of the document" class="text">Enter your document:</label><br />
				        		<textarea name="documentText" cols="150" rows="15" style="width:80%"><c:out value="${text}" escapeXml="true"/></textarea>
		      				
								<p><span class="direct-input">This is an : 
								<select name="mimetypeText">
									<jsp:include page="mimetypes.jsp">
										<jsp:param name="mimetypeSimple" value="false"/>
										<jsp:param name="mimetypeObtained" value="${paramValues.mimetypeText[textStatus.index]}" />
									</jsp:include>
								</select>
						    	</span>							 
						    	<span class="removeText">remove</span>
							</p>
							</div>
		 			        </c:forEach>
					    </c:when>
					    
					    <c:otherwise>
				        	<div class="direct-input"><label title="Text of the document" class="text">Enter your document:</label><br />
				        	<textarea name="documentText" cols="150" rows="15" style="width:80%"><c:if test='${not empty param.documentText}'><c:out value="${param.documentText}" escapeXml="true"/></c:if></textarea>
				        	
							<p><span class="direct-input">This is an : 
							  <select name="mimetypeText">
								<jsp:include page="mimetypes.jsp">
									<jsp:param name="mimetypeSimple" value="true"/>
								</jsp:include> 
						      </select>
						    </span>
						    <span class="removeText">remove</span></p>
						    </div>
						</c:otherwise>
						
					</c:choose>

				    <p id="addText"><span id="addTextLink">add another text</span></p>
				    <jsp:include page="more-options.jsp"/>
					<div class="buttons">
						<button type="submit" class="generate">Generate documentation</button>
					</div>

					</form>
		        </div> 

		        <div id="tabs-3"> 
		        	<h2>by file upload</h2> 
					<form method="post" action="?" enctype="multipart/form-data">
						<p>
						<label title="File to be documented">File: </label><input type="file" name="datafile" size="40"/>
						This file is a: 
						<select name="mimetypeFile">
							<option value="default" selected="selected">Autodetect (if possible)</option>
							<jsp:include page="mimetypes.jsp">
								<jsp:param name="mimetypeSimple" value="true"/>  
							</jsp:include> 
						</select>
					    </p>
					    <p><button id="addFile">add another file</button></p>
					    <jsp:include page="more-options.jsp"/>
						<div class="buttons">
							<button type="submit" class="generate">Generate documentation</button>
						</div>
					    
					</form>
				</div> <!--  /tab3 -->
				
				<div id="tabs-4"> 
		        	<h2>by existing report</h2> 
					<form method="get" action="">
					<p><label for="reportURL" title="URL of an existing Parrot report" class="uri">URL: </label>
					<c:choose>  
						<c:when test='${empty param.reportURL}'>
							<input id="reportURL" name="reportURL" value="http://" type="text" size="100" />
						</c:when>
						<c:otherwise>
							<input id="reportURL" name="reportURL" value="<c:out value="${param.reportURL}" />" type="text" size="100" />
						</c:otherwise>
					</c:choose>
					<span class="uriHint">(for example: <tt id="example-report">http://ontorule-project.eu/resources/parrot/examples/previous-report-metadata.html</tt>)</span>
					</p>
					
					<jsp:include page="more-options.jsp"/>
					<div class="buttons">
						<button type="submit" class="generate">Generate documentation</button>
					</div>

					</form>
				</div> <!-- tab4 -->


		</div> 
		    <div id="tipOfTheDay">
		    <span >Did you know ...</span>
		    </div>
		    <div id="video"><span ><a href="http://www.youtube.com/watch?v=uN2XQA6TVRc">Watch a video demo of Parrot on YouTube</a></span></div>
		    <p><img src="images/help.png" alt="help" class="help" />Do you need <a href="help">help</a>?</p>
	</div>    
    
    <jsp:include page="footer.jsp" />
    
    </div> 

	<!-- Feedback -->
	<img src="images/feedback.png" width="30" height="100" alt="feedback" id="feedback" /> 
	<div id="feedback-dialog" title="Give us some feedback">
		<p><img src="images/bug.png" alt="bug"></img> <a href="https://bitbucket.org/fundacionctic/parrot/issues/">Report a bug </a></p>
		<p><img src="images/heart.png" alt="request"></img> <a href="https://bitbucket.org/fundacionctic/parrot/issues/">Request a feature</a></p>
		<p><img src="images/contact.png" alt="contact"></img> <a href="https://bitbucket.org/fundacionctic/parrot/wiki/Home#!contact">Contact us</a></p>
	</div>
	
	<!-- Generating -->
	<div id="generating-dialog" title="Thank you for using Parrot">
		<p><img src="images/loading-spinner.gif" alt=""></img></p>
		<p>Parrot is generating the report</p> 
	</div> 
	
<!-- standalone -->
<!-- <script type="text/javascript" src="standalone/ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script> -->
<!-- <script type="text/javascript" src="standalone/ajax.googleapis.com/ajax/libs/jqueryui/1.8.11/jquery-ui.min.js"></script> -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.11/jquery-ui.min.js"></script>

<script type="text/javascript" src="javascript/jquery.randomContent.js"></script>
<script type="text/javascript" src="javascript/feedback.jQuery.js"></script>
<script type="text/javascript" src="report/js/jquery.corner.js"></script>
<script type="text/javascript" src="javascript/scripts.js"></script>

<c:if test='${not empty paramValues.documentText}'>
<script type="text/javascript">//<![CDATA[ 
jQuery(document).ready(function(){
	$("#tabs").tabs("select", 1);
});
//]]></script>
</c:if>

<c:if test='${not empty param.reportURL}'>
<script type="text/javascript">//<![CDATA[ 
jQuery(document).ready(function(){
	$("#tabs").tabs("select", 3);
});
//]]></script>
</c:if>

<jsp:include page="google-analytics.jsp" />

</body> 

</html> 