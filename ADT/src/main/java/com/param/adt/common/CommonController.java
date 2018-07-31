/*package com.param.adt.common;

import java.io.File;
import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.param.adt.master.globle.dao.IOrganizationUnitUserMapperDao;
import com.param.adt.master.globle.service.ILoginService;
import com.param.adt.master.globle.service.IOrganizationMasterService;
import com.param.adt.master.globle.service.IUnitMasterService;


@Controller
@SessionAttributes("sessionObject")
public class CommonController implements IRoleMasterConstants{

	@Autowired
	public SessionObject sessionObject;
	@Autowired
	private ILoginService iLoginService;
	@Autowired
	private IUnitMasterService iUnitMasterService;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private IOrganizationMasterService iHospitalMasterService;
	
	@RequestMapping(value= "/")
	public String getloginPage(Model model){
		try{
		String message = (String)model.asMap().get("message");
		if(message != null ){ model.addAttribute("message", message);}
		model.addAttribute("listUnit", iUnitMasterService.getUnitMasterList('A').getListObject());
		model.addAttribute("hospitalList", iHospitalMasterService.getOrganizationMasterList().getListObject());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "common/loginPage";
	}
	@RequestMapping(value= "doctor-loginpage")
	public String getLogin_Page(){
		return "common/doctor-loginpage";
	}
	
	@RequestMapping(value= "/homePage", method = RequestMethod.GET)
	public String gethomePage(Model model,final RedirectAttributes redirectAttributes){
	String rpage = "/";
	try{
	String doctorUpdateMsg = (String)model.asMap().get("doctorUpdateMsg");
	if(doctorUpdateMsg != null){
		redirectAttributes.addFlashAttribute("doctorUpdateMsg", doctorUpdateMsg);
	}
	Integer roleId = sessionObject.getRoleMasterId();
	rpage = roleId == ADMIN ? "adminLandingPage" : roleId == DOCTOR ? "doctorLandingPage" : roleId == BILLING ? "billing_landing_page" : roleId == RECEPTION ?  "receptionLandingPage" : roleId == PATIENT ? "doctorSlots" : roleId == NURSE ? "nurseAppointmentList" : "/" ;
	}catch(Exception e){
		e.printStackTrace();
	}
	return "redirect:/" + rpage;
	}
	
	@RequestMapping(value= "/appointmentList")
	public String getAppointmentPage(){
		return "appointment/appointment-list";
	}
	
	
	@RequestMapping(value= "/appointments")
	public String getAppointments(){
		return "appointment/appointments";
	}
	@RequestMapping(value= "/search_appointments")
	public String getsearchAppointments(){
		return "appointment/search_appointments";
	}
	
	@RequestMapping(value= "/patientRegistration")
	public String getpatientRegistration(){
		return "patient/patient-registration";
	}
	@RequestMapping(value= "/patient-cover-page")
	public String getpatientcoverpage(){
		return "patient/patient-cover-page";
	}
	
	
	@RequestMapping(value= "/patient-new-page")
	public String getpatientnewpage(){
		return "patient/patient-new-page";
	}
	
	@RequestMapping(value= "/patient-details")
	public String getpatientdetails(){
		return "patient/patient-cover-home-page";
	}
	
	@RequestMapping(value= "/patientDetailsList")
	public String getpatientdetailsList(){
		return "patient/patient-details-list";
	}
	
	@RequestMapping(value= "/doctorRegistration")
	public String getdoctorRegistration(){
		return "doctor/doctor-registration";
	}
	
	@RequestMapping(value= "/doctorDetailsList")
	public String getDoctorDetailsList(){
		return "doctor/doctor-details-list";
	}
	
	
	@RequestMapping(value= "/doctorSlots")
	public String getdoctorSchedule(){
		return "doctor/doctor-slots";
	}
	@RequestMapping(value= "/doc-patient-list")
	public String getdoctorlandingpage(){
		return "doctor/doc-patient-list";
	}
	
	@RequestMapping(value="/imageScanner")
	public String imageScanner(){
		return "common/imageScanner";
	}
	
	@RequestMapping(value="/ServiceMaster")
	public String ServiceMaster(){
		return "master/ServiceMaster";
	}	
	@RequestMapping(value="/role-config")
	public String roleconfig(){
		return "master/role-config";
	}	
	*//**************** bank master ******************//*
	@RequestMapping(value="/bankMaster")
	public String bankMaster(){
		return "master/bank-master";
	}
	*//**************** income Category Master ******************//*
	@RequestMapping(value="/incomeCategoryMaster")
	public String incomeCategoryMaster(){
		return "master/global/income-category-master";
	}
	*//**************** Employment Status Master ******************//*
	@RequestMapping(value="/employmentStatusMaster")
	public String employmentStatusMaster(){
		return "master/global/employment-status-master";
	}
	
	*//**************** prescription Details Page controller 14july2017 ******************//*
	@RequestMapping(value="/prescriptionDetails")
	public String prescriptionDetails(){
		return "patient/prescription-details-page";
	}
	*//**************** order Details Page controller 14july2017 ******************//*
	@RequestMapping(value="/orderDetails")
	public String orderDetails(){
		return "patient/order-details-page";
	}

	*//******************************* examination template added on 30-aug-2017 by pranita*******************************//*
	@RequestMapping(value="/insomniaSeverityIndexTemplate")
	public String insomniaSeverityIndexTemplate(){
		return "template/insomnia_severity_index_template";
		
	}
*//******************************* error pages controller start*******************************//*	
	@RequestMapping(value="/error400Page")
	public String error400page(){
		return "common/error-400-page";
	}	
	@RequestMapping(value="/error404Page")
	public String error404page(){
		return "common/error-404-page";
	}	
	@RequestMapping(value="/error405Page")
	public String error405page(){
		
		return "common/error-405-page";
	}	
	@RequestMapping(value="/error500Page")
	public String error500page(){
		return "common/error-500-page";
	}	
	@RequestMapping(value="/error503Page")
	public String error503page(){
		return "common/error-503-page";
	}	
	@RequestMapping(value="/pathalogyDIV")
	public String pathalogyDIV(){
		return "doctor/pathalogyDIV";
	}
	@RequestMapping(value="/microbiologyDIV")
	public String microbiologyDIV(){
		return "doctor/microbiologyDIV";
	}
	@RequestMapping(value="/radiologyDIV")
	public String radiologyDIV(){
		return "doctor/radiologyDIV";
	}
	@RequestMapping(value="/departmentMaster")
	public String departmentMaster(){
		return "master/service/departmentMaster";
	}
	@RequestMapping(value="/departmentUnitMaster")
	public String departmentUnitMaster(){
		return "master/service/unitDepartmentMapper";
	}
	
	*//******************************* error pages controller end*******************************//*
	//------ reschedule appointment page controller start------//
	@RequestMapping(value="/rescheduleAppointment")
	public String rescheduleAppointment(){
		return "appointment/reschedule-appointment";
	}
	//------ reschedule appointment page controller end -------//
	//--------------------------------------------------------//
	
	@RequestMapping(value="/info")
	public String info(Model model){
		//------ get time zone ---------//
		Calendar cal = Calendar.getInstance();
    	long milliDiff = cal.get(Calendar.ZONE_OFFSET);
    	// Got local offset, now loop through available timezone id(s).
    	String [] ids = TimeZone.getAvailableIDs();
    	String name = null;
    	for (String id : ids) {
    		//System.out.println(id);
    	  TimeZone tz = TimeZone.getTimeZone(id);
    	  if (tz.getRawOffset() == milliDiff) {
    	    // Found a match.
    	    name = id;
    	    break;
    	  }
    	}
    	System.out.println("My Time zone="+name);
    	model.addAttribute("timeZone", name);
    	//-------- real path --------//
    	model.addAttribute("realPath", servletContext.getRealPath("/"));
		
    	//-------- create diectory --------//


File theDir = new File("home" + File.separator + "hello");

// if the directory does not exist, create it
if (!theDir.exists()) {
    String directoryName = "/home/hello";
	System.out.println("creating directory: " + directoryName );
    boolean result = false;

    try{
        theDir.mkdir();
        result = true;
    } 
    catch(SecurityException se){
        //handle it
    	model.addAttribute("exception", se.getMessage());
    }        
    if(result) {    
        System.out.println("DIR created"); 
        model.addAttribute("DIR", "DIR created");
    }
}else{
	System.out.println("already exist");
	model.addAttribute("DIR", "already exist");
}


    	return "info";
	}
	
}
*/