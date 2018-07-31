package com.param.lis.common.dto;

import java.math.BigInteger;
import java.util.Date;

public class CommonCount
{
	private Date createddate;
	private BigInteger count;

	public Date getCreateddate()
	{
		return createddate;
	}

	public void setCreateddate(Date createddate)
	{
		this.createddate = createddate;
	}

	public BigInteger getCount()
	{
		return count;
	}

	public void setCount(BigInteger count)
	{
		this.count = count;
	}

}
