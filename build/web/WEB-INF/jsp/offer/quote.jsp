<%-- 
    Document   : quote
    Created on : 06-Oct-2021, 5:11:26 PM
    Author     : njuser
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${process eq 'getQuote'}">
        <c:if test="${stateList eq null}"> 
            <h5>List is Null</h5>
        </c:if> 
        <form id="quoteform">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="firstname">First Name :</label>
                            <input type="text" class="form-control" name="firstname" tabindex="1" placeholder="Enter First Name" id="firstname">
                        </div>
                        <div class="form-group">
                            <label style="display:block;" for="dob">DOB :</label>
                            <input type="text" class="form-control" readonly name="dob" tabindex="3" style="width:97%; display:inline-block;" placeholder="Enter Date of Birth" id="dob">
                        </div>
                        <div class="form-group">
                            <label for="state">State :</label>
                            <select  class="form-control" name="state" tabindex="5" id="state" onchange="CityLoader()">
                                <option>Select State</option>
                                <c:forEach items="${stateList}" var="state">
                                    <option>${state.geo_name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="pincode">Pincode :</label>
                            <input type="text" class="form-control" name="pincode" tabindex="7" placeholder="Enter Pincode" id="pincode">
                        </div>
                        <div class="form-group">
                            <label for="roof" >Roof Material :</label>
                            <input type="radio"  tabindex="9" id="roof" name="roof" value="WOODEN" checked> Wooden
                            <input type="radio" id="roof" name="roof" value="RCC"> RCC
                        </div>
                        <div class="form-group">
                            <label for="safety" >Fire Safety :</label>
                            <input type="radio" tabindex="11" id="safety" name="safety" value="1" checked> Yes
                            <input type="radio" id="safety" name="safety" value="0"> No
                        </div>
                    </div>


                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="lastname">Last Name :</label>
                            <input type="text" class="form-control" tabindex="2" name="lastname" placeholder="Enter Last Name" id="lastname">
                        </div>
                        <div class="form-group">
                            <label for="address">Address :</label>
                            <input type="text" class="form-control"  tabindex="4" name="address" placeholder="Enter Address" id="address">
                        </div>
                        <div class="form-group">
                            <label for="city" >City :</label>
                            <select tabindex="6" class="form-control" name="city" id="city">

                            </select>
                        </div>
                        <div class="form-group">
                            <label for="carpetarea">Carpet Area :</label>
                            <input type="text" class="form-control" tabindex="8" name="carpetarea" placeholder="Enter Carpet Area" id="carpetarea">
                        </div>
                        <div class="form-group">
                            <label for="floor" >Floor Material :</label>
                            <input type="radio" tabindex="10" id="floor" name="floor" value="WOODEN" checked> Wooden
                            <input type="radio" id="floor" name="floor" value="TILE"> Tile
                        </div>
                        <div class="form-group">
                            <label for="builtyear">Built Year :</label>
                            <input tabindex="12" type="text" class="form-control" name="builtyear" placeholder="Enter Built Year" id="builtyear">
                        </div>
                    </div>
                </div>
                <button type="button" tabindex="13" class="btn btn-primary" onclick="javascript:validateQuoteForm();">Get Customize Quote</button>
                <button type="button" tabindex="14" class="btn btn-warning">Reset</button>
            </div>
        </form>
    </c:when>

    <c:when test="${process eq 'changeCoverage'}">
        <c:if test="${status eq 1 }">
            <div class="form-container container" style="width:50%;margin:2.5rem auto;border:1px solid darkgrey;border-radius:1rem;">
                <form class="px-4 py-3" id="coverageForm" style="width:100%;margin:1.8rem 0;">
                    <div class="form-group">
                        <label for="inquiryid">Inquiry ID</label>
                        <select class="form-control" id="inquiryid" onchange="getCoverageAndPremium();">
                            <option>Select Inquiry ID</option>
                            <c:forEach items="${inquiryidList}" var="i">
                                <option>${i.inquiryid}</option>
                            </c:forEach>
                        </select>
                        <div class="form-group">
                            <label for="fname">First Name</label>
                            <input type="text" readonly="true" class="form-control" id="fname" placeholder="First Name">
                        </div>
                        <div class="form-group">
                            <label for="lname">Last Name</label>
                            <input type="text" readonly="true" class="form-control" id="lname" placeholder="Last Name">
                        </div>
                        <div class="form-group">
                            <label for="Coverage">Coverage</label>
                            <input type="text" class="form-control" id="coverage" placeholder="Coverage" onchange="changePremium()">
                        </div>
                        <div class="form-group">
                            <label for="premium">Premium</label>
                            <input type="text" readonly="true" class="form-control" id="premium" placeholder="premium">
                        </div>
                    </div>
                    <button type="button" class="btn btn-primary" id="btn-offer-update" onclick="OfferUpdate()" disabled>change Coverage</button>
                    <br><br>
                    <button type="button" class="btn btn-primary" id="btn-quote-update" onclick="UpdateQuoteLoader()" disabled>update the Quote</button>
                    <div id="hidden"></div>
                     <div id="hidden1"></div>
                     <div id="hidden2"></div>
                </form>
            </div>
        </c:if>
        <c:if test="${status eq 0 }">
            <div class="alert alert-primary container" role="alert" style="margin-top: 5rem;">
                    Unable To Change Coverage
            </div>  
        </c:if>
    </c:when>

            
    <c:when test="${process eq 'viewOffer'}">
        <c:if test="${status  eq 1}">
            <table class="table container">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">Inquiry ID</th>
                        <th scope="col">First Name</th>
                        <th scope="col">Last Name</th>
                        <th scope="col">State</th>
                        <th scope="col">city</th>
                        <th scope="col">Opertaion</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${offerList}" var="i">  
                        <tr>
                            <th scope="row">${i.inquiryid}</th>
                            <td>${i.firstname}</td>
                            <td>${i.lastname}</td>
                            <td>${i.state}</td>
                            <td>${i.city}</td>

                            <c:if test="${i.offerid eq null}">
                                <td><button type="button" onclick="GenerateOffer('${i.inquiryid}')" class="btn btn-primary">Generate Offer</button></td>
                            </c:if>
                            <c:if test="${i.offerid ne null}">
                                <td><button type="button" onclick="ViewOffer('${i.inquiryid}')" class="btn btn-secondary">view Offer</button></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${status eq 0}">
            <div class="alert alert-primary container" role="alert" style="margin-top: 5rem;">
                There Is No Offer History
            </div>
        </c:if>
    </c:when>

    <c:when test="${process eq 'updateQuote'}">
        <c:if test="${status eq 1}"> 
            <input type="hidden" id="inquiryid" value="${inquiryid}">
            <c:forEach items="${inquirylist}" var="i">
                <form id="quoteform">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="firstname">First Name :</label>
                                    <input type="text" class="form-control" value="${i.firstname}" readonly="true" name="firstname" tabindex="1" placeholder="Enter First Name" id="firstname">
                                </div>
                                <div class="form-group">
                                    <label style="display:block;" for="dob">DOB :</label>
                                    <input type="text" class="form-control" value="${i.dob}" readonly="true" name="dob" tabindex="3" style="width:97%; display:inline-block;" placeholder="Enter Date of Birth" id="dob">
                                </div>
                                <div class="form-group">
                                    <label for="state">State :</label>
                                    <select  class="form-control" readonly="true" name="state" tabindex="5" id="state">
                                        <option>${i.state}</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="pincode">Pincode :</label>
                                    <input type="text" class="form-control" value="${i.pincode}" readonly="true"  name="pincode" tabindex="7" placeholder="Enter Pincode" id="pincode">
                                </div>
                                <div class="form-group">
                                    <label for="roof" >Roof Material :</label>
                                    <c:if test="${i.roof eq 'WOODEN'}">
                                        <input type="radio"  tabindex="9" id="roof" name="roof" value="WOODEN" checked> Wooden
                                        <input type="radio" id="roof" name="roof" value="RCC"> RCC
                                    </c:if>
                                    <c:if test="${i.roof eq 'RCC'}">
                                        <input type="radio"  tabindex="9" id="roof" name="roof" value="WOODEN" > Wooden
                                        <input type="radio" id="roof" name="roof" value="RCC" checked> RCC
                                    </c:if>
                                </div>
                                <div class="form-group">
                                    <label for="safety" >Fire Safety :</label>
                                    <c:if test="${i.firesafety eq true}">
                                        <input type="radio" tabindex="11" id="safety" name="safety" value="1" checked> Yes
                                        <input type="radio" id="safety" name="safety" value="0"> No
                                    </c:if>
                                    <c:if test="${i.firesafety eq false}">
                                        <input type="radio" tabindex="11" id="safety" name="safety" value="1" > Yes
                                        <input type="radio" id="safety" name="safety" value="0" checked> No
                                    </c:if>
                                </div>
                            </div>


                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="lastname">Last Name :</label>
                                    <input type="text" class="form-control" tabindex="2" value="${i.lastname}" readonly="true" name="lastname" placeholder="Enter Last Name" id="lastname">
                                </div>
                                <div class="form-group">
                                    <label for="address">Address :</label>
                                    <input type="text" class="form-control" value="${i.address}" readonly="true" tabindex="4" name="address" placeholder="Enter Address" id="address">
                                </div>
                                <div class="form-group">
                                    <label for="city" >City :</label>
                                    <select tabindex="6" class="form-control"  readonly="true" name="city" id="city">
                                        <option>${i.city}</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="carpetarea">Carpet Area :</label>
                                    <input type="text" class="form-control" tabindex="8" value="${i.carpetarea}" name="carpetarea" placeholder="Enter Carpet Area" id="carpetarea">
                                </div>
                                <div class="form-group">
                                    <label for="floor" >Floor Material :</label>
                                    <c:if test="${i.floor eq 'WOODEN'}">
                                        <input type="radio" tabindex="10" id="floor" name="floor" value="WOODEN" checked> Wooden
                                        <input type="radio" id="roof" name="floor" value="TILE"> Tile
                                    </c:if>
                                    <c:if test="${i.floor eq 'TILE'}">
                                        <input type="radio" tabindex="10" id="floor" name="floor" value="WOODEN" > Wooden
                                        <input type="radio" id="roof" name="floor" value="TILE" checked> Tile
                                    </c:if>
                                </div>
                                <div class="form-group">
                                    <label for="builtyear">Built Year :</label>
                                    <input tabindex="12" type="text" value="${i.year}" class="form-control" name="builtyear" placeholder="Enter Built Year" id="builtyear">
                                </div>
                            </div>
                        </div>
                        <button type="button" tabindex="13" class="btn btn-primary" onclick="updateQuote();">Update Customize Quote</button>    
                    </div>

                </form>
            </c:forEach>
        </c:if>
        <c:if test="${status eq 0}">
            <div class="alert alert-danger container" role="alert" style="margin-top: 5rem;">
                Unable to update Quote
            </div>
        </c:if>
    </c:when>

    <c:when test="${process eq 'loadCity'}">

        <option>Select city</option>
        <c:forEach items="${cityList}" var="city">
            <option>${city.geo_name}</option>
        </c:forEach>
    </c:when>

    <c:when test="${process eq 'Insert'}">
        <c:if test="${status eq 1}">
            <div class="alert alert-success container" role="alert" style="margin-top: 5rem;">
                Successfully Inserted Inquiry
            </div>
        </c:if>
        <c:if test="${status eq 0}">
            <div class="alert alert-danger container" role="alert" style="margin-top: 5rem;">
                Problem Occured To Insert Inquiry
            </div>
        </c:if>
    </c:when>
    <c:when test="${process eq 'viewCoveragePremium'}">
        <c:if test="${status eq 1}">
            <c:forEach items="${coveragePremium}" var="i">
                <input type="hidden" id="cov-hidden" value="${i.coverage}">
                <input type="hidden" id="pre-hidden" value="${i.premium}">
            </c:forEach> 
        </c:if>
    </c:when>
    <c:when test="${process eq 'viewFnameLname'}">
        <c:if test="${status eq 1}">
            <c:forEach items="${fnameLnameList}" var="i">
                <input type="hidden" id="first-name-hidden" value="${i.firstname}">
                <input type="hidden" id="last-name-hidden" value="${i.lastname}">
                 <input type="hidden" id="max-cov-hidden" value="${i.maxcoverage}">
                <input type="hidden" id="min-cov-hidden" value="${i.mincoverage}">
                <input type="hidden" id="offer-id-hidden" value="${i.offerid}">
            </c:forEach> 
        </c:if>
    </c:when>
    <c:when test="${process eq 'loadPremium'}">
        <input value="${premium}" type="hidden" id="premiumFromAjax">              
    </c:when>
    <c:when test="${process eq 'updatedOffer'}">
        <c:if test="${status eq 1}">
            <div class="alert alert-success container" role="alert" style="margin-top: 5rem;">
                Offer Updated Successfully
            </div>
        </c:if>
    </c:when>
    <c:when test="${process eq 'updateInquiry'}">
        <c:if test="${status eq 1}">
            <div class="alert alert-success container" role="alert" style="margin-top: 5rem;">
                Inquiry Updated Successfully
            </div>
        </c:if>
    </c:when>    
                
    <c:otherwise>
        <div class="alert alert-secondary container" role="alert" style="margin-top: 5rem;">
               
            This is Awesome , we haven't think of it , get back to you soon
        
        </div>
    
    </c:otherwise>
</c:choose>
