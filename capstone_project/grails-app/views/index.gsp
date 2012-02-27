<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Omaha Maker Space</title>
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

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
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
			<ul'>
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
			<h1>About</h1>
			<p>The Omaha Maker Group exists to facilitate a place where people can explore Technology, Science and Art.  
			We operate a community workshop in Omaha, Nebraska and have bi-weekly meetings where people can collaborate, 
			share resources, create, and learn together. We are Omaha’s hackerspace.  If you’re an inventor, artist, 
			scientist, student, educator, hacker, maker, hobbyist, or just curious about how things work, you’ve come 
			to the right place.</p>

			<div id="controller-list" role="navigation">
				<h2>Available Controllers:</h2>
				<ul>
					<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
						<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
					</g:each>
				</ul>
			</div>
		</div>
	</body>
</html>
