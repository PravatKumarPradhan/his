package com.param.adt.common;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.param.adt.admission.dto.AdmissionNoteDto;


@Service
public class DocumentUploadUtility implements IAWSCredentials,ICommonConstants{

	public String uploadAdmissionDocuments(MultipartFile multipartFile,AdmissionNoteDto admissionPatientDocumentsDto){
		try{
			if(multipartFile != null && multipartFile.getSize() > 0){
				//AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
				//AmazonS3Client s3Client = new AmazonS3Client(awsCredentials);
				//ObjectMetadata metadata = new ObjectMetadata();
				//metadata.setContentType(multipartFile.getContentType());
				//write a file on s3 server
				String keyName = admissionPatientDocumentsDto.getAdmissionNoteId() + "_" + multipartFile.getOriginalFilename();
				//s3Client.putObject(APPOINTMENT_DOC_BUCKET, keyName, multipartFile.getInputStream(), metadata);
				//Setting Public Permissions to file
				//s3Client.setObjectAcl(APPOINTMENT_DOC_BUCKET, keyName, CannedAccessControlList.PublicRead);
				//get url of file			
				//s3Client.getResourceUrl(APPOINTMENT_DOC_BUCKET, keyName);
				return COMMON_PATH + APPOINTMENT_DOC_BUCKET + "/" + keyName;
			}
		}catch(Exception ie){
			ie.printStackTrace();
		}
		return null;
	}
	
