 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
 
public class xmlReader {
 
  public static String target;
  public static void main(String args[]) {
	xmlReader xr = new xmlReader();
	target = args[1];
    try {
 
	File fXmlFile = new File(args[0]);
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
 
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();
 
	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
 
	NodeList nList = doc.getElementsByTagName("s");
 
	//System.out.println("----------------------------");
 
	for (int temp = 0; temp < nList.getLength(); temp++) {
 
		Node nNode = nList.item(temp);
 
		
		
		StringBuffer sb = new StringBuffer();
		
		
		NodeList wList = nNode.getChildNodes();
		if(xr.judgeChild(sb, wList)) {
			//System.out.println("\nCurrent Element :" + nNode.getNodeName());
			String out = sb.toString();
			if(!out.trim().equals("")) {
				//System.out.println(nNode.getAttributes().getNamedItem("n"));
				System.out.println(out.trim());
			}
			if(out.contains("The Town & Country ")) {
				System.out.println(out.trim());
			}
		}
			
/*		for (int i = 0; i < wList.getLength(); i++) {
			boolean flag = false;
			Node currNode = wList.item(i);
			if(currNode.getNodeName() == "w") {
				
			}
			else {
				NodeList nextNodeList = currNode.getChildNodes();
				for (int j = 0; j < nextNodeList.getLength(); j++) {
					Node currNextLayerNode = nextNodeList.item(j);
				}
			}
		}*/
		
		
/*		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
 
			System.out.println("Staff id : " + eElement.getAttribute("id"));
			System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
			System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
			System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
			System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
 
		}*/
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
  }
  
  public boolean judgeChild(StringBuffer sb, NodeList nodeList) {
	  boolean flag = false;
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currNode = nodeList.item(i);
			
			if(currNode.getNodeName() == "w" || currNode.getNodeName() ==  "c") {
				//Element element = (Element) currNode;
				//System.out.println(element.getElementsByTagName("w").item(0).getTextContent());
				//System.out.println(currNode.getFirstChild().toString());
				//System.out.println(currNode.getFirstChild().getNodeValue());
				//System.out.println(((Element)currNode).getAttribute("hw"));
				if(((Element)currNode).getAttribute("hw").equals(target)){
				//if(currNode.getNodeName() == "w" ) {
					//System.out.println(((Element)currNode).getAttribute("hw").compareToIgnoreCase(target) == 0);
					flag = true;
				}
				
				String appendedWord = currNode.getFirstChild().getNodeValue();
				//appendedWord.replace("//", "////");
				appendedWord.replace("/", "//");
				sb.append(appendedWord);
			}
		/*	else if () {
				//Element element = (Element) currNode;
				//System.out.println(element.getElementsByTagName("c").item(0).getTextContent());
				//System.out.println(currNode.getFirstChild().toString());
				System.out.println(currNode.getFirstChild().getNodeValue());
			}*/
			else {
				flag = flag | judgeChild(sb,currNode.getChildNodes());
			}
		}
		
	  return flag;
  }
}