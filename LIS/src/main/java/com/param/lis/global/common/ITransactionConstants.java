
package com.param.lis.global.common;

import java.util.Arrays;
import java.util.List;

/** @author Ganesh Chaudhari **/
public interface ITransactionConstants
{

	boolean isRejected = false;
	
	Character ACCEPT ='A';
	Character REJECT ='R';
			
	Integer ORDER_REMAINING = 1;
	Integer ORDER_COMPLETED = 2;
	Integer ORDER_NEW = 0;

	/** Test Sample Status Constants */
	Integer TEST_ORDERED = 1;
	Integer SAMPLE_COLLECTED = 2;
	Integer SAMPLE_RECEIVED = 3;
	Integer SAMPLE_RECOLLECTED = 4;
	Integer SAMPLE_UNDER_PROCESSING = 5;
	Integer RESULT_ENTRY_DONE = 6;
	Integer RESULT_VALIDATION_DONE = 17;
	Integer RETESTING = 7;
	Integer REPORT_RELEASED = 8;
	Integer PARTIAL_REPORT_RELEASED = 9;
	Integer PROVISIONAL_REPORT_RELEASED = 10;
	Integer FINAL_REPORT_RELEASED = 11;
	Integer SAMPLE_SEND_TO_CR = 12;
	Integer SAMPLE_SEND_TO_DEPT = 13;
	Integer SAMPLE_ACCEPTED = 14;
	Integer SAMPLE_REJECTED = 15;
	Integer SEND_TO_REPORT_ENTRY = 16;
	Integer OP_IP_ACCEPTED = 19;
	Integer OP_IP_REJECTED = 20;
	Integer SPECIMAN_ACCEPTED = 21;
	Integer GROSS_CREATED = 22;
	Integer BLOCK_CREATED = 23;
	Integer SLIDE_CREATED = 24;
	Integer OUT_SOURCE_SAMPLE = 25;
	
	
	
	/**
	  Microbiology Status   
     */
	 
	Integer SEND_FOR_INCUBATION_OBERVATION =26 ;
	Integer DURING_OBJSERVATION = 27;
	Integer INCUBATION_OBJSERVATION_COMPLETED=28 ;
	Integer MACROSCOPIC_EXAMINATION=29 ;
	Integer MACROSCOPIC_EXAMINATION_COMPLETED= 30;
	Integer DURING_BIOCHEMICAL_RESULT_ENTRY=31 ;
	Integer BIOCHEMICAL_RESULT_ENTRY_DONE=32 ;
	Integer MICROSCOPIC_EXAMINATION=33 ;
	Integer RELEASE_FOR_SENSITIVITY_ENTRY = 18;
	Integer SENSITIVITY_TEST = 34;
	Integer SENSITIVITY_TEST_CHECK = 35;

	
	List<Integer> COLLECTED_SAMPLE_STATUS   =  Arrays.asList(SAMPLE_COLLECTED);
	List<Integer> REJECTED_SAMPLE_IDS   =  Arrays.asList(OP_IP_REJECTED,SAMPLE_REJECTED);
	List<Integer> bioChemStatus = Arrays.asList(SAMPLE_ACCEPTED,RETESTING);
	List<Integer> MICRO_INCUBATION_SAMPLE_STATUS_IDS = Arrays.asList(SEND_FOR_INCUBATION_OBERVATION,DURING_OBJSERVATION);
	List<Integer> MACRO_EXAMINATION_SAMPLE_STATUS_IDS = Arrays.asList(MACROSCOPIC_EXAMINATION,SEND_FOR_INCUBATION_OBERVATION,INCUBATION_OBJSERVATION_COMPLETED);
	List<Integer> BIOCHEMICAL_SAMPLE_STATUS_IDS = Arrays.asList(MACROSCOPIC_EXAMINATION_COMPLETED,DURING_BIOCHEMICAL_RESULT_ENTRY);
	List<Integer> MICROSCOPI_EXA_SAMPLE_STATUS_IDS = Arrays.asList(BIOCHEMICAL_RESULT_ENTRY_DONE,MICROSCOPIC_EXAMINATION);
	
