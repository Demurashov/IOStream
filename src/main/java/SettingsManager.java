import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class SettingsManager {
    private String fileName;
    private Map<String, String> mapLoad = new TreeMap<>();
    private Map<String, String> mapSave = new TreeMap<>();
    private Map<String, String> mapLog = new TreeMap<>();

    public SettingsManager(String fileName) { //throws ParserConfigurationException, IOException, SAXException {
        this.fileName = fileName;
    }

    private Document readSettigs() {
        File file = new File(fileName);
        NodeList nodeList;
        if (file.exists()) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(file);
                Node root = doc.getDocumentElement();
                nodeList = root.getChildNodes();
                return doc;

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            System.out.println("Файл " + fileName + " не обнаружен");
        }
        return null;
    }

    private void setLoadSettings(Document doc) {
        NodeList lSnl = doc.getElementsByTagName("load").item(0).getChildNodes();
        for (int i = 0; i < lSnl.getLength(); i++) {
            Node node = lSnl.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                mapLoad.put(node.getNodeName(), node.getTextContent());
            }
        }
    }

    private void setSaveSettings(Document doc) {
        NodeList lSnl = doc.getElementsByTagName("save").item(0).getChildNodes();
        for (int i = 0; i < lSnl.getLength(); i++) {
            Node node = lSnl.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                mapSave.put(node.getNodeName(), node.getTextContent());
            }
        }
    }

    private void setLogSettings(Document doc) {
        NodeList lSnl = doc.getElementsByTagName("log").item(0).getChildNodes();
        for (int i = 0; i < lSnl.getLength(); i++) {
            Node node = lSnl.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                mapLog.put(node.getNodeName(), node.getTextContent());
            }
        }
    }
    public File getLoadFileName(){
        setLoadSettings(readSettigs());
        return new File(mapLoad.get("fileName"));
    }
    public Basket loadBySettings() {
        setLoadSettings(readSettigs());
        if (mapLoad.get("enabled").equals("true")) {
            File file = new File(mapLoad.get("fileName"));
            if (mapLoad.get("format").equals("json")) {
                return Basket.loadFromJsonFile(file);
            }
            if (mapLoad.get("format").equals("txt")) {
                return Basket.loadFromTxtFile(file);
            }
        } else {
            System.out.println("ЧТЕНИЕ ФАЙЛОВ ЗАПРЕЩЕНО НАСТРОЙКАМИ!!!");
        }
        return null;
    }

    public void saveBySettings(Basket basket) {
        setSaveSettings(readSettigs());
        if (mapSave.get("enabled").equals("true")) {
            File file = new File(mapSave.get("fileName"));
            if (mapSave.get("format").equals("json")) {
                basket.saveJson(file);
            }
            if (mapSave.get("format").equals("txt")) {
                basket.saveTxt(file);
            }
        } else {
            System.out.println("ЗАПИСЬ ФАЙЛОВ ЗАПРЕЩЕНА НАСТРОЙКАМИ!!!");
        }
    }

    public void saveLogBySettings(ClientLog clientLog) {
        setLogSettings(readSettigs());
        if (mapLog.get("enabled").equals("true")) {
            File file = new File(mapLog.get("fileName"));
            clientLog.exportAsCSV(file);
        } else {
            System.out.println("ЗАПИСЬ ФАЙЛОВ ЗАПРЕЩЕНА НАСТРОЙКАМИ!!!");
        }
    }
}




