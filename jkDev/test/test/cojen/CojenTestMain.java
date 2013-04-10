package test.cojen;

import java.lang.reflect.Method;

import org.cojen.classfile.CodeBuilder;
import org.cojen.classfile.LocalVariable;
import org.cojen.classfile.MethodInfo;
import org.cojen.classfile.Modifiers;
import org.cojen.classfile.RuntimeClassFile;
import org.cojen.classfile.TypeDesc;


public class CojenTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Display d = new Display();
		
		RuntimeClassFile rf = sample();
		Class clazz = rf.defineClass();
		
		System.out.println( "class_name:" + clazz.getName() );
		
		// cojen
		Method[] methods = clazz.getMethods();
		for(Method m: methods){
			System.out.println( m.getName() );
		}
	}

	
	// button 생성여부 - 
	private static RuntimeClassFile sample() {
		// TODO Auto-generated method stub
		RuntimeClassFile rf = new RuntimeClassFile();
		rf.addDefaultConstructor();
		
		
		//++++++++++++++++++++++++++++
		// method definded <-- method 정의 .
		// public void call()
		//MethodInfo method = rf.addConstructor(modifiers, params)
				
		MethodInfo method = rf.addMethod(Modifiers.PUBLIC_STATIC, "myJJKCallMethod_XXcall", null, null);
		CodeBuilder b = new CodeBuilder(method);
		
		//
		LocalVariable name = b.createLocalVariable("name", TypeDesc.STRING);
		
		//Vari
		
		
		TypeDesc display = TypeDesc.forClass("org.eclipse.swt.widgets.Display");
		b.createLocalVariable("display", display);
		b.newObject(display);
		b.dup();
		
		//b.loadLocal(name);
		//b.dup();
		
		b.returnVoid();
		//------------------------------------------------------
		
		return rf;
		
		
		
	}

}
