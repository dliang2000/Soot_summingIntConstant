package SummingIntConstant;

import soot.*;
import soot.jimple.*;
;
public class MainDemo {
	   public static void main(String[] args) {
		   SootClass c = Scene.v().forceResolve("Test", SootClass.BODIES);
		   c.setApplicationClass();
		   Scene.v().loadNecessaryClasses();
		   SootMethod method = c.getMethodByName("foo");
		   
		   JimpleBody body = Jimple.v().newBody(method);
	   }
}