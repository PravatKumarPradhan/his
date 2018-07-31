package com.param.lis.global.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

@Service
public class CommonDateUtils
	{

		/*@Autowired
		private SessionObject sessionObject;*/

		// static String zoneDesc = sessionObject.getZoneDesc();
		static String currentTimezone = "";
		static
			{
				Calendar cal = Calendar.getInstance();
				long milliDiff = cal.get(Calendar.ZONE_OFFSET);
				// Got local offset, now loop through available timezone id(s).
				String[] ids = TimeZone.getAvailableIDs();
				for (String id : ids)
					{
						TimeZone tz = TimeZone.getTimeZone(id);
						if (tz.getRawOffset() == milliDiff)
							{
								// Found a match.
								currentTimezone = id;
								break;
							}
					}
				System.out.println(currentTimezone);
			}

		public static Date getDate(String dateString, String pattern)
			{
				SimpleDateFormat format = null;
				Date date1 = null;
				try
					{
						format = new SimpleDateFormat(pattern);
						date1 = format.parse(dateString);
						/* System.out.println("dateeee===="+date1); */
					} catch (ParseException e)
					{
						e.printStackTrace();
					}
				return date1;
			}

		public static String getStringDate(Date dateDate, String pattern)
			{
				SimpleDateFormat format = null;
				String strDate = null;
				format = new SimpleDateFormat(pattern);
				strDate = format.format(dateDate);
				return strDate;
			}

		public static Date getDate(Date dateDate, String pattern)
			{
				SimpleDateFormat format = null;
				Date date2 = null;
				String strDate = null;
				try
					{
						format = new SimpleDateFormat(pattern);
						strDate = format.format(dateDate);
						date2 = format.parse(strDate);
						System.out.println(strDate + "__" + date2);
					} catch (ParseException e)
					{
						e.printStackTrace();
					}
				return date2;
			}

		public static Date getTime(Date dateDate, String pattern)
			{
				SimpleDateFormat format = null;
				Date date2 = null;
				String strDate = null;
				try
					{
						format = new SimpleDateFormat(pattern);
						strDate = format.format(dateDate);
						date2 = format.parse(strDate);
						System.out.println(strDate + "__" + date2);
					} catch (ParseException e)
					{
						e.printStackTrace();
					}
				return date2;
			}

		/*
		 * public static Date getDate(String dateDate, String fromPattern,
		 * String toPatteren) { SimpleDateFormat format = null; Date date =
		 * null; String strDate = null; try { format = new
		 * SimpleDateFormat(fromPattern); date = format.parse(dateDate);
		 * System.out.println(date);
		 * 
		 * format = new SimpleDateFormat(toPatteren); strDate =
		 * format.format(date); date = format.parse(strDate);
		 * System.out.println(strDate);
		 * 
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } return date; }
		 */

		// ------------------------------------------------------------------------------------------------//

		public static String getFirstDateOfMonth(Date date)
			{
				Calendar c = Calendar.getInstance();
				try
					{
						c.setTime(date);
						c.set(Calendar.DAY_OF_MONTH, 1);

					} catch (Exception e)
					{
						e.printStackTrace();
					}
				return getStringDate(c.getTime(), "dd-MM-yyyy");
			}

		public static String getLastDateOfMonth(Date date)
			{
				Calendar c = Calendar.getInstance();
				try
					{
						c.setTime(date);
						c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

					} catch (Exception e)
					{
						e.printStackTrace();
					}
				return getStringDate(c.getTime(), "dd-MM-yyyy");
			}

		/*public static String getFirstDateOfMonth(int month, SessionObject sessionObject)
			{
				Calendar cal = Calendar.getInstance();
				GregorianCalendar gc = null;
				Date startDate = null;
				Date currDate = null;
				try
					{
						gc = new GregorianCalendar(cal.get(Calendar.YEAR), month - 1, 1);
						startDate = gc.getTime();
						currDate = getDate(getDateByzone(sessionObject.getZoneDesc()), "dd-MM-yyyy");
						System.out.println("===========>" + currDate);
						if (currDate.compareTo(startDate) >= 0)
							return getStringDate(currDate, "dd-MM-yyyy");
						else if (currDate.compareTo(startDate) < 0)
							return getStringDate(startDate, "dd-MM-yyyy");

					} catch (Exception e)
					{
						e.printStackTrace();
					}
				return null;// getStringDate(gc.getTime(), "dd-MM-yyyy");
			}*/

		public static String getLastDateOfMonth(int month)
			{
				Calendar cal = Calendar.getInstance();
				GregorianCalendar gc = null;
				String lastDate = null;
				try
					{
						gc = new GregorianCalendar(cal.get(Calendar.YEAR), month - 1, 1);
						lastDate = getLastDateOfMonth(gc.getTime());
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				return lastDate;
			}

		public static LocalDate getLocalDate(Date date)
			{
				LocalDate localDate = null;
				try
					{
						localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				return localDate;
			}

		public static Date getDate(LocalDate date)
			{
				Date dateDate = null;
				try
					{
					  dateDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				return dateDate;
			}

		public static String getStringDate(LocalDate date, String pattern)
			{
				Date dateDate = null;
				String strDate = null;
				try
					{
						dateDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
						strDate = getStringDate(dateDate, pattern);
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				return strDate;
			}
		
		public static LocalTime getLocalTime(String time) {
			LocalTime localTime = null;
			String hhMM[] = null;
			try {
				hhMM = time.split(":");
				localTime = LocalTime.of(Integer.parseInt(hhMM[0]), Integer.parseInt(hhMM[1]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return localTime;
		}

		public static int getDayOfWeek(Date date)
			{
				Calendar cal = Calendar.getInstance();
				int DAY_OF_WEEK = 0;
				try
					{
						cal.setTime(date);
						DAY_OF_WEEK = cal.get(Calendar.DAY_OF_WEEK);
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				return DAY_OF_WEEK;
			}

		public static int getWeekOfMonth(Date date)
			{
				Calendar cal = Calendar.getInstance();
				int WEEK_OF_MONTH = 0;
				try
					{
						cal.setTime(date);
						WEEK_OF_MONTH = cal.get(Calendar.WEEK_OF_MONTH);
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				return WEEK_OF_MONTH;
			}

		/*public static LocalTime getLocalTime(String time)
			{
				LocalTime localTime = null;
				String hhMM[] = null;
				try
					{
						hhMM = time.split(":");
						localTime = LocalTime.of(Integer.parseInt(hhMM[0]), Integer.parseInt(hhMM[1]));
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				return localTime;
			}*/
		
		
		/*
		 * public static void main(String args[]){
		 * //System.out.println("Hello Java");
		 * //CommonDateUtils.getDate("13/09/2016", "dd/MM/yyyy" ,"yyyy-MM-dd");
		 * //System.out.println(Status.CHECKEDIN.ordinal());
		 * System.out.println(getFirstDateOfMonth(10)); }
		 */

	/*	public static String getTodaysDate(SessionObject sessionObject)
			{
				Date date = getDateByzone(sessionObject.getZoneDesc());
				try
					{
						DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
						String strDate = format.format(date);
						return strDate;
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				return "";
			}
*/
		public static void main(String args[])
			{
				/*
				 * String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a"; String
				 * dateInString = "16-11-2016 12:55:00 PM"; LocalDateTime ldt =
				 * LocalDateTime.parse(dateInString,
				 * DateTimeFormatter.ofPattern(DATE_FORMAT)); ZoneId currentZone
				 * = ZoneId.of(currentTimezone);
				 * System.out.println("TimeZone : " + currentTimezone);
				 * 
				 * //LocalDateTime + ZoneId = ZonedDateTime ZonedDateTime
				 * asiaZonedDateTime = ldt.atZone(currentZone);
				 * System.out.println("Date (India) : " + asiaZonedDateTime);
				 * 
				 * ZoneId newIndiaZone = ZoneId.of("Africa/Abidjan");
				 * System.out.println("TimeZone : " + newIndiaZone);
				 * 
				 * ZonedDateTime nyDateTime =
				 * asiaZonedDateTime.withZoneSameInstant(newIndiaZone);
				 * System.out.println("Date (New York) : " + nyDateTime);
				 * 
				 * DateTimeFormatter format =
				 * DateTimeFormatter.ofPattern(DATE_FORMAT);
				 * System.out.println("\n---DateTimeFormatter---");
				 * 
				 * System.out.println("Date (kolkata) : " +
				 * format.format(nyDateTime));
				 */
				// getDateByzone("ok");
			}

		/***
		 * Method to return Date based on timezone in string format
		 * 
		 * @author Nikhil
		 * @param zone
		 * @return date string
		 */
		/*
		 * public static String getStringDateByzone(String zone){ return
		 * getStringDate(getDateByzone(zone) , "dd-M-yyyy hh:mm:ss a"); }
		 */

		public static String getStringDateByzone(String zone, String pattern)
			{
				return getStringDate(getDateByzone(zone, pattern), pattern);
			}

		/***
		 * Method to Date based on timezone
		 * 
		 * @param zone
		 * @return Date
		 */
		public static Date getDateByzone(String zone)
			{
				try
					{
						String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";
						String dateInString = getStringDate(new Date(), DATE_FORMAT);
						LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern(DATE_FORMAT));
						ZoneId currentZone = ZoneId.of(currentTimezone);
						System.out.println("Current TimeZone : " + currentTimezone);

						// Current zone date time
						ZonedDateTime cutrrentZonedDateTime = ldt.atZone(currentZone);
						System.out.println("Date (AWS) : " + cutrrentZonedDateTime);

						// In Session date time
						// ZoneId sessionZone = ZoneId.of("Africa/Abidjan");
						ZoneId sessionZone = ZoneId.of(zone);
						System.out.println("Session TimeZone : " + sessionZone);

						// comapare to zones
						ZonedDateTime newDateTime = cutrrentZonedDateTime.withZoneSameInstant(sessionZone);
						System.out.println("Date (Session zone) : " + newDateTime);

						DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);

						System.out.println("Final date time : " + format.format(newDateTime));
						return getDate(format.format(newDateTime).toString(), DATE_FORMAT);
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				return null;
			}

		public static Date getDateByzone(String zone, String pattern)
			{
				try
					{
						String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";
						String dateInString = getStringDate(new Date(), DATE_FORMAT);
						LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern(DATE_FORMAT));
						ZoneId currentZone = ZoneId.of(currentTimezone);
						// System.out.println("Current TimeZone : " +
						// currentTimezone);

						// Current zone date time
						ZonedDateTime cutrrentZonedDateTime = ldt.atZone(currentZone);
						// System.out.println("Date (AWS) : " +
						// cutrrentZonedDateTime);

						// In Session date time
						// ZoneId sessionZone = ZoneId.of("Africa/Abidjan");
						ZoneId sessionZone = ZoneId.of(zone);
						// System.out.println("Session TimeZone : " +
						// sessionZone);

						// comapare to zones
						ZonedDateTime newDateTime = cutrrentZonedDateTime.withZoneSameInstant(sessionZone);
						// System.out.println("Date (Session zone) : " +
						// newDateTime);

						DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);

						// System.out.println("Final date time : " +
						// format.format(newDateTime));
						return getDate(format.format(newDateTime).toString(), pattern);
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				return null;
			}

		
	public static Integer getAgeFromBirthDate(Date dob)
	{
	    Calendar dateOfBirth = Calendar.getInstance();
	    dateOfBirth.setTime(dob == null ? new Date() : dob);
	    Calendar today = Calendar.getInstance();
	    int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
	    if (today.get(Calendar.DAY_OF_YEAR) < dateOfBirth.get(Calendar.DAY_OF_YEAR))
	      age--;
	    return age;
	}
		/*
		 * public static Date getDateByzone(String zone, String pattern){ try{
		 * String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a"; String dateInString =
		 * getStringDate(new Date(), DATE_FORMAT); LocalDateTime ldt =
		 * LocalDateTime.parse(dateInString,
		 * DateTimeFormatter.ofPattern(DATE_FORMAT)); ZoneId currentZone =
		 * ZoneId.of(currentTimezone); System.out.println("Current TimeZone : "
		 * + currentTimezone);
		 * 
		 * //Current zone date time ZonedDateTime cutrrentZonedDateTime =
		 * ldt.atZone(currentZone); System.out.println("Date (AWS) : " +
		 * cutrrentZonedDateTime);
		 * 
		 * //In Session date time //ZoneId sessionZone =
		 * ZoneId.of("Africa/Abidjan"); ZoneId sessionZone = ZoneId.of(zone);
		 * System.out.println("Session TimeZone : " + sessionZone);
		 * 
		 * //comapare to zones ZonedDateTime newDateTime =
		 * cutrrentZonedDateTime.withZoneSameInstant(sessionZone);
		 * System.out.println("Date (Session zone) : " + newDateTime);
		 * 
		 * DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
		 * 
		 * System.out.println("Final date time : " +
		 * format.format(newDateTime)); return
		 * getDate(format.format(newDateTime).toString(), pattern);
		 * }catch(Exception e){ e.printStackTrace(); } return null; }
		 */
	}
