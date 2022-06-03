<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.fuppo.list.label.code" path="code" width="10%"/>
	<acme:list-column code="inventor.fuppo.list.label.name" path="name" width="10%"/>
	<acme:list-column code="inventor.fuppo.list.label.statement" path="statement" width="80%"/>
	<acme:list-payload path="payload"/>
</acme:list>
<acme:button code="inventor.fuppo.list.button.create" action="/inventor/fuppo/create"/>
