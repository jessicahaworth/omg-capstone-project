<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>

	<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}
			</style>

		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<g:layoutHead/>
        <r:layoutResources />
	</head>
	<body>
	<div><a href="http://omahamakergroup.org/"><img src="${resource(dir: 'images', file: 'cropped-OMG_Title2.jpg')}" alt="Grails"/></a></div>
	<div id="status" role="complementary">
			<h2>Contributors</h2>
			<ul>
				<li><a href="http://davestech.blogspot.com/" title="Simply documentation of projects I start and sometimes even finish.">Dave&#8217;s Tech Blog</a></li>
				<li><a href="http://www.fusselman.org/wp" title="Technology, Travel and Everything Else">Fusselman.org » Omaha Maker Group</a></li>
				<li><a href="http://grickle.org" title="News, musings, and other ramblings of James Harr">Grickle Blag</a></li>
				<li><a href="http://www.rspoon.com" title="I make stuff">RustySpoon Random Stuff</a></li>

			</ul>

			<h2>Friends</h2>
			<ul>
				<li><a href="http://www.c3kc.org/" rel="contact">Cowtown Computer Congress</a></li>
				<li><a href="http://blog.hackerspaces.org/">Hackerspaces.org</a></li>
				<li><a href="http://makezine.com/" rel="contact">Make Magazine</a></li>
				<li><a href="http://www.makekc.org/" rel="contact">Make: KC</a></li>
				<li><a href="http://omaha2600.org/">Omaha 2600</a></li>
				<li><a href="http://www.reddit.com/r/Omaha/">Omaha Reddit</a></li>
				<li><a href="http://www.patentless.org/" title="Innovations and Inventions for the people by people.">Patentless.org</a></li>
				<li><a href="http://www.siliconprairienews.com/" rel="contact">Silicon Prairie News</a></li>
				<li><a href="http://www.tavshed.com" rel="friend" title="tavshed.com" target="_blank">tavshed.com</a></li>
			</ul>
			<h2>Resources</h2>
			<ul>
				<li><a href="http://webchat.freenode.net/?channels=omahamaker&#038;uio=OT10cnVlJjEyPXRydWU62" title="irc.freenode.net#omahamaker">#omahamaker IRC Channel</a></li>
				<li><a href="http://omahamakergroup.org/directions-to-the-space/" rel="me">Directions to the Space</a></li>
				<li><a href="https://docs.google.com/spreadsheet/ccc?key=0AiSDs5PearUjdC1lM0hEVEh5bzRUYWxFd2tKQ0ZacUE&#038;hl=en_US" title="A list of useful stuff, both in the Makery and at people&#8217;s homes&#8230;if you need it&#8230;ask for it!">Equipment Inventory</a></li>
				<li><a href="http://www.ustream.tv/channel/omaha-maker-group-meetings" title="If you can&#8217;t make a meeting in person, join it virtually.  We broadcast during the regular meetings.">Live Broadcasts of Meetings</a></li>
				<li><a href="http://www.ustream.tv/channel/makery-mendel" title="Ustream live videos of Makery Prints">Makery Mendel Prints</a></li>
				<li><a href="http://omahamakergroup.org/?attachment_id=19" rel="me">OMG Bylaws</a></li>
				<li><a href="http://groups.google.com/group/omaha-maker-group">OMG Google Group</a></li>
			</ul>
		</div>

		<div id="page-body" role="main">
		<g:layoutBody/>
		</div>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<g:javascript library="application"/>

		<r:layoutResources />
        
	</body>
</html>