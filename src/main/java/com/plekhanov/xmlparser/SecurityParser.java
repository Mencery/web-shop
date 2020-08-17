package com.plekhanov.xmlparser;

import com.plekhanov.entity.user.Role;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.plekhanov.constant.filter.SecurityParserConstants.*;

/**
 * class that parsers permission.xml
 */
public class SecurityParser {
    public Map<String, List<Role>> urlPattern2Roles;

    /**
     * initialize {@link Map}
     */
    public SecurityParser() {
        urlPattern2Roles = new HashMap<>();
    }

    /**
     *
     * @return parsed permission.xml
     */
    public Map<String, List<Role>> parse() {
        try {
            File inputFile = new File(PERMISSION_XML_PATH);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            NodeList nodeList = doc.getElementsByTagName(NODE_CONSTRAINT);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    urlPattern2Roles.put(getUrlPattern(element),getRoles(element));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return urlPattern2Roles;
    }

    /**
     *
     * @param element - xml element
     * @return list of roles that access in this element
     */
    private List<Role> getRoles(Element element) {
        List<Role> roleList = new ArrayList<>();

        NodeList roleNodeList = element.getElementsByTagName(NODE_ROLE);

        for (int i = 0; i < roleNodeList.getLength(); i++) {
            Node node = roleNodeList.item(i);
            Element roleElement = (Element) node;
            Role role = Role.valueOf(roleElement.getTextContent().toUpperCase());
            roleList.add(role);
        }
        return roleList;
    }

    /**
     *
     * @param element - xml element
     * @return url-pattern of xml element
     */
    private String getUrlPattern(Element element){
        return element
                .getElementsByTagName(NODE_URL_PATTERN)
                .item(0)
                .getTextContent();
    }
}
