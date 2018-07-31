package com.param.lis.histopathology.transaction.dao;

import java.util.Date;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.histo.TTemplateResult;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.TTemplateResultDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class TTemplateResultDaoImpl extends GenericDao<TTemplateResult, Integer>
implements ITTemplateResultDao, ICommonConstants, IError,ITransactionConstants
{
	
	final static Logger logger = Logger.getLogger(TTemplateResultDaoImpl.class);
	
	public TTemplateResultDaoImpl() 
	{
		super(TTemplateResult.class);
	}

	@Autowired
	private DozerBeanMapper mapper;



	@Override
	public Response saveTemplateResult(TTemplateResultDto tTemplateResultDto) throws ApplicationException {
		try
		{
				if(tTemplateResultDto.getTemplateResId()!=null&&tTemplateResultDto.getTemplateResId()!=0) {
					
					TTemplateResult tTemplateRes = mapper.map(tTemplateResultDto, TTemplateResult.class,
							"TTemplateResultDtoToTTemplateResult");
					tTemplateRes.setUpdatedDate(new Date().getTime());
					TTemplateResult tTemplateResultData = update(tTemplateRes);
					if (tTemplateResultData != null)
					{
						return new Response(SUCCESS, SUCCESS_200, "Template Result Update Successfully.", null, null);
						
					} else
					{
						return new Response(ERROR, ERR_500, "Failed to Update Template Result.", null, null);
					}
					
					
				}else {
					
					TTemplateResult tTemplateRes = mapper.map(tTemplateResultDto, TTemplateResult.class,
							"TTemplateResultDtoToTTemplateResult");
					tTemplateRes.setCreatedDate(new Date().getTime());
					TTemplateResult tTemplateResultData = save(tTemplateRes);
					if (tTemplateResultData != null)
					{
						return new Response(SUCCESS, SUCCESS_200, "Template Result Added Successfully.", null, null);
						
					} else
					{
						return new Response(ERROR, ERR_500, "Failed to Added Template Result.", null, null);
					}
					
				}
			
			
			
		}catch(HibernateException exception){
			logger.error("Exection", exception);
		} catch (Exception e)
		{
			logger.error("Exection", e);
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}



  @Override
  public Response getHistopathReport(Integer templateResId, Integer orgId, Integer orgUnitId)
      throws ApplicationException {
    try
    {
      TTemplateResult tTemplateRes = findById(templateResId); 
      TTemplateResultDto tTemplateResultDto = 
          mapper.map(tTemplateRes, TTemplateResultDto.class,
          "TTemplateResultDtoToTTemplateResult");
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, tTemplateResultDto);

    } catch (Exception e)
    {
        logger.error("Exection", e);
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

	

}
