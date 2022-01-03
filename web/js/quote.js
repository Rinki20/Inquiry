/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function GetQuoteLoader()
{
//   alert("I am clicked");

    getSynchronousData('home.fin?cmdAction=loadGetQuote', '', 'load');
    loadDatePicker('dob','65','18','-65Y','-18Y');
}
function ChangeCoverageLoader()
{
    getSynchronousData('home.fin?cmdAction=loadChangeCoverage', '', 'load');
}
function ViewOfferLoader()
{
    //
    getSynchronousData('home.fin?cmdAction=loadViewOffer', '', 'load');
}
function ReportLoader()
{
    //
    
    getSynchronousData('home.fin?cmdAction=loadReport', '', 'load');
}

//validation for form 
function validateQuoteForm()
{

    var quoteform = document.getElementById("quoteform");
    var result = validate_ind(quoteform, 'firstname', 'First Name', true) && 
            validate_length(quoteform,'firstname', 30 , 'First Name') &&
            validate_ind(quoteform, 'lastname', 'Last Name', true) &&
            validate_length(quoteform,'lastname', 30 , 'Last Name') &&
            validate_address(quoteform, 'address', 'Address', true, 100) &&
            validate_dropdown(quoteform,'state','State',true) &&
            validate_dropdown(quoteform,'city','City',true) &&
            validate_pincode(quoteform, 'pincode', 'Pincode', "no", true) &&
            validate_only_number(quoteform, 'carpetarea', 'Carpetarea', true) &&
            validate_carpetarea(quoteform,'carpetarea','Carpetarea') &&
            validate_only_number(quoteform, 'builtyear', 'Built Year', true) &&
            validate_dateRange(quoteform,'builtyear','Built Year');

        var formData = getFormData(quoteform);

    
    if (result)
    {

        //call InsertInquiry controller to insert data 
        //alert(formData);
        getSynchronousData("home.fin?cmdAction=insertInquiry", formData, 'load');

//        var status = document.getElementById('status').value;
//
//        if (status > 0)
//        {
//            alert("Inserted inquiry Successfully" + status);
//        } else
//        {
//            alert("problem occured in inserting inquiry" + status);
//        }

    } else
    {
        return true;
    }
}
// this function will call loadCity in InquiryController
function CityLoader()
{
    var state = document.getElementById("state").value;
    getSynchronousData('home.fin?cmdAction=loadCity', "state=" + state, 'city');
}
// this function will call inquiry information based on button click 

function GenerateOffer(inquiryid)
{
//    alert(inquiryid.toString());
    getSynchronousData("home.fin?cmdAction=generateOffer", "inquiryid=" + inquiryid, 'load');
}
function ViewOffer(inquiryid)
{
//    alert(inquiryid.toString());
    getSynchronousData("home.fin?cmdAction=viewOffer", "inquiryid=" + inquiryid, 'load');
}

//it will call when inquiry id selected from change coverage page


function getCoverageAndPremium()
{

    var inquiryid = document.getElementById("inquiryid").value;
    var btnOfferUpdate = document.getElementById('btn-offer-update');
    var btnQuotesUpdate = document.getElementById('btn-quote-update');
    var coverage = document.getElementById("coverage");
    var premium = document.getElementById("premium");
    var fname = document.getElementById("fname");
    var lname = document.getElementById("lname");

    if (inquiryid === "Select Inquiry ID")
    {
        btnOfferUpdate.disabled = true;
        btnQuotesUpdate.disabled = true;
        coverage.value = '';
        premium.value = '';
        fname.value = '';
        lname.value = '';
        

    } else {
        getSynchronousData('home.fin?cmdAction=loadCoverageAndPremium', "inquiryid=" + inquiryid, 'hidden');
        coverage.value = document.getElementById('cov-hidden').value;
        premium.value = document.getElementById('pre-hidden').value;
        getSynchronousData('home.fin?cmdAction=loadFnameLname', "inquiryid=" + inquiryid, 'hidden1');

        fname.value = document.getElementById('first-name-hidden').value;
        lname.value = document.getElementById('last-name-hidden').value;

        btnQuotesUpdate.disabled = false;
//        btnOfferUpdate.disabled = false;

    }


//    alert(inquiryid);



}

