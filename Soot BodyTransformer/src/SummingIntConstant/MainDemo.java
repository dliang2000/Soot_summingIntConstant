package SummingIntConstant;

import soot.*;
import soot.jimple.*;
import soot.options.Options;
import soot.util.*;
import java.io.*;
import java.util.*;

public class MainDemo {
	   public static void main(String[] args) {		   
		   SootClass c = Scene.v().forceResolve("SummingIntConstant.Test", SootClass.BODIES);
		   c.setApplicationClass();
		   Scene.v().loadNecessaryClasses();
		   SootMethod method1 = c.getMethodByName("foo");
		   SootMethod method2 = c.getMethodByName("bar");
		   List<SootMethod> entryPoints = new ArrayList<>();
		   entryPoints.add(method1);
		   entryPoints.add(method2);
		   Scene.v().setEntryPoints(entryPoints);
		   
		   PackManager.v().getPack("jtp").add(new Transform("jtp.instrumenter", new SummingIntConstantInstrumenter()));
		   PackManager.v().runPacks();
		   
		   soot.Main.main(args);		   
	   }
}