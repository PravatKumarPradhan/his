package com.param.lis.global.common;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.commons.lang3.StringUtils;

public class CommonUtils
	{

	public static final String query = "SELECT * FROM ABC WHERE a=:a";
		public static BigDecimal stringToBigDecimal(String inputNumber)
			{
				try
					{
						if (StringUtils.isEmpty(inputNumber))
							{
								return BigDecimal.ZERO;
							}
						else
							{
								return new BigDecimal(inputNumber);
							}
					}
				catch (Exception e)
					{
						e.printStackTrace();
						return null;
					}

			}
		
	        public static String numberToString(BigInteger inputNumber,String prefix,Integer width)
	            {
	            StringBuilder generatedNumber = new StringBuilder();
	                try
	                    {
	                      
	                      Integer numberLength = inputNumber.toString().length();
	                        if (!inputNumber.equals(BigInteger.ZERO))
	                            {
	                                generatedNumber.append(prefix);
	                                for (int i = 0; i < (width-numberLength); i++) 
	                                {
	                                  generatedNumber.append("0");
                                    }
	                                generatedNumber.append(inputNumber);
	                            }
	                    }
	                catch (Exception e)
	                    {
	                        e.printStackTrace();
	                        return null;
	                    }
	               return  generatedNumber.toString(); 
	            }

	        

	}
