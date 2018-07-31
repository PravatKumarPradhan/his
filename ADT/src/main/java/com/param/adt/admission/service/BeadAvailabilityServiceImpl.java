package com.param.adt.admission.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.admission.dao.IBedAvailabilityDao;
import com.param.adt.admission.dto.BedAvailibailityDto;
import com.param.adt.common.ADTCommonDateUtils;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class BeadAvailabilityServiceImpl implements IBedAvailibilityService,ICommonConstants
{
	
	@Autowired
	IBedAvailabilityDao iBedAvailabilityDao;
	
	@Override
	public Response bedAvailiblityList(BedAvailibailityDto bedAvailibailityDto) throws ApplicationException {
		try{
			System.out.println(bedAvailibailityDto.getDate().substring(0, 10));
			
			List<String> dates=new ArrayList<String>();
			dates.add(bedAvailibailityDto.getDate());
			String sDate1=bedAvailibailityDto.getDate();  
			Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);  
			Calendar cal = Calendar.getInstance();
			cal.setTime(date1);

			for(int i = 1; i< 7; i++)
			{
				cal.add(Calendar.DAY_OF_YEAR,1);
				String d=ADTCommonDateUtils.getAvalDate(cal.getTime(), "yyyy-MM-dd");
				dates.add(d);
			}
			bedAvailibailityDto.setStartDate(dates.get(0));
			bedAvailibailityDto.setEndDate(dates.get(6));
			
			Response res = iBedAvailabilityDao.bedAvailiblityList(bedAvailibailityDto);
			
			ArrayList finalList=new ArrayList();
			finalList.add(dates);
			finalList.add(res.getListObject());
			
			return new Response(SUCCESS, null, null, finalList, null);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
