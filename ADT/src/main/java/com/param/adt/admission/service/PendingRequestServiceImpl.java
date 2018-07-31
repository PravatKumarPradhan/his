package com.param.adt.admission.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.param.adt.admission.dao.IPendingRequestDao;
import com.param.adt.admission.dto.AdmissionNoteDto;
import com.param.adt.admission.dto.AdmissionPatientDocumentsDto;
import com.param.adt.admission.model.AdmissionNote;
import com.param.adt.admission.model.AdmissionPatientDocuments;
import com.param.adt.common.DocumentUploadUtility;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PendingRequestServiceImpl implements IPendingRequestService, ICommonConstants {

	@Autowired
	private IPendingRequestDao iPendingRequestDao;
	
	@Autowired
	private DocumentUploadUtility documentUploadUtility;

	@Override
	public Response getPendingAdmissionRequestDetails(AdmissionNoteDto admissionNoteDto) throws ApplicationException {
		try {
			return iPendingRequestDao.getPendingAdmissionRequestDetails(admissionNoteDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getAvtivePatientCategoryList() throws ApplicationException {
		try {
			return iPendingRequestDao.getAvtivePatientCategoryList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getAvtivePaymentEntitlementList() throws ApplicationException {
		try {
			return iPendingRequestDao.getAvtivePaymentEntitlementList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updatePendingAdmissionRequestDetails(AdmissionNoteDto admissionNoteDto) {
		try {
			return iPendingRequestDao.updatePendingAdmissionRequestDetails(admissionNoteDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateAdmissionRequestMapperDetails(AdmissionNoteDto admissionNoteDto,
			AdmissionNote admissionNoteObiject) {
		try {
			admissionNoteDto.setActiveStatus('P');
			return iPendingRequestDao.updateAdmissionRequestMapperDetails(admissionNoteDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateReserveBedDetails(AdmissionNoteDto admissionNoteDto) throws ApplicationException {

		admissionNoteDto.setActiveStatus('R');
		return iPendingRequestDao.updateReserveBedDetails(admissionNoteDto);
	}

	@Override
	public Response uploadAdmissionDocuments(AdmissionNoteDto admissionNoteDto) throws ApplicationException {
		try {
			Iterator<AdmissionPatientDocumentsDto> itr = admissionNoteDto.getDocumentArray().iterator();
			while (itr.hasNext()) 
			{
				AdmissionPatientDocuments admissionPatientDocumentsDto = new AdmissionPatientDocuments();   
				AdmissionPatientDocumentsDto obj = itr.next();
				
				
				MultipartFile document = obj.getDocumentPath();
				String filePath = null;
				if(document != null && document.getSize() > 0){
					
					admissionPatientDocumentsDto.setAdmissionNoteId(admissionNoteDto.getAdmissionNoteId());
					filePath = documentUploadUtility.uploadAdmissionDocuments(document, admissionNoteDto);
				}
				System.out.println(filePath);
				admissionPatientDocumentsDto.setOrganizationId(admissionNoteDto.getOrganizationId());
				admissionPatientDocumentsDto.setUnitId(admissionNoteDto.getUnitId());
				admissionPatientDocumentsDto.setDocumentTypeId(obj.getDocumentTypeId());
				admissionPatientDocumentsDto.setAdmissionNoteId(admissionNoteDto.getAdmissionNoteId());
				admissionPatientDocumentsDto.setDocumentPath(filePath);
				admissionPatientDocumentsDto.setCreatedBy(admissionNoteDto.getCreatedBy());
				admissionPatientDocumentsDto.setUpdatedBy(admissionNoteDto.getUpdatedBy());
				iPendingRequestDao.uploadAdmissionDocuments(admissionPatientDocumentsDto);
			}
			return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getPendingAdmissionCount(AdmissionNoteDto admissionNoteDto) throws ApplicationException {
		try {
			return iPendingRequestDao.getCount(admissionNoteDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

}
