package DataImporter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLProductDataImporter implements ProductDataImporter {

    private List<ProductDataDto> productDataDtos = new ArrayList<>();

    public XMLProductDataImporter() {
        ImportXML();
    }

    @Override
    public List<ProductDataDto> GetProductDataDtos() {

        return productDataDtos;
    }

    private void ImportXML() {
        try {
            File fXmlFile = new File("./files/products.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("product");

            for (int nodeIndex = 0; nodeIndex < nodeList.getLength(); nodeIndex++) {

                Node node = nodeList.item(nodeIndex);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String productId = element.getAttribute("id");
                    String productName = element.getElementsByTagName("name").item(0).getTextContent();
                    long amount = Long.parseLong(element.getElementsByTagName("amount").item(0).getTextContent());
                    long price = Long.parseLong(element.getElementsByTagName("price").item(0).getTextContent());

                    productDataDtos.add(new ProductDataDto(productId, productName, amount, price));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
