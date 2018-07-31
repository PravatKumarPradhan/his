package com.param.lis.global.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Util {
	public static String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
	
	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	     List<T> r = new ArrayList<T>(c.size());
	     for(Object o: c)
	       r.add(clazz.cast(o));
	     return r;
	 }
}
