<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
	<h2>
		<acme:message code="administrator.dashboard.form.title.fuppos"/>
	</h2>
	
	<h2>
		<acme:message code="administrator.dashboard.form.label.ratio-component-fuppo"/>
		<acme:print value="${String.format('%,.2f', ratioOfComponentWithFuppo)}%"/>
	</h2>
	
	<h3><acme:message code="administrator.dashboard.form.label.avg-quantity-fuppo-by-currency"/></h3>
	<table class="table table-sm" aria-describedby="administrator.dashboard.form.label.avg-quantity-fuppo-by-currency">
		<jstl:forEach items="${currencies}" var="currency">
			<tr>
				<th scope="row"><acme:print value="${currency}"/></th>
				<jstl:set value="${averageQuantityOfFuppoByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator()}" var="entrySet"/>
				<jstl:if test="${entrySet.hasNext()}">
					<jstl:forEach items="${entrySet}" var="entry">
						<th scope="row"><acme:print value="${entry.getValue()}"/></th>
					</jstl:forEach>
				</jstl:if>
			</tr>
		</jstl:forEach>
	</table>
	
	<h3><acme:message code="administrator.dashboard.form.label.deviation-quantity-fuppo-by-currency"/></h3>
	<table class="table table-sm" aria-describedby="administrator.dashboard.form.label.deviation-quantity-fuppo-by-currency">
		<jstl:forEach items="${currencies}" var="currency">
			<tr>
				<th scope="row"><acme:print value="${currency}"/></th>
				<jstl:set value="${deviationQuantityOfFuppoByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator()}"	var="entrySet"/>
				<jstl:if test="${entrySet.hasNext()}">
					<jstl:forEach items="${entrySet}" var="entry">
						<th scope="row"><acme:print value="${entry.getValue()}"/></th>
					</jstl:forEach>
				</jstl:if>
			</tr>
		</jstl:forEach>
	</table>
	
	<h3><acme:message code="administrator.dashboard.form.label.max-quantity-fuppo-by-currency"/></h3>
	<table class="table table-sm" aria-describedby="administrator.dashboard.form.label.max-quantity-fuppo-by-currency">
		<jstl:forEach items="${currencies}" var="currency">
			<tr>
				<th scope="row"><acme:print value="${currency}"/></th>
				<jstl:set value="${maximumQuantityOfFuppoByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator()}" var="entrySet"/>
				<jstl:if test="${entrySet.hasNext()}">
					<jstl:forEach items="${entrySet}" var="entry">
						<th scope="row"><acme:print value="${entry.getValue()}"/></th>
					</jstl:forEach>
				</jstl:if>
			</tr>
		</jstl:forEach>
	</table>
	
	<h3><acme:message code="administrator.dashboard.form.label.min-quantity-fuppo-by-currency"/></h3>
	<table class="table table-sm" aria-describedby="administrator.dashboard.form.label.min-quantity-fuppo-by-currency">
		<jstl:forEach items="${currencies}" var="currency">
			<tr>
				<th scope="row"><acme:print value="${currency}"/></th>
				<jstl:set value="${minimumQuantityOfFuppoByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator()}" var="entrySet"/>
				<jstl:if test="${entrySet.hasNext()}">
					<jstl:forEach items="${entrySet}" var="entry">
						<th scope="row"><acme:print value="${entry.getValue()}"/></th>
					</jstl:forEach>
				</jstl:if>
			</tr>
		</jstl:forEach>
	</table>

	<h2>
		<acme:message code="administrator.dashboard.form.title.components"/>
	</h2>
	
	<table class="table table-sm" aria-describedby="administrator.dashboard.form.label.number-components">
		<tr>
			<th scope="row">
				<acme:message code="administrator.dashboard.form.label.number-components"/>
			</th>
			<td>
				<acme:print value="${numberComponents}"/>
			</td>
		</tr>
	</table>
	<h3>
		<acme:message code="administrator.dashboard.form.label.average.value"/>
	</h3>
	<table class="table table-sm" aria-describedby="administrator.dashboard.form.label.average.value">
		<jstl:forEach var="entry" items="${averageRetailPriceComponents}">
			<tr>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.technology"/>
				</th>
				<td>
					<acme:print value="${entry.key.first}"/>
				</td>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.currency"/>
				</th>
				<td>
					<acme:print value="${entry.key.second}"/>
				</td>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.average.value"/>
				</th>
				<td>
					<acme:print value="${entry.value}"/>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	<h3>
		<acme:message code="administrator.dashboard.form.label.deviation.value"/>
	</h3>
	<table class="table table-sm" aria-describedby="administrator.dashboard.form.label.deviation.value">
		<jstl:forEach var="entry" items="${deviationRetailPriceComponents}">
			<tr>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.technology"/>
				</th>
				<td>
					<acme:print value="${entry.key.first}"/>
				</td>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.currency"/>
				</th>
				<td>
					<acme:print value="${entry.key.second}"/>
				</td>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.deviation.value"/>
				</th>
				<td>
					<acme:print value="${entry.value}"/>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	<h3>
		<acme:message code="administrator.dashboard.form.label.minimum.value"/>
	</h3>
	<table class="table table-sm" aria-describedby="administrator.dashboard.form.label.minimum.value">
		<jstl:forEach var="entry" items="${minimumRetailPriceComponents}">
			<tr>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.technology"/>
				</th>
				<td>
					<acme:print value="${entry.key.first}"/>
				</td>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.currency"/>
				</th>
				<td>
					<acme:print value="${entry.key.second}"/>
				</td>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.minimum.value"/>
				</th>
				<td>
					<acme:print value="${entry.value}"/>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	<h3>
		<acme:message code="administrator.dashboard.form.label.maximum.value"/>
	</h3>
	<table class="table table-sm" aria-describedby="administrator.dashboard.form.label.maximum.value">
		<jstl:forEach var="entry" items="${maximumRetailPriceComponents}">
			<tr>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.technology"/>
				</th>
				<td>
					<acme:print value="${entry.key.first}"/>
				</td>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.currency"/>
				</th>
				<td>
					<acme:print value="${entry.key.second}"/>
				</td>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.maximum.value"/>
				</th>
				<td>
					<acme:print value="${entry.value}"/>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	
	<h2>
		<acme:message code="administrator.dashboard.form.title.tools"/>
	</h2>
	
	<table class="table table-sm" aria-describedby="administrator.dashboard.form.label.number-tools">
		<tr>
			<th scope="row">
				<acme:message code="administrator.dashboard.form.label.number-tools"/>
			</th>
			<td>
				<acme:print value="${numberTools}"/>
			</td>
		</tr>
	</table>
	
	<h3>
		<acme:message code="administrator.dashboard.form.label.average.value"/>
	</h3>
	<table class="table table-sm" aria-describedby="administrator.dashboard.form.label.average.value">
		<jstl:forEach var="entry" items="${averageRetailPriceTools}">
			<tr>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.currency"/>
				</th>
				<td>
					<acme:print value="${entry.key}"/>
				</td>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.average.value"/>
				</th>
				<td>
					<acme:print value="${entry.value}"/>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	<h3>
		<acme:message code="administrator.dashboard.form.label.deviation.value"/>
	</h3>
	<table class="table table-sm" aria-describedby="administrator.dashboard.form.label.deviation.value">
		<jstl:forEach var="entry" items="${deviationRetailPriceTools}">
			<tr>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.currency"/>
				</th>
				<td>
					<acme:print value="${entry.key}"/>
				</td>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.deviation.value"/>
				</th>
				<td>
					<acme:print value="${entry.value}"/>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	<h3>
		<acme:message code="administrator.dashboard.form.label.minimum.value"/>
	</h3>
	<table class="table table-sm" aria-describedby="administrator.dashboard.form.label.minimum.value">
		<jstl:forEach var="entry" items="${minimumRetailPriceTools}">
			<tr>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.currency"/>
				</th>
				<td>
					<acme:print value="${entry.key}"/>
				</td>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.minimum.value"/>
				</th>
				<td>
					<acme:print value="${entry.value}"/>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	<h3>
		<acme:message code="administrator.dashboard.form.label.maximum.value"/>
	</h3>
	<table class="table table-sm" aria-describedby="administrator.dashboard.form.label.maximum.value">
		<jstl:forEach var="entry" items="${maximumRetailPriceTools}">
			<tr>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.currency"/>
				</th>
				<td>
					<acme:print value="${entry.key}"/>
				</td>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.maximum.value"/>
				</th>
				<td>
					<acme:print value="${entry.value}"/>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	
	<h2>
		<acme:message code="administrator.dashboard.form.title.patronages"/>
	</h2>
	
	<table class="table table-sm" aria-describedby="administrator.dashboard.form.label.number-patronages.value">
		<jstl:forEach var="entry" items="${numberPatronages}">
			<tr>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.status"/>
				</th>
				<td>
					<acme:print value="${entry.key}"/>
				</td>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.number-patronages.value"/>
				</th>
				<td>
					<acme:print value="${entry.value}"/>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	<h3>
		<acme:message code="administrator.dashboard.form.label.average.value"/>
	</h3>
	<table class="table table-sm" aria-describedby="administrator.dashboard.form.label.average.value">
		<jstl:forEach var="entry" items="${averageBudgetPatronage}">
			<tr>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.status"/>
				</th>
				<td>
					<acme:print value="${entry.key}"/>
				</td>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.average.value"/>
				</th>
				<td>
					<acme:print value="${entry.value}"/>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	<h3>
		<acme:message code="administrator.dashboard.form.label.deviation.value"/>
	</h3>
	<table class="table table-sm" aria-describedby="administrator.dashboard.form.label.deviation.value">
		<jstl:forEach var="entry" items="${deviationBudgetPatronage}">
			<tr>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.status"/>
				</th>
				<td>
					<acme:print value="${entry.key}"/>
				</td>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.deviation.value"/>
				</th>
				<td>
					<acme:print value="${entry.value}"/>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	<h3>
		<acme:message code="administrator.dashboard.form.label.minimum.value"/>
	</h3>
	<table class="table table-sm" aria-describedby="administrator.dashboard.form.label.minimum.value">
		<jstl:forEach var="entry" items="${minimumBudgetPatronage}">
			<tr>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.status"/>
				</th>
				<td>
					<acme:print value="${entry.key}"/>
				</td>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.minimum.value"/>
				</th>
				<td>
					<acme:print value="${entry.value}"/>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	<h3>
		<acme:message code="administrator.dashboard.form.label.maximum.value"/>
	</h3>
	<table class="table table-sm" aria-describedby="administrator.dashboard.form.label.maximum.value">
		<jstl:forEach var="entry" items="${maximumBudgetPatronage}">
			<tr>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.status"/>
				</th>
				<td>
					<acme:print value="${entry.key}"/>
				</td>
				<th scope="row">
					<acme:message code="administrator.dashboard.form.label.maximum.value"/>
				</th>
				<td>
					<acme:print value="${entry.value}"/>
				</td>
			</tr>
		</jstl:forEach>
	</table>
</acme:form>