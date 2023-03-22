package com.example.examan2023.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * generic class to store and read XML files <=> objects
 *
 *
 *
 * @author  GL - 2023
 * 
 * 
 * @since 1.1
 */
public class XmlOperations<T> {

	
	private Class<T> clazz;
	/**
	 * 
	 * 
	 * @param clazz: the class to use
	 * 
	 * 
	 */
	public XmlOperations(Class<T> clazz) {
		this.clazz=clazz;
	}
	// lecture
		/**
		 * 
		 * 
		 * @param path : path of your xml file
		 * 
		 * @return a list of objects created by the data within the xml file
		 */
	public List<T> load(String path)
	{
		List<T> objects=new ArrayList<T>();
		try {
			Document document =
					DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(path));
			
			String nomClasse=clazz.getSimpleName().toLowerCase();
			
			NodeList elements= document.getDocumentElement().getElementsByTagName(nomClasse);
			
			for (int i = 0; i < elements.getLength(); i++) {
				Element element=(Element) elements.item(i);
				
				T object=clazz.getDeclaredConstructor().newInstance();
				
				NodeList childrens =element.getChildNodes();
				for (int j = 0; j < childrens.getLength(); j++) {
					if(childrens.item(j).getNodeType()==Node.ELEMENT_NODE)
					{
						Element elementFils=(Element) childrens.item(j);
						Field field=null;
						try {
							field=clazz.getDeclaredField(elementFils.getNodeName());
						}
						catch (Exception e) {
							//e.printStackTrace();
						}
						if(field!=null)
						{
							Class t=field.getType();
							String setMethodString="set"+
									field.getName().substring(0,1).toUpperCase()+
									field.getName().substring(1);
							Method setter= clazz.getDeclaredMethod(setMethodString, t);
							if(MAP.containsKey(t))
							t=MAP.get(t);
							
							setter.invoke(object, t.getDeclaredConstructor(String.class).newInstance(elementFils.getTextContent()));

						}
					}
				}
				objects.add(object);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return objects;
	}
	//ecriture

		/**
		 * 
		 * 
		 * @param path : path of your xml file
		 * @param list : list of data to save
		 * 
		 * @return boolean: either data is saved or not
		 */
	public boolean save(String path,List<T> list)
	{
		try {
			if(list==null || list.size()==0)
				return false;
			
			Class c=list.get(0).getClass();
			if(clazz != c)
				System.err.println("error .....");
			
			Field[] fileds=clazz.getDeclaredFields();
			Document document=DocumentBuilderFactory.newInstance().
					newDocumentBuilder().newDocument();
			Element racine=document.createElement
					(clazz.getSimpleName().toLowerCase()+"s");
			document.appendChild(racine);
			for(T o:list)
			{
				Element element=document.createElement
						(clazz.getSimpleName().toLowerCase());
				racine.appendChild(element);
				for(int i=0;i<fileds.length;i++)
				{
					Field field=fileds[i];
					Element ele1=document.createElement(field.getName());
					element.appendChild(ele1);
					String getString="get"+field.getName().substring(0,1).toUpperCase()+
							field.getName().substring(1);
					Method getter=clazz.getDeclaredMethod(getString);
					ele1.setTextContent(getter.invoke(o).toString());
				}
			}
			// 
			TransformerFactory transformerFactory=TransformerFactory.newInstance();
			transformerFactory.setAttribute("indent-number", 3);
			Transformer transformer=transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domSource=new DOMSource(document);
			transformer.transform(domSource, new StreamResult(new File(path)));
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	private static final Map<Class<?>, Class<?>> MAP = new HashMap() {{
	    put( int.class,Integer.class);
	    put(long.class,Long.class);
	    put( float.class,Float.class);
	    put(boolean.class,Boolean.class);
	    // etc
	}};
}