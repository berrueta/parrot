<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> 
<head>
  <title>Examples of Parrot, RIF and OWL documentation service</title>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <link rel="shortcut icon" href="images/favicon.ico" type="image/png" />
  <meta name="description" content="parrot" />
  <meta name="keywords" content="parrot, documentation, tool, rif, rdf" />
  <!-- standalone --> 
  <!-- <link rel="stylesheet" type="text/css" href="standalone/yui.yahooapis.com/2.8.0r4/build/fonts/fonts-min.css" /> -->  
  <link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/fonts/fonts-min.css" />
  <link type="text/css" rel="stylesheet" href="css/style.css" media="screen,projection,print" />
</head>

<body>

<div class="all">

<div id="header">
<h1><a href="/parrot"
title="go to parrot home page">Parrot </a></h1>

<h2>a RIF and OWL documentation service</h2>
</div>

<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${fn:replace(req.requestURL, fn:substring(req.requestURI, 0, fn:length(req.requestURI)), req.contextPath)}" />

<h2>Parrot pages</h2> 
<ul> 
<li><a href="parrot">Parrot homepage</a></li> 
<li><a href="help">Parrot help</a></li>
<li><a href="examples">Parrot examples</a></li> 
</ul>

<h2>Ontologies</h2> 
<ul> 
<li><a href="parrot?documentUri=http://ontorule-project.eu/resources/steel-30.owl&mimetype=default&profile=business">Steel ontology [external resource] (Business profile)</a></li> 
<li><a href="parrot?documentUri=http://ontorule-project.eu/resources/steel-30.owl&mimetype=default&profile=technical">Steel ontology [external resource] (Technical profile)</a></li> 
<li><a href="parrot?documentUri=foaf&mimetype=default&showform=true">FOAF ontology report (from prefix.cc) [external resource] (show form)</a></li> 
<li><a href="parrot?documentUri=${baseURL}/examples/anonymous.owl&profile=technical">Anonymous ontological elements (RDF/XML)</a></li> 

</ul>

<h2>Rules</h2>
<ul>
<li><a href="parrot?documentUri=${baseURL}/examples/under18.rifps&profile=business&showform=true">Under 18 years old rule (RIF PS) (show form)</a></li> 
<li><a href="parrot?documentUri=${baseURL}/examples/under18.rif&profile=business&showform=true">Under 18 years old rule (RIF XML) (show form)</a></li> 
<li><a href="parrot?documentUri=${baseURL}/examples/import-rif-rules.rifps&mimetype=default">Car Rules (RIF PS) (with import directive (RIF) - under 18 rules)</a></li> 
<li><a href="parrot?documentUri=${baseURL}/examples/anonymous.rif&profile=technical">Anonymous rule and rule set (RIF XML)</a></li> 
<li><a href="parrot?documentUri=${baseURL}/examples/anonymous.rifps&profile=technical">Anonymous rule and rule set (RIF PS)</a></li> 
</ul>