	/*public String uploadAppointmentPolicyDocuments(MultipartFile multipartFile,AppointmentMasterDto appointmentMasterDto){
		try{
			if(multipartFile != null && multipartFile.getSize() > 0){
				AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
				AmazonS3Client s3Client = new AmazonS3Client(awsCredentials);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(multipartFile.getContentType());
				//write a file on s3 server
				String keyName = appointmentMasterDto.getAppointmentId() + "_" + multipartFile.getOriginalFilename();
				s3Client.putObject(APPOINTMENT_POLICY_DOC_BUCKET, keyName, multipartFile.getInputStream(), metadata);
				//Setting Public Permissions to file
				s3Client.setObjectAcl(APPOINTMENT_POLICY_DOC_BUCKET, keyName, CannedAccessControlList.PublicRead);
				//get url of file			
				s3Client.getResourceUrl(APPOINTMENT_POLICY_DOC_BUCKET, keyName);
				return COMMON_PATH + APPOINTMENT_POLICY_DOC_BUCKET + keyName;
			}
		}catch(AmazonServiceException ae){
			System.out.println("Error Message:    " + ae.getMessage() + "\nHTTP Status Code: " + ae.getStatusCode()+"\nAWS Error Code:   " + ae.getErrorCode()+"\nError Type:       " + ae.getErrorType()+"Request ID:       " + ae.getRequestId());
		}catch(IOException ie){
			ie.printStackTrace();
		}
		return null;
	}
	
	public String uploadDoctorRegistrationDocuments(MultipartFile multipartFile,DoctorRegistarionDto doctorRegistarionDto){
		try{
			if(multipartFile != null && multipartFile.getSize() > 0){
				AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
				AmazonS3Client s3Client = new AmazonS3Client(awsCredentials);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(multipartFile.getContentType());
				//write a file on s3 server
				String keyName = doctorRegistarionDto.getDoctorNumber() + "_" + multipartFile.getOriginalFilename();
				s3Client.putObject(DOCTOR_REG_DOC_BUCKET, keyName, multipartFile.getInputStream(), metadata);
				//Setting Public Permissions to file
				s3Client.setObjectAcl(DOCTOR_REG_DOC_BUCKET, keyName, CannedAccessControlList.PublicRead);
				//get url of file			
				s3Client.getResourceUrl(DOCTOR_REG_DOC_BUCKET, keyName);
				return COMMON_PATH + DOCTOR_REG_DOC_BUCKET + keyName;
			}
		}catch(AmazonServiceException ae){
			System.out.println("Error Message:    " + ae.getMessage() + "\nHTTP Status Code: " + ae.getStatusCode()+"\nAWS Error Code:   " + ae.getErrorCode()+"\nError Type:       " + ae.getErrorType()+"Request ID:       " + ae.getRequestId());
		}catch(IOException ie){
			ie.printStackTrace();
		}
		return null;
	}
	
	public String uploadDoctorCoverSheet(MultipartFile multipartFile,AppointmentDoctorCoversheetMapperDto coverSheetDto){
		try{
			if(multipartFile != null && multipartFile.getSize() > 0){
				AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
				AmazonS3Client s3Client = new AmazonS3Client(awsCredentials);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(multipartFile.getContentType());
				//write a file on s3 server
				String keyName = coverSheetDto.getAppointmentId() + "_" + coverSheetDto.getDoctorId() + "_" + multipartFile.getOriginalFilename();
				s3Client.putObject(APPOINTMENT_COVER_SHEET_BUCKET, keyName, multipartFile.getInputStream(), metadata);
				//Setting Public Permissions to file
				s3Client.setObjectAcl(APPOINTMENT_COVER_SHEET_BUCKET, keyName, CannedAccessControlList.PublicRead);
				//get url of file			
				s3Client.getResourceUrl(APPOINTMENT_COVER_SHEET_BUCKET, keyName);
				return COMMON_PATH + APPOINTMENT_COVER_SHEET_BUCKET + "/" + keyName;
			}
		}catch(AmazonServiceException ae){
			System.out.println("Error Message:    " + ae.getMessage() + "\nHTTP Status Code: " + ae.getStatusCode()+"\nAWS Error Code:   " + ae.getErrorCode()+"\nError Type:       " + ae.getErrorType()+"Request ID:       " + ae.getRequestId());
		}catch(IOException ie){
			ie.printStackTrace();
		}
		return null;
	}
	
	public String uploadDoctorCoverSheet(InputStream inputStream,AppointmentListDto appointmentListDto){
		try{
			if(inputStream != null && inputStream.available() > 0){
				AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
				AmazonS3Client s3Client = new AmazonS3Client(awsCredentials);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType("application/pdf");
				//write a file on s3 server
				String keyName = appointmentListDto.getAppointmentId() + "_" + appointmentListDto.getDoctorId() + "_cover_sheet.pdf";
				s3Client.putObject(APPOINTMENT_COVER_SHEET_BUCKET, keyName, inputStream, metadata);
				//Setting Public Permissions to file
				s3Client.setObjectAcl(APPOINTMENT_COVER_SHEET_BUCKET, keyName, CannedAccessControlList.PublicRead);
				//get url of file			
				s3Client.getResourceUrl(APPOINTMENT_COVER_SHEET_BUCKET, keyName);
				return COMMON_PATH + APPOINTMENT_COVER_SHEET_BUCKET + "/" + keyName;
			}
		}catch(AmazonServiceException ae){
			System.out.println("Error Message:    " + ae.getMessage() + "\nHTTP Status Code: " + ae.getStatusCode()+"\nAWS Error Code:   " + ae.getErrorCode()+"\nError Type:       " + ae.getErrorType()+"Request ID:       " + ae.getRequestId());
		}catch(IOException ie){
			ie.printStackTrace();
		}
		return null;
	}
	
	public String uploadDoctorDictation(MultipartFile multipartFile, DoctorCoverSheetUKDto coverSheetDto){
		try{
			if(multipartFile != null && multipartFile.getSize() > 0){
				AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
				AmazonS3Client s3Client = new AmazonS3Client(awsCredentials);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(multipartFile.getContentType());
				//write a file on s3 server
			
				String keyName = new Date().getTime() + "_" + coverSheetDto.getPatientId() + "_" + coverSheetDto.getDoctorId();
				s3Client.putObject(COVERSHEET_UK_DICTATION_BUCKET, keyName, multipartFile.getInputStream(), metadata);
				//Setting Public Permissions to file
				s3Client.setObjectAcl(COVERSHEET_UK_DICTATION_BUCKET, keyName, CannedAccessControlList.PublicRead);
				//get url of file
				s3Client.getResourceUrl(COVERSHEET_UK_DICTATION_BUCKET, keyName);
				return COMMON_PATH + COVERSHEET_UK_DICTATION_BUCKET + "/" + keyName;
			}
		}catch(AmazonServiceException ae){
			System.out.println("Error Message:    " + ae.getMessage() + "\nHTTP Status Code: " + ae.getStatusCode()+"\nAWS Error Code:   " + ae.getErrorCode()+"\nError Type:       " + ae.getErrorType()+"Request ID:       " + ae.getRequestId());
		}catch(IOException ie){
			ie.printStackTrace();
		}
		return null;
	}
	
	public String uploadIdentificationDocument(MultipartFile multipartFile , PatientIdentificationDetailsDto patientIdentificationDetailsDto){
		try{
			if(multipartFile != null && multipartFile.getSize() > 0){
				AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
				AmazonS3Client s3Client = new AmazonS3Client(awsCredentials);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(multipartFile.getContentType());
				String keyName = "IDF" + "_" + patientIdentificationDetailsDto.getPatientId() + "_" + patientIdentificationDetailsDto.getIdentificationTypeId();
				
				s3Client.putObject(PATIENT_IDENTIFICATION_DOC, keyName, multipartFile.getInputStream(), metadata);
				//Setting Public Permissions to file
				s3Client.setObjectAcl(PATIENT_IDENTIFICATION_DOC, keyName, CannedAccessControlList.PublicRead);
				//get url of file
				s3Client.getResourceUrl(PATIENT_IDENTIFICATION_DOC, keyName);
				return COMMON_PATH + PATIENT_IDENTIFICATION_DOC + "/" + keyName;
			}
		}catch(AmazonServiceException ae){
			System.err.println("Error Message:    " + ae.getMessage() + "\nHTTP Status Code: " + ae.getStatusCode()+"\nAWS Error Code:   " + ae.getErrorCode()+"\nError Type:       " + ae.getErrorType()+"Request ID:       " + ae.getRequestId());
		}catch(IOException ie){
			ie.printStackTrace();
		}
		return null;
	}
	
	public String uploadEmpIdentificationDocument(MultipartFile multipartFile , EmployeeIdentificationDetailsDto employeeIdentificationDetailsDto){
		try{
			if(multipartFile != null && multipartFile.getSize() > 0){
				AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
				AmazonS3Client s3Client = new AmazonS3Client(awsCredentials);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(multipartFile.getContentType());
				String keyName = "IDF" + "_" + employeeIdentificationDetailsDto.getEmployeeId() + "_" + employeeIdentificationDetailsDto.getIdentificationTypeId();
				
				s3Client.putObject(EMPLOYEE_IDENTIFICATION_DOC, keyName, multipartFile.getInputStream(), metadata);
				//Setting Public Permissions to file
				s3Client.setObjectAcl(EMPLOYEE_IDENTIFICATION_DOC, keyName, CannedAccessControlList.PublicRead);
				//get url of file
				s3Client.getResourceUrl(EMPLOYEE_IDENTIFICATION_DOC, keyName);
				return COMMON_PATH + EMPLOYEE_IDENTIFICATION_DOC + "/" + keyName;
			}
		}catch(AmazonServiceException ae){
			System.err.println("Error Message:    " + ae.getMessage() + "\nHTTP Status Code: " + ae.getStatusCode()+"\nAWS Error Code:   " + ae.getErrorCode()+"\nError Type:       " + ae.getErrorType()+"Request ID:       " + ae.getRequestId());
		}catch(IOException ie){
			ie.printStackTrace();
		}
		return null;
	}
	
	public String uploadPathologyOrder(InputStream inputStream, OrderMasterDto orderMasterDto){
		try{
			if(inputStream != null && inputStream.available() > 0){
				AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
				AmazonS3Client s3Client = new AmazonS3Client(awsCredentials);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType("application/pdf");
				//write a file on s3 server
				String keyName = orderMasterDto.getOrderId() + "_" + orderMasterDto.getAppointmentListDto().getAppointmentId() + "_pathology_order.pdf";
				s3Client.putObject(ORDER_PATHOLOGY, keyName, inputStream, metadata);
				//Setting Public Permissions to file
				s3Client.setObjectAcl(ORDER_PATHOLOGY, keyName, CannedAccessControlList.PublicRead);
				//get url of file			
				s3Client.getResourceUrl(ORDER_PATHOLOGY, keyName);
				return COMMON_PATH + ORDER_PATHOLOGY + "/" + keyName;
			}
		}catch(AmazonServiceException ae){
			System.err.println("Error Message:    " + ae.getMessage() + "\nHTTP Status Code: " + ae.getStatusCode()+"\nAWS Error Code:   " + ae.getErrorCode()+"\nError Type:       " + ae.getErrorType()+"Request ID:       " + ae.getRequestId());
		}catch(IOException ie){
			ie.printStackTrace();
		}
		return null;
	}
	
	public String uploadMicrobiologyOrder(InputStream inputStream, OrderMasterDto orderMasterDto){
		try{
			if(inputStream != null && inputStream.available() > 0){
				AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
				AmazonS3Client s3Client = new AmazonS3Client(awsCredentials);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType("application/pdf");
				//write a file on s3 server
				String keyName = orderMasterDto.getOrderId() + "_" + orderMasterDto.getAppointmentListDto().getAppointmentId() + "_microbiology_order.pdf";
				s3Client.putObject(ORDER_MICROBIOLOGY, keyName, inputStream, metadata);
				//Setting Public Permissions to file
				s3Client.setObjectAcl(ORDER_MICROBIOLOGY, keyName, CannedAccessControlList.PublicRead);
				//get url of file			
				s3Client.getResourceUrl(ORDER_MICROBIOLOGY, keyName);
				return COMMON_PATH + ORDER_MICROBIOLOGY + "/" + keyName;
			}
		}catch(AmazonServiceException ae){
			System.err.println("Error Message:    " + ae.getMessage() + "\nHTTP Status Code: " + ae.getStatusCode()+"\nAWS Error Code:   " + ae.getErrorCode()+"\nError Type:       " + ae.getErrorType()+"Request ID:       " + ae.getRequestId());
		}catch(IOException ie){
			ie.printStackTrace();
		}
		return null;
	}
	
	public String uploadRadiologyOrder(InputStream inputStream, OrderMasterDto orderMasterDto){
		try{
			if(inputStream != null && inputStream.available() > 0){
				AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
				AmazonS3Client s3Client = new AmazonS3Client(awsCredentials);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType("application/pdf");
				//write a file on s3 server
				String keyName = orderMasterDto.getOrderId() + "_" + orderMasterDto.getAppointmentListDto().getAppointmentId() + "_radiology_order.pdf";
				s3Client.putObject(ORDER_RADIOLOGY, keyName, inputStream, metadata);
				//Setting Public Permissions to file
				s3Client.setObjectAcl(ORDER_RADIOLOGY, keyName, CannedAccessControlList.PublicRead);
				//get url of file			
				s3Client.getResourceUrl(ORDER_RADIOLOGY, keyName);
				return COMMON_PATH + ORDER_RADIOLOGY + "/" + keyName;
			}
		}catch(AmazonServiceException ae){
			System.err.println("Error Message:    " + ae.getMessage() + "\nHTTP Status Code: " + ae.getStatusCode()+"\nAWS Error Code:   " + ae.getErrorCode()+"\nError Type:       " + ae.getErrorType()+"Request ID:       " + ae.getRequestId());
		}catch(IOException ie){
			ie.printStackTrace();
		}
		return null;
	}
	
	public String uploadPatientDocuments(MultipartFile multipartFile , PatientDocumentMapperDto patientDocumentMapperDto){
		try{
			if(multipartFile != null && multipartFile.getSize() > 0){
				AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
				AmazonS3Client s3Client = new AmazonS3Client(awsCredentials);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(multipartFile.getContentType());
				String keyName = "PAT" + "_" + patientDocumentMapperDto.getPatientId() + "_" + patientDocumentMapperDto.getDocumentTypeId() + "_" + patientDocumentMapperDto.getDocumentDate() + "_" + patientDocumentMapperDto.getTitle() + patientDocumentMapperDto.getIndex();
				
				s3Client.putObject(PATIENT_DOCUMENTS, keyName, multipartFile.getInputStream(), metadata);
				//Setting Public Permissions to file
				s3Client.setObjectAcl(PATIENT_DOCUMENTS, keyName, CannedAccessControlList.PublicRead);
				//get url of file
				s3Client.getResourceUrl(PATIENT_DOCUMENTS, keyName);
				return COMMON_PATH + PATIENT_DOCUMENTS + "/" + keyName;
			}
		}catch(AmazonServiceException ae){
			System.err.println("Error Message:    " + ae.getMessage() + "\nHTTP Status Code: " + ae.getStatusCode()+"\nAWS Error Code:   " + ae.getErrorCode()+"\nError Type:       " + ae.getErrorType()+"Request ID:       " + ae.getRequestId());
		}catch(IOException ie){
			ie.printStackTrace();
		}
		return null;
	}
	
	*//**
	 * Method to get PDF from URL and set password to it
	 * @param fileUrl
	 * @param patientMasterDto
	 * @return
	 * @throws InvalidPdfException
	 *//*
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Response getFileFromUrl(String fileUrl,PatientMasterDto patientMasterDto) throws InvalidPdfException{
		try{
			List<InputStream> list = new ArrayList<InputStream>();
			fileUrl = fileUrl.replaceAll(" ", "%20");
			PdfReader reader = new PdfReader(new URL(fileUrl).openStream());
			System.out.println(reader);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 PdfStamper stamper = new PdfStamper(reader, baos);//new FileOutputStream("D:/zv.pdf"));
			    stamper.setEncryption(patientMasterDto.getMrnNumber().getBytes(), "clinivantage".getBytes(), PdfWriter.ALLOW_PRINTING,
			    PdfWriter.ENCRYPTION_AES_128 | PdfWriter.DO_NOT_ENCRYPT_METADATA);
			    stamper.close();
			    reader.close();
			    System.out.println(baos.toByteArray().length);
			    InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
			    System.out.println( inputStream.available());
			    inputStream.close();
			    baos.close();
			    list.add(inputStream);
			    return new Response(SUCCESS, null, null, list);
		}
	    catch(InvalidPdfException ipe){
	    	return new Response(ERROR, null, DOCUMENENT_NOT_SUPPORTED, null);
		}catch(Exception e){
			e.printStackTrace();
			return new Response(ERROR, null, DOCUMENENT_NOT_SUPPORTED, null);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	 * Method Upload Document to AWS
	 * @param urlList
	 * @param patientMasterDto
	 * @return
	 * @throws InvalidPdfException
	 
	public Response uploadDocumentWithPassword(List<String> urlList,PatientMasterDto patientMasterDto) throws InvalidPdfException{
		try{
			List<String> passwordProtectedUrlList = new ArrayList<String>();
			for(String url : urlList){
				Response response = getFileFromUrl(url,patientMasterDto);
				if(response.getStatus().equals(SUCCESS)){
					List<InputStream> list = response.getListObject();
				InputStream inputStream = list.get(0);//getFileFromUrl(url);
				if(inputStream != null && inputStream.available() > 0){
						AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
						AmazonS3Client s3Client = new AmazonS3Client(awsCredentials);
						ObjectMetadata metadata = new ObjectMetadata();
						metadata.setContentType("application/pdf");
						int unique_id= (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE); 
						String keyName = "PPP" + String.valueOf(unique_id);
						
						s3Client.putObject(PATIENT_DOCS_WITH_PASSWORD, keyName, inputStream, metadata);
						//Setting Public Permissions to file
						s3Client.setObjectAcl(PATIENT_DOCS_WITH_PASSWORD, keyName, CannedAccessControlList.PublicRead);
						//get url of file
						s3Client.getResourceUrl(PATIENT_DOCS_WITH_PASSWORD, keyName);
						System.out.println("link"+COMMON_PATH + PATIENT_DOCS_WITH_PASSWORD + "/" + keyName);
						//return COMMON_PATH + PATIENT_DOCS_WITH_PASSWORD + "/" + keyName;
						passwordProtectedUrlList.add(COMMON_PATH + PATIENT_DOCS_WITH_PASSWORD + "/" + keyName);
					}
				}else{
					return  new Response(ERROR, null, DOCUMENENT_NOT_SUPPORTED, null);
					
				}
			}
			return new Response(SUCCESS, null, null, passwordProtectedUrlList);
		}catch(AmazonServiceException ae){
			System.err.println("Error Message:    " + ae.getMessage() + "\nHTTP Status Code: " + ae.getStatusCode()+"\nAWS Error Code:   " + ae.getErrorCode()+"\nError Type:       " + ae.getErrorType()+"Request ID:       " + ae.getRequestId());
		}catch(IOException ie){
			ie.printStackTrace();
		}
		return null;
	}
	
	  Active Deposit document upload
	  @param multipartFile
	  @param appointmentMasterDto
	  @return
	 
	public String uploadActiveDepositDocuments(MultipartFile multipartFile, ActiveDepositMasterDto appointmentMasterDto){
		try{
			if(multipartFile != null && multipartFile.getSize() > 0){
				AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
				AmazonS3Client s3Client = new AmazonS3Client(awsCredentials);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(multipartFile.getContentType());
				//write a file on s3 server
				int unique_id= (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE); 
				String keyName = appointmentMasterDto.getPatientId() + String.valueOf(unique_id) + "_" + multipartFile.getOriginalFilename();
				s3Client.putObject(ACTIVE_DEPOSIT_MASTER_DOC_BUCKET, keyName, multipartFile.getInputStream(), metadata);
				//Setting Public Permissions to file
				s3Client.setObjectAcl(ACTIVE_DEPOSIT_MASTER_DOC_BUCKET, keyName, CannedAccessControlList.PublicRead);
				//get url of file			
				s3Client.getResourceUrl(ACTIVE_DEPOSIT_MASTER_DOC_BUCKET, keyName);
				return COMMON_PATH + ACTIVE_DEPOSIT_MASTER_DOC_BUCKET + "/" + keyName;
			}
		}catch(AmazonServiceException ae){
			System.out.println("Error Message:    " + ae.getMessage() + "\nHTTP Status Code: " + ae.getStatusCode()+"\nAWS Error Code:   " + ae.getErrorCode()+"\nError Type:       " + ae.getErrorType()+"Request ID:       " + ae.getRequestId());
		}catch(IOException ie){
			ie.printStackTrace();
		}
		return null;
	}*/
}