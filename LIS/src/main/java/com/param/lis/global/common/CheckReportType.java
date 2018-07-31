package com.param.lis.global.common;

import java.util.ArrayList;
import java.util.List;

import com.param.lis.common.dto.CommonDto;
import com.param.lis.transaction.dto.LabResultDetailsViewDto;

public class CheckReportType implements ITransactionConstants {
	
	

	public static LabResultDetailsViewDto reportType(LabResultDetailsViewDto labResultDetailsViewDto) 
	{
	   if(labResultDetailsViewDto!=null)
	   {
		if(labResultDetailsViewDto.getRefrangeTypeId()==REFERENCE_VALUE)
		{
		  Double result = new Double(labResultDetailsViewDto.getFinalResult());
	        if (result >= labResultDetailsViewDto.getParameterMin().doubleValue()
	                    && result <= labResultDetailsViewDto.getParameterMax().doubleValue()) 
	            {
	                labResultDetailsViewDto.setResultTypeFlag('N');
	                labResultDetailsViewDto.setReportType(NORMAL_REPORT);
	                
	            } else if (result <= labResultDetailsViewDto.getParamAbnrmlMin().doubleValue()
	                    || result >= labResultDetailsViewDto.getParamAbnrmlMax().doubleValue()) 
	            {
	                labResultDetailsViewDto.setResultTypeFlag('C');
	                labResultDetailsViewDto.setReportType(CRITICAL_REPORT);
	            } else if ((result < labResultDetailsViewDto.getParameterMin().doubleValue()
	                    && result > labResultDetailsViewDto.getParamAbnrmlMin().doubleValue())) 
	            {
	                labResultDetailsViewDto.setResultTypeFlag('L');
	                labResultDetailsViewDto.setReportType(ABNORMAL_REPORT);
	            } 
	            else if ((result > labResultDetailsViewDto.getParameterMax().doubleValue()
	                    && result < labResultDetailsViewDto.getParamAbnrmlMax().doubleValue())) 
	            {
	                labResultDetailsViewDto.setResultTypeFlag('H');
	                labResultDetailsViewDto.setReportType(ABNORMAL_REPORT);
	            } 
	        return labResultDetailsViewDto; 
		}
		else if(labResultDetailsViewDto.getRefrangeTypeId()==TEXTUAL_RANGE)
		{
		   if(labResultDetailsViewDto.getFinalResult().equals(labResultDetailsViewDto.getTextualRangeName()))
		       {
		         labResultDetailsViewDto.setResultTypeFlag('N');
                 labResultDetailsViewDto.setReportType(NORMAL_REPORT);
		       }
		   else 
		   {
		     labResultDetailsViewDto.setResultTypeFlag('H');
             labResultDetailsViewDto.setReportType(ABNORMAL_REPORT);  
		   }
		   return labResultDetailsViewDto;
		}
		else if(labResultDetailsViewDto.getRefrangeTypeId()==MULTIVALUED_RANGE) {
		  labResultDetailsViewDto.setResultTypeFlag('N');
          labResultDetailsViewDto.setReportType(NORMAL_REPORT);
          return labResultDetailsViewDto;
		}
	   }
		return null;
	}
	
	
	public static List<CommonDto> getTemplateType(){
		
		 final List<CommonDto> listLabTemplateMasterDto = new ArrayList<CommonDto>();
		 CommonDto commonDto1=new CommonDto();
		 commonDto1.setId(1);
		 commonDto1.setName("Dama concent");
		 CommonDto commonDto2=new CommonDto();
		 commonDto2.setId(2);
		 commonDto2.setName("Certificates");
		 
		 CommonDto commonDto3=new CommonDto();
		 commonDto3.setId(3);
		 commonDto3.setName("Cause of death certificates");
		 
		 CommonDto commonDto4=new CommonDto();
		 commonDto4.setId(4);
		 commonDto4.setName("Short leave certificates");
		 
		 CommonDto commonDto5=new CommonDto();
		 commonDto5.setId(5);
		 commonDto5.setName("Histopath report");
		 
		 CommonDto commonDto6=new CommonDto();
		 commonDto6.setId(6);
		 commonDto6.setName("Discharge Sleep");
		 
		 CommonDto commonDto7=new CommonDto();
		 commonDto7.setId(7);
		 commonDto7.setName("Discharge Summery");
		 
		 CommonDto commonDto8=new CommonDto();
		 commonDto8.setId(8);
		 commonDto8.setName("Examination");
		 
		 CommonDto commonDto9=new CommonDto();
		 commonDto9.setId(9);
		 commonDto9.setName("Consent Form");
		 
		 CommonDto commonDto10=new CommonDto();
		 commonDto10.setId(10);
		 commonDto10.setName("Sick Note");
		 
		 CommonDto commonDto11=new CommonDto();
		 commonDto11.setId(11);
		 commonDto11.setName("Fitness Certificate");

		/* CommonDto commonDto8=new CommonDto();
		 commonDto2.setId(2);
		 commonDto2.setName("Certificates");
		 
		 CommonDto commonDto9=new CommonDto();
		 commonDto2.setId(2);
		 commonDto2.setName("Certificates");*/
		 
		 listLabTemplateMasterDto.add(commonDto1);
		 listLabTemplateMasterDto.add(commonDto2);
		 listLabTemplateMasterDto.add(commonDto3);
		 listLabTemplateMasterDto.add(commonDto4);
		 listLabTemplateMasterDto.add(commonDto5);
		 listLabTemplateMasterDto.add(commonDto6);
		 listLabTemplateMasterDto.add(commonDto7);
		 listLabTemplateMasterDto.add(commonDto8);
		 listLabTemplateMasterDto.add(commonDto9);
		 listLabTemplateMasterDto.add(commonDto10);
		 listLabTemplateMasterDto.add(commonDto11);
		/* listLabTemplateMasterDto.add(commonDto8);
		 listLabTemplateMasterDto.add(commonDto9);
		 */
		
		return listLabTemplateMasterDto;
		
		 
	}
	
	
}