//it will called when coverage change on input
 
function changePremium()
{
  
    var btnOfferUpdate = document.getElementById('btn-offer-update');
    var inquiryid = document.getElementById("inquiryid").value;
    var coverage = document.getElementById("coverage").value.trim();
    var premium = document.getElementById("premium");
    var maxcov = parseInt(document.getElementById('max-cov-hidden').value);
    var mincov = parseInt(document.getElementById('min-cov-hidden').value);
    
    
    if ((coverage >= mincov) && (coverage <= maxcov))
    {
//        alert(inquiryid);
//        alert(coverage);
        getSynchronousData('home.fin?cmdAction=changePremium', 'inquiryid=' + inquiryid + '&coverage=' + coverage, 'hidden2');
        
        var premiumFromAjax = document.getElementById("premiumFromAjax");
        premium.value = premiumFromAjax.value;
        btnOfferUpdate.disabled = false;
        
    } else
    {
        document.getElementById("coverage").value = document.getElementById('cov-hidden').value;
        alert("please enter value between " + mincov + "and " + maxcov);
        btnOfferUpdate.disabled = true;
    }

}
function OfferUpdate()
{
    var coverage = document.getElementById("coverage").value.trim();
    var premium = document.getElementById("premium").value.trim();
    var inquiryid = document.getElementById("inquiryid").value;
    var offerid = document.getElementById("offer-id-hidden").value;
//    alert(offerid);
        
        getSynchronousData('home.fin?cmdAction=OfferUpdate', 'inquiryid=' + inquiryid + '&offerid=' + offerid + '&coverage=' + coverage + '&premium=' + premium, 'load');
    
    
}


function UpdateQuoteLoader()
{
    var inquiryid = document.getElementById("inquiryid").value;
    
    getSynchronousData('home.fin?cmdAction=loadUpdateQuote', 'inquiryid=' + inquiryid, 'load');

}

function updateQuote()
{
    var inquiryid = document.getElementById("inquiryid").value;
    var quoteform = document.getElementById("quoteform");
    var result = validate_only_number(quoteform, 'carpetarea', 'Carpetarea', true) &&
                 validate_only_number(quoteform, 'builtyear', 'Built Year', true) &&
                 validate_dateRange(quoteform,'builtyear','Built Year');

    var formData = getFormData(quoteform);

//    alert(formData);
    if (result)
    {

        //call InsertInquiry controller to insert data 

        getSynchronousData("home.fin?cmdAction=updateInquiry", formData + '&inquiryid='+inquiryid, 'load');

        var status = document.getElementById('status').value;

        if (status > 0)
        {
            alert("Inserted inquiry Successfully" + status);
        } else
        {
            alert("problem occured in inserting inquiry" + status);
        }

    } else
    {
        return true;
    }

}

function offerDetail(inquiryid)
{
   
   getSynchronousData("home.fin?cmdAction=loadOfferDetail",'inquiryid='+inquiryid,'load-modal');
   
   
   const historyModal = new bootstrap.Modal(document.getElementById(inquiryid));
    historyModal.show(); 
    
}

function validate_length(frm,fieldname,length,caption_name)
{
    const fieldvalue = frm.elements[fieldname].value;
    const len = fieldvalue.length;
    
    if(len <= length)
        return  true;
    
    alert("please Enter value less than " + length + "for " + caption_name);
    frm.elements[fieldname].focus();
    return false;
}

function validate_carpetarea(frm,fieldname,caption_name)
{
    const fieldvalue = frm.elements[fieldname].value;
    if(fieldvalue >= 30)
        return true;
    alert ("please Enter value greater than or equal to 30 for " + caption_name);
    frm.elements[fieldname].focus();
    return false;
    
}
function validate_dateRange(frm,fieldname,caption_name)
{
   const fieldvalue = frm.elements[fieldname].value;
   const d = new Date();
   let year = d.getFullYear();
   const diffYear = year - fieldvalue;
   if(diffYear >=0 && diffYear <=25)
       return true;
  
    alert("Enter value between " + (year-25) + " and " + year);
    return false;      
}