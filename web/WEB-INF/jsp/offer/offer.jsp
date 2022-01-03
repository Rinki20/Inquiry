<%-- 
    Document   : offer
    Created on : 27-Oct-2021, 2:13:31 PM
    Author     : njuser
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${process eq 'generateOffer'}">
  <div class="card text-center container"  style="margin-top:5rem;">
      <div class="card-header">
          <h3>Details of Coverage And Premium</h3>
      </div>
        <div class="card-body">            
            <c:if test="${status eq '1'}">
                <h5 class="card-title">Offer Id</h5>
                <p class="card-text">${offer.offerid}</p>
                <h5 class="card-title">Coverage</h5>
                <p class="card-text">${offer.coverage}</p>
                <h5 class="card-title">Premium</h5>
                <p class="card-text">${offer.premium}</p>
            </c:if>
                
        </div>
  </div>
    <c:if test="${status eq '0'}">
                <div class="alert alert-danger container" role="alert" style="margin-top: 5rem;">
                we can't generate quote for your inquiry Id
            </div> 
    </c:if> 
</c:if>
        
<c:if test="${process eq 'viewOffer'}">       
    <div class="card text-center container"  style="margin-top:5rem; width:40%">
        <div class="card-header">
          <h3>Details of Coverage And Premium</h3>
      </div>
      <div class="card-body">
    
        <c:if test="${status eq '1'}">
            <c:forEach var="off" items="${Offer}">
                <h5 class="card-title">Offer Id</h5>
                <p class="card-text">${off.offerid}</p>
                
                <h5 class="card-title">Coverage</h5>
                <p class="card-text">${off.coverage}</p> 
                
                <h5 class="card-title">Premium</h5>
                <p class="card-text">${off.premium}</p>
            </c:forEach>
        </c:if>
        
    </div>
  </div>
    <c:if test="${status eq '0'}">
            <div class="alert alert-danger container" role="alert" style="margin-top: 5rem;">
                we can't generate quote for your inquiry Id
            </div> 
    </c:if>
</c:if>

