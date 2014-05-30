<%@ tag description = "Outputs element wrapper for input elements" pageEncoding="UTF-8"%>  

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- Input attributes --%>
<%@ attribute name="name" required="true" type="java.lang.String" description="Input name" %>
<%@ attribute name="type" required="false" type="java.lang.String" description="Input type, defaults to text" %>
<%@ attribute name="value" required="false" type="java.lang.String" description="Override form value. Set to '[null]' to force empty value" %>
<%@ attribute name="label" required="false" type="java.lang.String" description="Completely override i18n label" %>
<%@ attribute name="form" required="false" type="java.lang.String" description="Name of the form for dynamic i18n messages" %>
<%@ attribute name="placeholder" required="false" type="java.lang.Boolean" description="Placeholder text for browsers that support it" %>
<%@ attribute name="title" required="false" type="java.lang.Boolean" description="Show a title value for select inputs" %>
<%-- Style attributes --%>
<%@ attribute name="id" required="false" type="java.lang.String" description="Input id, defaults to name" %>
<%@ attribute name="cssClass" required="false" type="java.lang.String" description="Optional wrapper class" %>
<%@ attribute name="styleId" required="false" type="java.lang.String" description="Add an Id to the more external wrapper" %>
<%-- Input attributes --%>
<%@ attribute name="size" required="false" type="java.lang.String" description="Size attribute for select inputs" %>
<%@ attribute name="other" required="false" type="java.lang.String" description="Other misc attributes, e.g. data attributes" %>
<%@ attribute name="checked" required="false" type="java.lang.Boolean" description="Whether a checkbox is checked by default or not" %>
<%@ attribute name="labelOverride" required="false" type="java.lang.Boolean" description="Completely override a resource bundle message for dynamic data" %>
<%@ attribute name="disabled" required="false" type="java.lang.Boolean" description="Input is only available to view" %>
<%@ attribute name="src" required="false" type="java.lang.String" description="Input[type=image] file location" %>
<%@ attribute name="list" required="false" type="java.lang.String" description="Autocomplete off, defaults to on" %>
<%@ attribute name="autocomplete" required="false" type="java.lang.String" description="Autocomplete off, defaults to on" %>
<%-- Validation attributes --%>
<%@ attribute name="required" required="false" type="java.lang.Boolean" description="Field is required" %>
<%@ attribute name="maxLength" required="false" type="java.lang.Integer" description="Maximum number of characters" %>
<%@ attribute name="minLength" required="false" type="java.lang.Integer" description="Minimum number of characters" %>
<%@ attribute name="maxNumber" required="false" type="java.lang.Integer" description="Maximum value of number" %>
<%@ attribute name="minNumber" required="false" type="java.lang.Integer" description="Minimum value of number" %>
<%@ attribute name="maxDate" required="false" type="java.lang.String" description="Maximum value of date input yyyy-mm-dd" %>
<%@ attribute name="minDate" required="false" type="java.lang.String" description="Minimum value of date input: yyyy-mm-dd" %>
<%@ attribute name="creditcard" required="false" type="java.lang.Boolean" description="Field must be a valid creditcard number" %>
<%@ attribute name="equalTo" required="false" type="java.lang.String" description="Field which value must match to" %>
<%@ attribute name="oneFromGroup" required="false" type="java.lang.String" description="Validation based on other fields. This validator takes priority over other validation methods. Which field 'name' should be valid to validate this field? i.e. 'telephoneNumber' or 'mobileNumber'." %>
<%@ attribute name="range" required="false" type="java.lang.String" description="Min,Max,Step" %>
<%-- Extra layout attributes --%>
<%@ attribute name="halfGrid" required="false" type="java.lang.Boolean" description="Trigger to be used to half input sizes e.g. month/year selectors" %>
<%@ attribute name="smallLabel" required="false" type="java.lang.Boolean" description="Trigger to be used with 'halfGrid' attribute for setting second label smaller" %>
<%@ attribute name="countryReveal" required="false" type="java.lang.String" description="Country reveal for address forms" %>
<%@ attribute name="countryConceal" required="false" type="java.lang.String" description="Country conceal for address forms" %>

<%-- Defaults --%>
<c:set var="radioInput" value="${type == 'radio' || type == 'checkbox'}" />
<c:set var="type" value="${!empty type ? type : 'text'}" />
<c:set var="form" value="${!empty form ? form : 'forms'}" />
<c:set var="id">${!empty id ? id : name}<c:if test="${radioInput}">_${fn:toLowerCase(value)}</c:if></c:set>

<%-- Set default value / styles --%>
<c:if test="${empty label}">
	<c:set var="label">${form}.${fn:toLowerCase(name)}<c:if test="${type == 'radio'}">.${fn:toLowerCase(value)}</c:if></c:set>
</c:if>
<c:if test="${value != '[null]'}">
	<c:set var="val" value="${empty value ? param[name] : value}" />
</c:if>

