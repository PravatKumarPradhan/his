package com.param.lis.histopathology.transaction.service;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.entity.lis.transaction.LabSampleMaster;
import com.param.lis.global.common.CommonUtils;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dao.IHistoMacroScopicExaminationDao;
import com.param.lis.histopathology.transaction.dao.ISpecimanReceiptDao;
import com.param.lis.histopathology.transaction.dto.TSpecimanMasterDto;
import com.param.lis.histopathology.transaction.dto.specimanReceiptDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class SpecimanReceiptServiceImpl implements ISpecimanReceiptService, ICommonConstants, IError,ITransactionConstants
{

	@Autowired
	ISpecimanReceiptDao iSpecimanReceiptDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 @Autowired 
	 private IHistoMacroScopicExaminationDao iHistoMicroScopicExaminationDao;

	@Override
	@Transactional
	public Response listSpecimanReceipt(specimanReceiptDto specimanReceiptDto) throws ApplicationException
	{
		try
		{
			return iSpecimanReceiptDao.listSpecimanReceipt(specimanReceiptDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getTotalSpecimanReceiptRecord(specimanReceiptDto specimanReceiptDto) throws ApplicationException
	{
		try
		{
			return iSpecimanReceiptDao.getTotalSpecimanReceiptRecord(specimanReceiptDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response collectionSpeciman(List<specimanReceiptDto> listspecimanReceiptDto) {
		try
		{
			if(!listspecimanReceiptDto.isEmpty())
			{
				for (Iterator iterator = listspecimanReceiptDto.iterator(); iterator.hasNext();) 
				{
					specimanReceiptDto specimanReceiptDto = (specimanReceiptDto) iterator.next();
					LabSampleMaster labSampleMaster = new LabSampleMaster();
					LabSampleDetailsMaster labSampleDetailsMaster = new LabSampleDetailsMaster();
					List<LabSampleDetailsMaster> listLabSampleDetailsMaster = new ArrayList<>();
					
					labSampleMaster.setOrgId(specimanReceiptDto.getOrgId());
					labSampleMaster.setOrgUnitId(specimanReceiptDto.getOrgUnitId());
					labSampleMaster.setOrderId(specimanReceiptDto.getOrderId());
					labSampleMaster.setVisitAdmId(specimanReceiptDto.getVisitAdmId());
					labSampleMaster.setCreatedBy(specimanReceiptDto.getCreatedBy());
					labSampleMaster.setCreatedDate(specimanReceiptDto.getCreatedDate());
					labSampleMaster.setPatientId(specimanReceiptDto.getPatientId());
					labSampleMaster.setVisitTypeId(specimanReceiptDto.getVisitTypeId());
					
					
					
					BigInteger sampleNo = (BigInteger) sessionFactory.getCurrentSession().getNamedQuery("GENERATE_SAMPLE_NO").uniqueResult();
					
					labSampleDetailsMaster.setCreatedDate(specimanReceiptDto.getCreatedDate());
					labSampleDetailsMaster.setPatientVisitAge(specimanReceiptDto.getCreatedDate());
					labSampleDetailsMaster.setSampleNo(sampleNo);
					
					labSampleDetailsMaster.setSampleRecollectFlag('N');
					labSampleDetailsMaster.setTestId(specimanReceiptDto.getTestId());
					labSampleDetailsMaster.setDeptId(specimanReceiptDto.getDeptId());
					labSampleDetailsMaster.setSubDeptId(specimanReceiptDto.getSubDeptId());
					labSampleDetailsMaster.setCreatedBy(specimanReceiptDto.getCreatedBy());
					labSampleDetailsMaster.setOrgId(specimanReceiptDto.getOrgId());
					labSampleDetailsMaster.setOrgUnitId(specimanReceiptDto.getOrgUnitId());
					labSampleDetailsMaster.setOrderId(specimanReceiptDto.getOrderId());
					labSampleDetailsMaster.setOrderDetailsId(specimanReceiptDto.getOrderDetailsId());
					labSampleDetailsMaster.setPackageId(specimanReceiptDto.getPackageId());
					labSampleDetailsMaster.setSampleTypeId(specimanReceiptDto.getSampleId());
					labSampleDetailsMaster.setContainerId(specimanReceiptDto.getContainerId());
					labSampleDetailsMaster.setSampleReqCount(0);
					labSampleDetailsMaster.setSamplePendingCount(0);
					labSampleDetailsMaster.setIsOutsourced(specimanReceiptDto.getIsOutsourced());
					labSampleDetailsMaster.setPriorityId(specimanReceiptDto.getPriorityId());
					labSampleDetailsMaster.setPatientId(specimanReceiptDto.getPatientId());
					labSampleDetailsMaster.setDoctorId(specimanReceiptDto.getDoctorId());
					labSampleDetailsMaster.setGenderId(specimanReceiptDto.getGenderId());
					
					listLabSampleDetailsMaster.add(labSampleDetailsMaster);
					labSampleMaster.setListLabSampleDetailsMaster(listLabSampleDetailsMaster);
					
					Response specimanRes =  iSpecimanReceiptDao.collectionSpeciman(labSampleMaster);
					if(specimanRes.getCode().equals(SUCCESS_200) && specimanRes.getObject()!=null)
					{ 
						LabSampleDetailsMaster labSampleDetailsMst = (LabSampleDetailsMaster) specimanRes.getObject();
						
						TSpecimanMasterDto tSpecimanMasterDto = new TSpecimanMasterDto();
						tSpecimanMasterDto.setOrgId(specimanReceiptDto.getOrgId());
						tSpecimanMasterDto.setOrgUnitId(specimanReceiptDto.getOrgUnitId());
						tSpecimanMasterDto.setSpecimanId(specimanReceiptDto.getSpecimanId());
						tSpecimanMasterDto.setSpecimanTypeId(specimanReceiptDto.getSpecimanTypeId());
						tSpecimanMasterDto.setSpecimanName(specimanReceiptDto.getSpecimanName());
						tSpecimanMasterDto.setIsDeleted('N');
						tSpecimanMasterDto.setCreatedBy(specimanReceiptDto.getCreatedBy());
						tSpecimanMasterDto.setCreatedDate(specimanReceiptDto.getCreatedDate());
						tSpecimanMasterDto.setLabSampleDtlsId(labSampleDetailsMst.getLabSampleDtlsId());
						tSpecimanMasterDto.setHistopathlogyNumber(CommonUtils.numberToString(labSampleDetailsMst.getSampleNo(),HISTOPATHLOGY_PREFIX,NUMBER_WIDTH));
						iHistoMicroScopicExaminationDao.saveSpecimanDetails(tSpecimanMasterDto);
					}
					else {
						return new Response(ERROR, ERR_500, "Failed To Collect Speciman.", null, null);
					}
					
				}
				return new Response(SUCCESS, SUCCESS_200, "Speciman Accepted Successfully.", null, null);
			}
			else {
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