	List<Integer> SENSITIVITY_SAMPLE_STATUS_IDS = Arrays.asList(RELEASE_FOR_SENSITIVITY_ENTRY,SENSITIVITY_TEST,SENSITIVITY_TEST_CHECK);
	
	
	List<Integer> HISTO_MICROEXA_STATUS = Arrays.asList(PARTIAL_REPORT_RELEASED,SLIDE_CREATED);
	List<Integer> OUT_SOURCED_STATUS = Arrays.asList(SAMPLE_SEND_TO_DEPT,OP_IP_ACCEPTED);
	
	
	
	
	
   // Paicent Arrival Constants
	
	Character IS_OUT_SOURCE ='N';

	// Visit Type Constants
	Integer OP = 1;
	Integer IP = 2;
	Integer DC = 3;
	Integer ER = 4;
	
	//TEST_TYPES
	Integer SINGLE_PARAM_TEST = 0;
	Integer MULTY_PARAM_TEST = 1;
	
	Character YES = 'Y';
	Character NO = 'N';
	
	//AGE-TYPE -CONSTANTS
	Integer DAY= 1;
	Integer MONTHS=2;
	Integer YEAR= 3;
	
	//AGE-TYPE -FROM CONSTANS
	Integer DAYS= 1;
	Integer MONTHS_TO_DAYS=30;
	Integer YEAAR_TO_DAYS=365;
		
	//AGE-TYPE -FROM CONSTANS
	String DAYS_TO_STRING= "Day";
	String MONTHS_TO_STRING= "Months";
	String YEAAR_TO_STRING="Years";
	
	//Patient Arrival
	Integer PATIRNT_NOT_ARRIVAL= 1;
	Integer PATIRNT_ARRIVAL=2;
	Integer REPAT_PATIRNT=3;
	
	//Report Constants
	Integer NORMAL_REPORT= 1;
	Integer ABNORMAL_REPORT = 2;
	Integer CRITICAL_REPORT =3;
	
	
	//Order Status 
	Integer PENDING = 1;
	Integer INPROCESS = 2;
	Integer RENDER = 3;
	Integer CANCEL = 4;
	List<Integer> RENDER_CANCEL = Arrays.asList(RENDER,CANCEL);
	
	//LAB_DEPARTMENTS 
	Integer LAB_DEPT = 8;
	
	//LAB_SUB_DEPARTMENTS 
	Integer BIOCHEMISTRY_DEPT = 5;
	Integer HEMATOLOGY_DEPT = 6;
	Integer MICROBIOLOGY_DEPT = 8;
	Integer HISTOPATHOLOGY_DEPT = 9;

	List<Integer> LAB_SUB_DEPTS = Arrays.asList(BIOCHEMISTRY_DEPT,HEMATOLOGY_DEPT,MICROBIOLOGY_DEPT,HISTOPATHOLOGY_DEPT);
	List<Integer> BIOCHEMISTRY_HEMATOLOGY_DEPT_IDS = Arrays.asList(BIOCHEMISTRY_DEPT,HEMATOLOGY_DEPT);
	
	
	   List<Integer> LAB_SUB_DEPTS_ORDER = Arrays.asList(BIOCHEMISTRY_DEPT,HEMATOLOGY_DEPT,MICROBIOLOGY_DEPT);
	
	/**Incubation During Objservation Transaction*/
    Integer IncubationFlag = 1;
    Integer MacroscopicExaminationFlag = 2;
    Integer MicroscopicExaminationFlag = 3;
    
    String HISTOPATHLOGY_PREFIX = "HN";
    String SUB_SPECIMEN_PREFIX = "SP";
    String GROSS_PREFIX = "GN";
    String BLOCK_PREFIX = "BN";
    String SLIDE_PREFIX = "SL";
    Integer NUMBER_WIDTH = 8;
    
    Integer REFERENCE_VALUE = 1;
    Integer TEXTUAL_RANGE = 2;
    Integer MULTIVALUED_RANGE = 3;
	
	
}
