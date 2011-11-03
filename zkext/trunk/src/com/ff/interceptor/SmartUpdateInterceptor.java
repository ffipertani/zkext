package com.ff.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import com.ff.Component;
import com.ff.PropertyField;
import com.ff.enums.UpdateType;

public class SmartUpdateInterceptor implements MethodInterceptor  {

	@Override
	public Object intercept(Object object, Method method, Object[] args,
			MethodProxy methodProxy) throws Throwable {
		Object res=null;
		
		if(object instanceof Component){
			res = methodProxy.invokeSuper(object, args);
			
			if(method.getName().equals("getProperties")){
				return res;
			} 
			Component component = (Component)object;
			Map<String,PropertyField> properties = component.getProperties();
			if(method.getName().startsWith("set")){
				String propName = method.getName().substring(3).toLowerCase();
				PropertyField pf = properties.get(propName);
				if(pf!=null){
					if(pf.getUpdateClient()== UpdateType.ALWAYS){
						String getterName = method.getName().substring(3);
						getterName = "get"+getterName;
						Method m = component.getClass().getMethod(getterName, new Class[]{});
						Object value = m.invoke(component, null);
						component.updateClient(pf.getName(), value);
					}
				}
			}
		}else{
			res = methodProxy.invokeSuper(object, args);
		}
		return res;
	}

}
