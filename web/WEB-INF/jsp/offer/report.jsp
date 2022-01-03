<%-- 
    Document   : report
    Created on : 30-Oct-2021, 4:49:48 PM
    Author     : njuser
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${process eq 'viewReport'}">
    <c:if test="${status eq 1}">

        <table class="table table-responsive container"  style="margin:3rem 5rem;">

            <thead class="table-dark">
                <tr>
                    <th scope="col">Inquiry Id</th>
                    <th scope="col">Inquiry List</th>
                    <th scope="col">Offer Id</th>
                    <th scope="col">View Generated Offer</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${ReportList}" var="i">
                    <tr>
                        <th scope="row">${i.inquiryid} <br>
                            ${i.firstname} ${i.lastname}
                        </th>
                        <td>carpetarea : ${i.carpetarea} <br>
                            roof       : ${i.roof}<br>
                            floor      : ${i.floor}<br>
                            fire safety: ${i.firesafety}
                        </td>
                        <td>${i.offerid}</td>
    <!--                    <td><button type="button" onclick="offerDetail('${i.inquiryid}');" class="btn btn-primary">View Generated Offer</button></td>                   -->
                        <td><button type="button" onclick="offerDetail('${i.inquiryid}');" class="btn btn-primary" data-toggle="modal" data-target="#offer-detail">
                                View Generated Offer
                            </button></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div id="load-modal"></div>
    </c:if>
    <c:if test="${status eq 0}">
        <div class="alert alert-primary container" role="alert" style="margin-top: 2rem;">
            <h4>There is No history Of Generated Offer</h4>
        </div>
    </c:if>
</c:if>
<c:if test="${process eq 'viewOfferList'}">
    <!-- The Modal -->
    <div class="modal fade" id="${modalid}">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Offer History Details </h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <c:if test="${result eq 1}">
                        <center><h4 class="modal-title">Generated Offer History </h4></center>
                        <table class="table table-responsive"  style="margin:2rem 18rem;">
                            
                            <thead class="table-dark">
                                <tr>
                                    <th scope="col">Offer Id</th>
                                    <th scope="col">Coverage</th>
                                    <th scope="col">Premium</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${generatedOfferList}" var="i">
                                    <tr>
                                        <th scope="row">${i.offerid}</th>

                                        <td> ${i.coverage} </td>
                                        <td>${i.premium}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                          
                    </c:if>
                    <c:if test="${result eq 0}">
                        <div class="alert alert-danger container" role="alert" style="margin-top: 2rem;">
                            <h4>No History Of Generated Offer<h4>
                        </div>
                    </c:if>
                    <!-----------------------------update coverage list------------------- -->
                    <center><h4 class="modal-title">Updated Offer History </h4>
                    <c:if test="${status eq 1}">

                        <table class="table table-responsive"  style="margin:2rem 10rem;">

                            <thead class="table-dark">
                                <tr>
                                    <th scope="col">Offer Id</th>
                                    <th scope="col">Carpetarea</th>
                                    <th scope="col">Roof</th>
                                    <th scope="col">Floor</th>
                                    <th scope="col">Fire Safety</th>
                                    <th scope="col">Year</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${OfferList}" var="i">
                                    <tr>
                                        <th scope="row">${i.offerid}</th>                                    
                                        <td>${i.carpetarea}</td>
                                        <td>${i.roof}</td>
                                        <td>${i.floor}</td>
                                        <td>${i.firesafety}</td>
                                        <td>${i.year}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <center>
                    </c:if>
                    <c:if test="${status eq 0}">
                        <div class="alert alert-danger container" role="alert" style="margin-top: 2rem;">
                            <h4>No History Of Upadted Offer<h4>
                        </div>
                    </c:if>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>
</c:if>