package SummingIntConstant;

import soot.*;
import soot.jimple.*;
import soot.util.*;
import java.util.*;

public class SummingIntConstantInstrumenter extends BodyTransformer {

	Local l = null;

	@Override
	protected void internalTransform(Body body, String phase, Map options) {
		// body's method
		SootMethod method = body.getMethod();

		// debugging
		System.out.println("instrumenting method : " + method.getSignature());
		ArrayList<IntConstant> intConstantList = getIntConstantCandidates(body);
		int intConstantSum = sumIntConstant(intConstantList);
		System.out.println("The sum of all the IntConstants within the method is: " + intConstantSum);
	}

	private ArrayList<IntConstant> getIntConstantCandidates(Body body) {
		ArrayList<IntConstant> intConstantList = new ArrayList<IntConstant>();

		for (Unit unit : body.getUnits()) {
			Iterator ubIt = unit.getUseAndDefBoxes().iterator();
			while (ubIt.hasNext()) {
				ValueBox vb = (ValueBox) ubIt.next();
				Value v = vb.getValue();
				if (v instanceof BinopExpr) {
					BinopExpr be = (BinopExpr) v;
					Value lo = be.getOp1(), ro = be.getOp2();
					if (lo instanceof IntConstant) {
						intConstantList.add((IntConstant) lo);
						System.out.println("IntConstant added: " + lo);
					}
					if (ro instanceof IntConstant) {
						intConstantList.add((IntConstant) ro);
						System.out.println("IntConstant added: " + ro);
					}
				}
			}
		}
		return intConstantList;
	}
	
	private int sumIntConstant(ArrayList<IntConstant> intConstantList) {
		int sum = 0;
		for (IntConstant ic: intConstantList) {
			sum += ic.hashCode();
		}
		return sum;
	}

}