package com.param.adt.discharge.dao;

import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.admission.model.AdmissionDetails;
import com.param.adt.common.ADTCommonDateUtils;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.discharge.dto.DischargeDto;
import com.param.adt.discharge.model.Discharge;
import com.param.global.dto.AdmissionDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DueForDischargeDaoImpl extends GenericDao<Discharge, Integer>
		implements IDueForDischargeDao, ICommonConstants {

	public DueForDischargeDaoImpl() {
		super(Discharge.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	DozerBeanMapper mapper;

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Response saveDischargeRequest(DischargeDto dischargeDto) throws ApplicationException {
		try {
			Discharge discharge = mapper.map(dischargeDto, Discharge.class, "DischargeDto_to_Discharge");
			save(discharge);

			// <-----Updating is discharge in admission details to P(Pending)
			Session session = sessionFactory.openSession();
			Query query = session
					.createQuery("update AdmissionDetails adtl set adtl.isDischarge='P' where adtl.admissionId="
							+ dischargeDto.getAdmissionId());
			query.executeUpdate();
			// ----->
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDischagePatientList(AdmissionDto admissionDto) throws ApplicationException {
		try {
			List<AdmissionDto> admissionDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ADMITTED_PATIENT_LIST_FOR_DISCHARGE")
					.setInteger("organizationId", admissionDto.getOrganizationId())
					.setInteger("unitId", admissionDto.getUnitId())
					.setDate("pdd", ADTCommonDateUtils.getDate(admissionDto.getPdd(), "dd-M-yyyy"))
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			return new Response(SUCCESS, null, null, admissionDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updatePDD(DischargeDto dischargeDto) throws ApplicationException {
		try {
			int preAdmissionDetailsId=0;
			Session session = sessionFactory.openSession();
			
			List<AdmissionDto> admissionDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ADMISSION_DETAILS_BY_ADMISSION_ID")
					.setInteger("organizationId", dischargeDto.getOrganizationId())
					.setInteger("unitId", dischargeDto.getUnitId())
					.setInteger("admissionId", dischargeDto.getAdmissionId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			
			if(dischargeDto.getIsCancel()!='\0')
			{
				//<-----Updating cancel details to admission details
				Query query = session.createQuery(
						"update AdmissionDetails dis set dis.remark=:remark ,"
						+ "dis.updatedBy=:updatedBy, "
						+ "dis.extendReasonId=:extendReasonId, "
						+ "dis.updatedDate=:updatedDate, "
						+ "dis.isCancel='Y', "
						+ "dis.cancelDate=:updatedDate, "
						+ "dis.cancelBy=:updatedBy "
						+ "where dis.admissionId=:admissionId");
							
				query.setInteger("updatedBy",dischargeDto.getUpdatedBy());
				query.setInteger("admissionId", dischargeDto.getAdmissionId());
				query.setInteger("extendReasonId",dischargeDto.getUpdatedBy());
				query.setString("remark", dischargeDto.getRemarkDischarge());
				query.setParameter("updatedDate", ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
				
				query.executeUpdate();
				//----->
				
			}
			else
			{
				//<-----Updating cancel details to admission details
				Query query = session.createQuery(
						"update AdmissionDetails dis set dis.remark=:remark ,"
						+ "dis.updatedBy=:updatedBy, "
						+ "dis.extendReasonId=:extendReasonId, "
						+ "dis.updatedDate=:updatedDate, "
						+ "dis.pdd=:pdd "
						+ "where dis.admissionId=:admissionId");
							
				query.setInteger("updatedBy",dischargeDto.getUpdatedBy());
				query.setInteger("admissionId", dischargeDto.getAdmissionId());
				query.setInteger("extendReasonId",dischargeDto.getUpdatedBy());
				query.setString("remark", dischargeDto.getRemarkDischarge());
				query.setDate("updatedDate", ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
				query.setDate("pdd", ADTCommonDateUtils.getDate(dischargeDto.getPdd(), "dd-M-yyyy HH:mm:ss"));
				
				query.executeUpdate();
				//----->
			}
			
			/*//<-----Updating cancel details to admission details
			Query query = session.createQuery(
					"update AdmissionDetails dis set dis.remark=:remark ,"
					+ "dis.updatedBy=:updatedBy, "
					+ "dis.extendReasonId=:extendReasonId, "
					+ "dis.updatedDate=:updatedDate, "
					+ "dis.pdd=:pdd "
					+ "where dis.admissionId=:admissionId");
						
			query.setInteger("updatedBy",dischargeDto.getUpdatedBy());
			query.setInteger("admissionId", dischargeDto.getAdmissionId());
			query.setInteger("extendReasonId",dischargeDto.getUpdatedBy());
			query.setString("remark", dischargeDto.getNoteCancel());
			query.setDate("updatedDate", ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			query.setDate("pdd", ADTCommonDateUtils.getDate(dischargeDto.getPdd(), "dd-M-yyyy HH:mm:ss"));
			
			query.executeUpdate();
			//----->
*/			
			//<-----Adding new entry in admission details by in-activating previous active record.
			Transaction tr=session.beginTransaction();
			Iterator<AdmissionDto> itr = admissionDtosList.iterator();
			 
			  while (itr.hasNext()) {
				  AdmissionDetails admissionDetails =new AdmissionDetails();
			    
				  AdmissionDto obj = itr.next();
				  preAdmissionDetailsId=obj.getAdmissionDetailsId();
				  admissionDetails.setUnitId(obj.getUnitId());
				  admissionDetails.setOrganizationId(obj.getOrganizationId());
				  admissionDetails.setAdmissionId(obj.getAdmissionId());
				  admissionDetails.setAdmissionTypeId(obj.getAdmissionTypeId());
				  admissionDetails.setFloorId(obj.getFloorId());
				  admissionDetails.setWardId(obj.getWardId());
				  admissionDetails.setRoomId(obj.getRoomId());
				  admissionDetails.setBedId(obj.getBedId());
				  admissionDetails.setIsShortLeave('N');
				  admissionDetails.setIsDischarge('N');
				  admissionDetails.setIsCancel('N');
				  admissionDetails.setIsClose('N');
				  admissionDetails.setIsHighRisk(obj.getIsHighRisk());
				  admissionDetails.setIsCritical(obj.getIsCritical());
				  admissionDetails.setStatus('A');
				  admissionDetails.setCreatedBy(dischargeDto.getCreatedBy());
				  admissionDetails.setUpdatedBy(dischargeDto.getUpdatedBy());
				  admissionDetails.setCreatedDate(ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				  admissionDetails.setUpdatedDate(ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				  admissionDetails.setBedCategoryId(obj.getBedCategoryId());
				  admissionDetails.setBillingBedCategoryId(obj.getBillingBedCategoryId());
				  admissionDetails.setMealPrefId(obj.getMealPrefId());
				  admissionDetails.setAttendentMealPrefId(obj.getAttendentMealPrefId());
				  admissionDetails.setDoa(ADTCommonDateUtils.getDate(obj.getDoa(),"dd-M-yyyy HH:mm:ss"));
				  admissionDetails.setPdd(ADTCommonDateUtils.getDate(dischargeDto.getPdd(), "dd-M-yyyy HH:mm:ss"));
				  admissionDetails.setTriageCategoryId(obj.getTriageCategoryId());
				  admissionDetails.setErBedTypeAllocation(obj.getErBedTypeAllocation());
				  session.save(admissionDetails);
				  tr.commit();
			  }
			
			  //<-----
			  Transaction tr2=session.beginTransaction();
			  Query query2=session.createQuery("update AdmissionDetails dis set dis.status='I' where dis.admissionDetailsId="+preAdmissionDetailsId);
			  query2.executeUpdate();
			  tr2.commit();
			  //----->
			  
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
