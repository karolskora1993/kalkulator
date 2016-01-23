package kalkulator.models;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * Klasa reprezentująca domyślne opcje uzytkownika, któe są zapisane w pliku
 *
 * @author Karol
 */
public class DefaultOptions
{
    private String defaultUsername = "";

    /**
     *
     * @param fileName ścieżka do pliku z ustawieniami
     */
    public DefaultOptions(String fileName)
    {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            File f = new File(fileName);
            Document doc = builder.parse(f);

            Element root = doc.getDocumentElement();
            NodeList children = root.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                NodeList c = child.getChildNodes();
                for (int j = 0; j < c.getLength(); j++) {
                    Node cc = c.item(j);
                    if (cc instanceof Element) {
                        Element element = (Element) cc;
                        Text textNode = (Text) element.getFirstChild();
                        String text = textNode.getData().trim();
                        if (element.getTagName().equals("defaultlogin")) {
                            defaultUsername = text;
                        }
                    }
                }

            }

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @return domyslny login
     */
    public String getDefaultUsername(){
        return defaultUsername;
    }
}
