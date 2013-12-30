import java.io.File;


public class ExecuteMe {
	public static String target;
	public static void main(String[] args) {
		String rootDir = "/usr/groups/corpora-cds/bnc-xml/Texts/";
		target = args[0];
		ExecuteMe em = new ExecuteMe();
		em.add(new File(rootDir));
	}
	
	public void add(File dir) {
		//File f = new File(rootDir);
		  for (File child : dir.listFiles()) {
		    // Do something with child
			  if(child.isDirectory()) add(child);
			  else {
				  String[] args = {child.getAbsolutePath(),target};
				  xmlReader.main(args);
			  }
		  }
	}
}
