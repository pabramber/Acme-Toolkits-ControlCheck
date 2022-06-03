<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.roles.Provider,acme.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link" action="http://www.example.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.jespaepae" action="https://coderadio.freecodecamp.org/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.pabramber" action="https://www.fromsoftware.jp/ww/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.borpincal" action="http://www.youtube.com"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.raugalroc" action="https://www.marca.com"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.juaortgue" action="https://www.game.es"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.nurgomari" action="https://www.twitch.tv"/>
			<acme:menu-separator/>
	    	<acme:menu-suboption code="master.menu.any.user-account" action="/any/user-account/list"/>
			<acme:menu-suboption code="master.menu.anonymous.invention.list-component" action="/any/invention/list-component"/>
			<acme:menu-suboption code="master.menu.anonymous.invention.list-tool" action="/any/invention/list-tool"/>
			<acme:menu-suboption code="master.menu.anonymous.toolkits.list-published-toolkits" action="/any/toolkit/list-published"/>
			<acme:menu-suboption code="master.menu.anonymous.chirp.list" action="/any/chirp/list"/>
			<acme:menu-suboption code="master.menu.anonymous.chirp.create" action="/any/chirp/create"/>
      
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-suboption code="master.menu.administrator.system-configuration" action="/administrator/system-configuration/show"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.administrator-dashboard" action="/administrator/administrator-dashboard/show"/>
      		<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.any.user-account" action="/any/user-account/list"/>
      		<acme:menu-suboption code="master.menu.anonymous.invention.list-component" action="/any/invention/list-component"/>
      		<acme:menu-suboption code="master.menu.anonymous.invention.list-tool" action="/any/invention/list-tool"/>
      		<acme:menu-suboption code="master.menu.administrator.toolkits.list-published-toolkits" action="/any/toolkit/list-published"/>
      		<acme:menu-suboption code="master.menu.anonymous.chirp.list" action="/any/chirp/list"/>
      		<acme:menu-separator/>
      		<acme:menu-suboption code="master.menu.authenticated.announcement.list" action="/authenticated/announcement/list"/>
      		 <acme:menu-suboption code="master.menu.administrator.announcement.create" action="/administrator/announcement/create"/>
      		
      		<acme:menu-suboption code="master.menu.authenticated.system-configuration" action="/authenticated/system-configuration/show"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">
			<acme:menu-suboption code="master.menu.patron.patronages" action="/patron/patronage/list"/>
			<acme:menu-suboption code="master.menu.patron.patronage-report" action="/patron/patronage-report/list"/>
			<acme:menu-separator/>
      		<acme:menu-suboption code="master.patron.patron-dashboard" action="/patron/patron-dashboard/show"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.any.user-account" action="/any/user-account/list"/>
			<acme:menu-suboption code="master.menu.anonymous.invention.list-component" action="/any/invention/list-component"/>
			<acme:menu-suboption code="master.menu.anonymous.invention.list-tool" action="/any/invention/list-tool"/>
			<acme:menu-suboption code="master.menu.patron.toolkits.list-published-toolkits" action="/any/toolkit/list-published"/>
      		<acme:menu-suboption code="master.menu.anonymous.chirp.list" action="/any/chirp/list"/>
      		<acme:menu-separator/>
      		<acme:menu-suboption code="master.menu.authenticated.announcement.list" action="/authenticated/announcement/list"/>
      		<acme:menu-suboption code="master.menu.authenticated.system-configuration" action="/authenticated/system-configuration/show"/>
      		<acme:menu-separator/>
      		<acme:menu-suboption code="master.menu.patron.patronage.create" action="/patron/patronage/create"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.inventor" access="hasRole('Inventor')">
			<acme:menu-suboption code="master.menu.inventor.fuppo.list" action="/inventor/fuppo/list-mine"/>
			<acme:menu-suboption code="master.menu.inventor.invention.list-tools" action="/inventor/invention/list-mine?type=TOOL"/>
			<acme:menu-suboption code="master.menu.inventor.invention.list-components" action="/inventor/invention/list-mine?type=COMPONENT"/>
			<acme:menu-suboption code="master.menu.inventor.toolkits.list-toolkits" action="/inventor/toolkit/list-mine"/>
			<acme:menu-suboption code="master.menu.inventor.patronage.list-patronages" action="/inventor/patronage/list"/>
			<acme:menu-suboption code="master.menu.inventor.patronage-report" action="/inventor/patronage-report/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.any.user-account" action="/any/user-account/list"/>
			<acme:menu-suboption code="master.menu.anonymous.invention.list-component" action="/any/invention/list-component"/>
			<acme:menu-suboption code="master.menu.anonymous.invention.list-tool" action="/any/invention/list-tool"/>
			<acme:menu-suboption code="master.menu.inventor.toolkits.list-published-toolkits" action="/any/toolkit/list-published"/>
      		<acme:menu-suboption code="master.menu.anonymous.chirp.list" action="/any/chirp/list"/>
      		<acme:menu-separator/>
      		<acme:menu-suboption code="master.menu.authenticated.announcement.list" action="/authenticated/announcement/list"/>
      		<acme:menu-suboption code="master.menu.authenticated.system-configuration" action="/authenticated/system-configuration/show"/>
      		<acme:menu-separator/>
      		<acme:menu-suboption code="master.menu.inventor.invention.create" action="/inventor/invention/create"/>
		</acme:menu-option>
		
		
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.become-inventor" action="/authenticated/inventor/create" access="!hasRole('Inventor')"/>
			<acme:menu-suboption code="master.menu.user-account.become-patron" action="/authenticated/patron/create" access="!hasRole('Patron')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

