package com.fudn.mybatisplusdemo.module.chain.service;

import com.fudn.mybatisplusdemo.module.chain.dto.ChainOrderDTO;
import com.fudn.mybatisplusdemo.module.chain.handler.AbstractChainHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fdn
 * @since 2022-04-26 23:49
 */
@Service
public class ChainContextHandler implements InitializingBean {

    // 容器上下文
    private ApplicationContext applicationContext;

    @Autowired
    private List<AbstractChainHandler> abstractHandleList;

    private AbstractChainHandler abstractHandler;

    @Override
    public void afterPropertiesSet() throws Exception {
        for (int i = 0; i < abstractHandleList.size(); i++) {
            if (i == 0) {
                abstractHandler = abstractHandleList.get(0);
            } else {
                AbstractChainHandler currentHandler = abstractHandleList.get(i - 1);
                AbstractChainHandler nextHandler = abstractHandleList.get(i);
                currentHandler.setNextHandler(nextHandler);
            }
        }
    }

    public void handlerRequest(ChainOrderDTO request) {
        abstractHandler.filter(request);
    }


//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//
//    private <T extends AbstractChainHandler> AbstractChainHandler getHandler(Class<T> handlerClass) {
//        AbstractChainHandler handler = handlerMap.get(handlerClass.getName());
//        // Handler 已经装载过了，直接返回
//        if (handler != null) {
//            return handler;
//        }
//
//        // Handler 没有装载过，需要从容器中获取并依次装载
//        Map<String, T> beans = applicationContext.getBeansOfType(handlerClass);
//        AbstractChainHandler headHandler = null;
//        AbstractChainHandler tailHandler = null;
//        for (Map.Entry<String, T> entry : beans.entrySet()) {
//            if (headHandler == null) {
//                headHandler = entry.getValue();
//                tailHandler = headHandler;
//            }else {
//                AbstractChainHandler next = entry.getValue();
//                tailHandler.setNextHandler(next);
//                tailHandler = next;
//            }
//        }
//        handlerMap.put(handlerClass.getName(),headHandler);
//        return headHandler;
//    }
//
//    public <T extends AbstractChainHandler> void handlerRequest(HttpServletRequest request, Class<T> handlerClass) {
//        getHandler(handlerClass).execute(request);
//    }


}