<h2>Ontologies &amp; Rules</h2> 
<ul> 
<li><a href="parrot?documentUri=${baseURL}/examples/under18.rifps&documentUri=http://xmlns.com/foaf/spec/index.rdfs&profile=business&showform=true">Under 18 years old rule (RIF PS) + FOAF (show form)</a></li> 
<li><a href="parrot?documentUri=${baseURL}/examples/under18.rif&documentUri=http://xmlns.com/foaf/spec/index.rdfs&profile=business&showform=true">Under 18 years old rule (RIF XML) + FOAF (show form)</a></li> 
<li><a href="parrot?documentUri=${baseURL}/examples/under18-foaf.rifps&profile=business&showform=true">Under 18 years old rule (RIF PS) (with import directive (OWL) (show form)</a></li> 
<li><a href="parrot?documentUri=${baseURL}/examples/under18-foaf.rif&profile=business&showform=true">Under 18 years old rule (RIF XML) (with import directive (OWL) (show form)</a></li> 
<li><a href="parrot?documentUri=${baseURL}/examples/import-owl-ontology.rifps&mimetype=default&showform=true">Car Rules (RIF PS) (with import directive (OWL) - Terminae Audi) (show form)</a></li> 
<li><a href="parrot?documentUri=http://ontorule-project.eu/resources/2nd-review-b/review_rules.rifps&mimetype=default&documentUri=http://ontorule-project.eu/resources/2nd-review-b/review_skos.rdf+&mimetype=default&documentUri=http://ontorule-project.eu/resources/2nd-review-b/review_telix.rdf&mimetype=default&documentUri=http://ontorule-project.eu/resources/2nd-review-b/review_proto-rules.rdf&mimetype=default&documentUri=http://ontorule-project.eu/resources/2nd-review-b/review_input-1.xhtml&mimetype=default&showform=true">Full example report (Ontologies + Rules + Annotations) (related documentation) [external resource] (show form)</a></li>  
</ul>

<h2>Vocabularies</h2> 
<ul> 
<li><a href="parrot?documentUri=http%3A%2F%2Flabs.mondeca.com%2Fdataset%2Flov%2Frdf%2Fvocabulary_gr.rdf&mimetype=default&profile=business&language=en&customizeCssUrl=&showform=true">Good Relations Vocabulary [external resource] (show form)</a></li> 
<li><a href="parrot?documentUri=http%3A%2F%2Flabs.mondeca.com%2Fdataset%2Flov%2Flov.rdf&mimetype=default&profile=business&language=en&customizeCssUrl=&showform=true">Linked Open Vocabularies (LOV). All vocabularies [external resource] (show form)</a></li> 
</ul>

<h2>Datasets</h2> 
<ul> 
<li><a href="parrot?documentUri=http%3A%2F%2Fec.europa.eu%2Feurostat%2Framon%2Frdfdata%2Fvoid.rdf&mimetype=default&profile=business&language=en&customizeCssUrl=&showform=true">Eurostat's Metadata Server RAMON [external resource] (show form)</a></li> 
<li><a href="parrot?documentUri=http%3A%2F%2Frisp.asturias.es%2Fcatalogo%2Fcultura-turismo%2Fturismo.rdf&mimetype=default&documentUri=http%3A%2F%2Frisp.asturias.es%2Fcatalogo%2Fsi%2Finversiones.rdf&mimetype=default&documentUri=http%3A%2F%2Frisp.asturias.es%2Fcatalogo%2Fsi%2Festadisticas.rdf&mimetype=default&documentUri=http%3A%2F%2Frisp.asturias.es%2Fcatalogo%2Fempleo%2Foferta-formativa.rdf&mimetype=default&documentUri=http%3A%2F%2Frisp.asturias.es%2Fcatalogo%2Fcomun%2Fgobierno.rdf&mimetype=default&documentUri=http%3A%2F%2Frisp.asturias.es%2Fcatalogo%2Fcomun%2Flocalizacion.rdf&mimetype=default&profile=business&language=en&customizeCssUrl=&showform=true
">Datasets del Principado de Asturias (show form)</a></li> 
<li><a href="parrot?documentUri=${baseURL}/examples/dcat.rdf&mimetype=default&showform=true">Synthetic DCAT example (show form)</a></li> 
</ul> 

<h2>Generation from an existing report</h2> 
<ul>
<li><a href="parrot?reportURL=${baseURL}/examples/previous-report.html&showform=true">Report generated from an existing report of steel ontology (show form)</a></li> 
<li><a href="parrot?reportURL=${baseURL}/examples/previous-report-metadata.html&showform=true">Report generated from an existing report of steel ontology (show form) (just metadata)</a></li> 
</ul>

<h2>Internal resources</h2> 
<ul> 
<li><a href="examples/anonymous.owl">Anonymous ontological elements (RDF/XML)</a></li>
<li><a href="examples/anonymous.rif">Anonymous rule and rule set (RIF XML)</a></li>
<li><a href="examples/anonymous.rifps">Anonymous rule and rule set (RIF PS)</a></li>
<li><a href="examples/import-owl-ontology.rifps">Rules (RIF PS) (with import directive (OWL))</a>
<li><a href="examples/import-rif-rules.rifps">Rules (RIF PS) (with import directive (RIF XML))</a>
<li><a href="examples/under18.rif">Under 18 rule (RIF/XML)</a></li>
<li><a href="examples/under18.rifps">Under 18 rule (RIF PS)</a></li>
<li><a href="examples/under18-foaf.rif">Under 18 rule (RIF/XML) (with import directive (OWL))</a></li>
<li><a href="examples/under18-foaf.rifps">Under 18 rule (RIF PS) (with import directive (OWL))</a></li>
<li><a href="examples/dcat.rdf">Synthetic DCAT example file (RDF/XML)</a></li>
<li><a href="examples/previous-report.html">Previous Report generated</a></li>
<li><a href="examples/previous-report-metadata.html">Previous Report generated (just metadata)</a></li>
</ul> 

<div id="footer">
<p id="logo"><a href="http://www.fundacionctic.org/"><img src="images/ctic.png"
alt="Fundacion CTIC"/></a> <a href="http://ontorule-project.eu"><img
src="images/ontorule.png" alt="ONTORULE Project"/></a> </p>

<p><a href="http://ontorule-project.eu/parrot">Parrot</a> is a RIF and OWL
documentation service developed <a href="http://ct.ctic.es">Fundación
CTIC</a>. <a href="https://bitbucket.org/fundacionctic/parrot">Source code is available</a>.</p>

<p>This work has been partially funded by <a href="http://ontorule-project.eu"
title="ONTORULE Web site">ONTORULE Project (FP7-ICT-2008-3, project reference
231875)</a>.</p>

<p>Some icons has been created by <a
href="http://www.famfamfam.com/about/">Mark James</a> and there are distributed
under <a href="http://creativecommons.org/licenses/by/2.5/">CreativeCommons-by
2.5</a> license.</p>
</div>
</div>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
<script type="text/javascript" src="report/js/jquery.corner.js"></script>
<script type="text/javascript" src="javascript/help-scripts.js"></script>

<script type="text/javascript">//<![CDATA[ 
var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-8820144-1']);
_gaq.push(['_trackPageview']);

(function() {
  var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
  ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
  var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
})();
//]]>         
</script>
</body>
</html>