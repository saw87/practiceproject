package com.maventree.services.util.navigation_service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class NavigationTreeLoader {
	
	public RegistryTreeNode loadNavigationTree(String filename) throws JDOMException, IOException{
		// read xml file
		SAXBuilder builder = new SAXBuilder();
		File fXmlFile = new File(filename);
		Document document = (Document) builder.build(fXmlFile);
		Element rootNode = document.getRootElement();

		// create root node
		NavMenuItem mi = new NavMenuItem();
		mi.pAlias = rootNode.getAttributeValue("pAlias");
		RegistryTreeNode navigationTreeRoot = new RegistryTreeNode(mi);
		
		T(navigationTreeRoot, rootNode);
		
		return navigationTreeRoot;
	}

	private void T(RegistryTreeNode parent, Element eElement) {
		List nList = eElement.getChildren();
	
		for (int i = 0; i < nList.size(); i++) {
			Element e = (Element) nList.get(i);
			String nodeName = e.getName();

			if (nodeName.equals("submenu")) {
				NavMenuItem m = new NavMenuItem();
				m.pAlias = e.getAttributeValue("pAlias");
				m.label = e.getAttributeValue("label");

				RegistryTreeNode n1 = new RegistryTreeNode(m);
				parent.addChild(n1);

				T(n1, e);
			} else if (nodeName.equals("item")) {
				NavMenuItem m = new NavMenuItem();
				m.pAlias = e.getAttributeValue("pAlias");
				m.label = e.getAttributeValue("label");
				m.resourceUrl = e.getAttributeValue("resource");

				RegistryTreeNode n1 = new RegistryTreeNode(m);
				parent.addChild(n1);
			}
		}
	}

}



