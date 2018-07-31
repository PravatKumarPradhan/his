package com.param.lis.microbiology.transaction.dao;

import com.param.entity.lis.micro.MicroscopicExaminationDetailsMaster;
import com.param.lis.global.common.Response;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ImicroscopicExaminationDetailsMaster extends IGenericDao<MicroscopicExaminationDetailsMaster, Integer>
{
	public Response savemicroscopicExaminationDetailsMaster(MicroscopicExaminationDetailsMaster microscopicExaminationDetailsMaster)throws ApplicationException;
	public Response updatemicroscopicExaminationDetailsMaster(MicroscopicExaminationDetailsMaster microscopicExaminationDetailsMaster)throws ApplicationException;
}
