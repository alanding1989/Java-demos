package factory.abstraction;

import java.util.HashMap;
import java.util.Map;

import factory.simple.Operation;
import factory.simple.OperationAdd;
import factory.simple.OperationDiv;
import factory.simple.OperationMul;
import factory.simple.OperationSub;

/**
 * 利用反射改造简单工厂模式，去掉分支判断的逻辑
 *
 * @author liu yuning
 *
 */
public class OperationFactory {
    private static Map<String, Class<?>> allOperationMaps = new HashMap<String, Class<?>>();

    public static void fillMap() {
	allOperationMaps.put("+", OperationAdd.class);
	allOperationMaps.put("-", OperationSub.class);
	allOperationMaps.put("*", OperationMul.class);
	allOperationMaps.put("/", OperationDiv.class);
    }

    public static Operation createOperation(String operator)
	    throws InstantiationException, IllegalAccessException {
	Operation operation;

	fillMap();
	Class<?> operationClass = allOperationMaps.get(operator);

	if (operationClass == null) {
	    throw new RuntimeException("unsupported operation");
	}

	operation = (Operation) operationClass.newInstance();

	return operation;
    }

}
