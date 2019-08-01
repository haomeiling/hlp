package cn.bxd.sip.his.webservice.operation;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description: 处理分发
 * Package: cn.bxd.sip.his.webservice.operation
 *
 * @author Leeves
 * @version 1.0.0  2018-08-09
 */
@Component
public class OperationProcessorHolder {

    @Autowired
    private Map<String, OperationProcessor> operationProcessors;

    public OperationProcessor findOperationProcessor(Class clc) {
        OperationProcessor operationProcessor = operationProcessors.get(WordUtils.uncapitalize(clc.getSimpleName()));
        if (operationProcessor == null) {
            operationProcessor = operationProcessors.get(WordUtils.uncapitalize(NoDefinitionOperation.class.getSimpleName()));
        }
        return operationProcessor;
    }

}