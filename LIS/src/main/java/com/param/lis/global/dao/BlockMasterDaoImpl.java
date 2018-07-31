package com.param.lis.global.dao;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import java.math.BigInteger;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.global.BlockMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.BlockMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;

@Repository
@SuppressWarnings("rawtypes")
public class BlockMasterDaoImpl extends  GenericDao<BlockMaster, Integer> implements IBlockMasterDao, ICommonConstants, IError{

	public BlockMasterDaoImpl() {
		super(BlockMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addBlockMaster(BlockMasterDto blockMasterDto)throws ApplicationException {
		try{
			BlockMaster blockMaster = new BlockMaster();
			blockMaster.setCreatedBy(blockMasterDto.getCreatedBy());
			blockMaster.setCreatedDate(blockMasterDto.getCreatedDate());
			blockMaster.setCode(blockMasterDto.getCode());
			blockMaster.setDesc(blockMasterDto.getDesc());
			blockMaster.setStatus(blockMasterDto.getStatus());
			blockMaster.setOrgId(blockMasterDto.getOrgId());
			blockMaster.setUpdatedBy(blockMasterDto.getUpdatedBy());
			blockMaster.setUpdatedDate(blockMasterDto.getUpdatedDate());
			blockMaster.setIsBlockFrozensec(blockMasterDto.getIsBlockFrozensec());
			blockMaster = save(blockMaster);
			return new Response<>(SUCCESS, SUCCESS_200,BLOCK_ADD_SUCC, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,BLOCK_NOT_FOUND, null,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response checkBlockMaster(BlockMasterDto blockMasterDto)throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listUnitMasteDtosessionFactory = sessionFactory.getCurrentSession().getNamedQuery("GET_REPORT_TYPE_BY_BLOCK_CODE").setString("code", blockMasterDto.getCode().trim().toLowerCase()).setInteger("orgId", blockMasterDto.getOrgId()).setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listUnitMasteDtosessionFactory, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getBlockMasterById(Integer orgId, Integer  blockId)
			throws ApplicationException {
		
		try
		{
			GlobalCommonDto containerMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REPORT_TYPE_BY_BLOCK_ID").setInteger("orgId", orgId)
					.setInteger("blockId", blockId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, containerMaster);

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,BLOCK_NOT_FOUND, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response updateBlockMaster(BlockMasterDto blockMasterDto)
			throws ApplicationException {
		try
		{
			BlockMaster reportTypeMaster = mapper.map(blockMasterDto, BlockMaster.class,"BlockMasterDtoTOBlockMaster");
			update(reportTypeMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500,BLOCK_ADD_FAIL, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response ActivateInactivateBlockMaster(Integer orgId,
			Integer unitId, Character unitStatus)
			throws ApplicationException {

		try
		{
			BlockMaster bacteriaMaster = findById(unitId);
			if (bacteriaMaster.getBlockId() != 0)
			{
				bacteriaMaster.setStatus(unitStatus);;
				BlockMaster bacteriaMst = update(bacteriaMaster);
				return new Response(SUCCESS, SUCCESS_200,BLOCK_ACTIVATE_SUCC, null, bacteriaMst);
				
			} else
			{
				return new Response(ERROR, ERR_500,BLOCK_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,BLOCK_NOT_FOUND, null, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response listBlockMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listUnitMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_BLOCK_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listUnitMasterDto, null);
		
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Response getToTalBlockMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			BigInteger blockMasterCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_BLOCK_RECORD").setInteger("orgId", orgId).uniqueResult();
			
			
			if (blockMasterCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, blockMasterCount);
			} else
			{
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public Response updateCheckBlockCodeAvaiable(BlockMasterDto blockMasterDto)
			throws ApplicationException {
		try {
			List<GlobalCommonDto> listCheckPhlebotomyCodeFactory = sessionFactory
					.getCurrentSession()
					.getNamedQuery("UPDATE_GET_REPORT_TYPE_BY_BLOCK_CODE")
					.setString("code",blockMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", blockMasterDto.getOrgId())
					.setInteger("blockId",	blockMasterDto.getBlockId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null,listCheckPhlebotomyCodeFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
