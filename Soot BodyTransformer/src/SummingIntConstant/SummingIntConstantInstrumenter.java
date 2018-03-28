package SummingIntConstant;

import soot.*;
import soot.jimple.*;
import soot.util.*;
import java.util.*;

public class SummingIntConstantInstrumenter extends BodyTransformer {

	public int intConstantSum = 0;

	@Override
	protected void internalTransform(Body body, String phase, Map options) {
		// body's method
		SootMethod method = body.getMethod();

		// debugging
		System.out.println("instrumenting method : " + method.getSignature());
		ArrayList<IntConstant> intConstantList = getIntConstantCandidates(body);
		intConstantSum = sumIntConstant(intConstantList);
		System.out.println("The sum of all the IntConstants within the method is: " + intConstantSum);
	}

	private ArrayList<IntConstant> getIntConstantCandidates(Body body) {
		ArrayList<IntConstant> intConstantList = new ArrayList<IntConstant>();
		for (Unit unit : body.getUnits()) {
			@SuppressWarnings("unchecked")
			List<ValueBox> vbs = unit.getUseAndDefBoxes();
			
			for (ValueBox vb: vbs) {
				Value v = vb.getValue();
				if (v instanceof IntConstant) {
					intConstantList.add((IntConstant) v);
					System.out.println("IntConstant added: " + v);
				}
			}
			
		}
		return intConstantList;
	}

	private int sumIntConstant(ArrayList<IntConstant> intConstantList) {
		int sum = 0;
		for (IntConstant ic : intConstantList) {
			sum += ic.hashCode();
		}
		return sum;
	}

}