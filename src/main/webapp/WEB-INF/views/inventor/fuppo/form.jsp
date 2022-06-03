<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:input-textbox code="inventor.fuppo.form.label.component.code" path="component.code"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:input-textbox code="inventor.fuppo.form.label.component.code" path="component.code" readonly="true"/>
		</jstl:otherwise>
	</jstl:choose>
	<acme:input-textbox code="inventor.fuppo.form.label.code" path="code"/>
	<jstl:if test="${command != 'create'}">
		<acme:input-moment code="inventor.fuppo.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	<acme:input-textbox code="inventor.fuppo.form.label.name" path="name"/>
	<acme:input-textarea code="inventor.fuppo.form.label.statement" path="statement"/>
	<acme:input-moment code="inventor.fuppo.form.label.periodStart" path="periodStart"/>
	<acme:input-moment code="inventor.fuppo.form.label.periodEnd" path="periodEnd"/> 
	<acme:input-money code="inventor.fuppo.form.label.quantity" path="quantity"/>
	<acme:input-url code="inventor.fuppo.form.label.moreInfo" path="moreInfo"/>
	
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:submit code="inventor.fuppo.form.button.update" action="/inventor/fuppo/update"/>
			<acme:submit code="inventor.fuppo.form.button.delete" action="/inventor/fuppo/delete"/>			
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.fuppo.form.button.create" action="/inventor/fuppo/create"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>
	