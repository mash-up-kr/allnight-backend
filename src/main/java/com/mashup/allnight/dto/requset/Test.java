package com.mashup.allnight.dto.requset;


import javax.enterprise.inject.spi.Bean;
import java.util.Iterator;
import java.util.Set;

public class Test {
}


//public class BeanUtil {
//
//    private static WeldContainer container = null;
//    private static Weld weld = null;
//    private static Object sync = new Object();
//    /**
//     *
//     *<pre>
//     * Return bean class
//     *</pre>
//     * @param beanName
//     * @return
//     * @throws Exception
//     */
//    @SuppressWarnings("unchecked")
//    public static <T> T getBean(String beanName) throws Exception {
//        init();
//        Set<Bean<?>> beans = container.getBeanManager().getBeans(beanName);
//        Class<?> clazz = null;
////    	beans = CDI.current().getBeanManager().getBeans(beanName);
//        //Why need to iterator....
//        Iterator<Bean<?>> itr = beans.iterator();
//        while(itr.hasNext()) {
//            Bean<?> bean = itr.next();
//            String tempBeanName = bean.getName();
//            if (tempBeanName.equals(beanName)) {
//                clazz = bean.getBeanClass();
//                break;
//            }
//        }
//        return (T)(clazz != null ? container.select(clazz).get() : null);
//    }
//
//
//
//    /**
//
//     *
//
//     *<pre>
//
//     * Initialize Bean Manager
//
//     *</pre>
//
//     * @throws Exception
//
//     */
//
//
//
//    private static void init() throws Exception {
//
//        if (container == null) {
//
//            synchronized(sync) {
//
//                weld = new Weld();
//
//                container = weld.initialize();
//
//            }
//
//        }
//
//    }
//}