<%-- Dynamic attribute information --%>
<c:set var="attributes">
	<c:if test="${((type == 'checkbox' || type == 'radio') && !empty value && param[name] == value) || checked}"> checked="checked"</c:if>
	<c:if test="${type == 'select' && !empty size}"> size="${size}"</c:if>
	<c:if test="${disabled}"> disabled="disabled"</c:if>
	<c:if test="${!empty src}"> src="${src}"</c:if>
	<c:if test="${!empty autocomplete}"> autocomplete="${autocomplete}"</c:if>
	<c:if test="${!empty list}"> list="${list}"</c:if>
	<c:if test="${placeholder}"> placeholder="<fmt:message key="${label}.placeholder" />"</c:if>
	<c:if test="${!empty countryReveal}"> data-country-reveal="${countryReveal}"</c:if>
	<c:if test="${!empty countryConceal}"> data-country-conceal="${countryConceal}"</c:if>
	<c:if test="${!empty val && type != 'select'}"> value="${val}"</c:if>
	<c:if test="${!empty other}"> ${other}</c:if>
	<%-- Dynamic validation information --%>
	<c:if test="${required}"> data-rule-required="true" data-msg-required="<fmt:message key="${label}.validation.required" />"</c:if>
	<c:if test="${type == 'email'}"> data-rule-email="true" data-msg-email="<fmt:message key="${label}.validation.required" />"</c:if>
	<c:if test="${type == 'date'}"> data-rule-date="true" data-msg-date="<fmt:message key="${label}.validation.date" />"</c:if>
	<c:if test="${type == 'tel'}"> data-rule-telephone="true" data-msg-telephone="<fmt:message key="${label}.validation.telephone" />"</c:if>
	<c:if test="${type == 'number'}"> data-rule-number="true" data-msg-number="<fmt:message key="${label}.validation.number" />" </c:if>
	<c:if test="${creditcard}"> data-rule-creditcard="true" data-msg-creditcard="<fmt:message key="${label}.validation.creditcard" />"</c:if>
	<c:if test="${!empty equalTo}"> data-rule-equalTo="${equalTo}" data-msg-equalTo="<fmt:message key="${label}.validation.match" />"</c:if>
	<c:if test="${!empty oneFromGroup}"> data-oneFromGroup="${oneFromGroup}"</c:if>
	<c:if test="${minLength > 0}"> data-rule-minlength="${minLength}" data-msg-minlength="<fmt:message key="${label}.validation.minlength" />"</c:if>
	<c:if test="${maxLength > 0}"> maxlength="${maxLength}" data-rule-maxlength="${maxLength}" data-msg-maxlength="<fmt:message key="${label}.validation.maxlength" />"</c:if>
	<c:if test="${minNumber > 0}"> min="${minNumber}" data-rule-min="${minNumber}" data-msg-min="<fmt:message key="${label}.validation.min" />"</c:if>
	<c:if test="${maxNumber > 0}"> max="${maxNumber}" maxlength="${maxNumber}" data-rule-maxlength="${maxNumber}" data-msg-maxlength="<fmt:message key="${label}.validation.max" />"</c:if>
	<c:if test="${!empty minDate}"> min="${minDate}" data-msg-min="<fmt:message key="${label}.validation.required" />"</c:if>
	<c:if test="${!empty maxDate}"> max="${maxDate}" data-msg-max="<fmt:message key="${label}.validation.required" />"</c:if>
	<c:if test="${!empty range}"><c:set var="range" value="${fn:split(range,',')}" /> min="${range[0]}" max="${range[1]}" step="${range[2]}"</c:if>
</c:set>

<%-- Generate HTML for input --%>
<c:set var="formInput">
	<c:choose>
		<c:when test="${type == 'select'}">
			<select class="input" id="${id}" name="${name}" ${attributes}>
				<c:if test="${title}"><option value=""><fmt:message key="${label}.title" /></option></c:if>
				<jsp:doBody />
			</select>
		</c:when>
		<c:when test="${type == 'textarea'}">
			<textarea class="input" id="${id}" name="${name}" ${attributes}>${val}</textarea>
		</c:when>
		<c:otherwise>
			<input class="input" type="${type}" id="${id}" name="${name}" ${attributes} />
		</c:otherwise>
	</c:choose>
</c:set>

<%-- Generate HTML for label --%>
<c:set var="formLabel">
	<label for="${id}" class="label <c:if test=" ${required}">required</c:if>">
		<c:choose>
			<c:when test="${!labelOverride}"><fmt:message key="${label}" /></c:when>
			<c:otherwise><span>${label}</span></c:otherwise>
		</c:choose>
	</label>
</c:set>

<%-- Print label and input --%>
<c:choose>
	<c:when test="${type == 'hidden'}">${formInput}</c:when>
	<c:otherwise>
		<div class="formElement ${type} ${cssClass}" <c:if test="${!empty styleId}">id="${styleId}"</c:if>>
			<c:choose>
				<c:when test="${radioInput}">${formInput}${formLabel}</c:when>
				<c:when test="${type == 'hidden'}">${formInput}</c:when>
				<c:otherwise>${formLabel}${formInput}</c:otherwise>
			</c:choose>
		</div>
	</c:otherwise>
</c:choose>

<c:if test="${type == 'range'}">
	<output for="${name}">${val}%</output>
</c:if>