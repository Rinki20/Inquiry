<%-- 
    Document   : home
    Created on : 01-Oct-2021, 3:43:01 PM
    Author     : njuser
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Progressive Home Insurance</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <!-- Popper JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        
<!--    validation call
        ajax call
        jquery ui function-->
        <script src="http://test.njindiainvest.com/finlibrary/resource/ajax.js"></script>
        <script src="http://test.njindiainvest.com/finlibrary/resource/common_functions.js"></script>
        <script src="http://test.njindiainvest.com/finlibrary/resource/validate.js"></script>
        
        <!--jquery link-->
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        
        <script src="js/quote.js" type="text/javascript"></script>
        
    </head>
    <body>      
        
        <ul class="nav nav-pills">
            <li class="nav-item">
                <a class="nav-link " href="#" onclick="GetQuoteLoader()">Get Quote</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" onclick="ChangeCoverageLoader()">Change the Coverage</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" onclick="ViewOfferLoader()">View Offer</a>
            </li>
            <li class="nav-item">
              <a class="nav-link " href="#" onclick="ReportLoader()">Report</a>
            </li>
        </ul>
        <div id="load">   
        </div>
        

    </body>
</html>
