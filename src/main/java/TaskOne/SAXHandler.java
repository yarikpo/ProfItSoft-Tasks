package TaskOne;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SAXHandler extends DefaultHandler {

    private Map<String, Double> finesByTypes = new HashMap<>();
    private Violation violation;
    private String content;

    public Map<String, Double> getFinesByTypes() {
        return Collections.unmodifiableMap(finesByTypes);
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {

        switch (qName) {
            case "violation":
                violation = new Violation();
                break;
        }
    }

    /**
     * If data is correct -- adds it to map
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {

        switch (qName) {
            case "violation":
                if (!violation.getType().isEmpty() && violation.getFineAmount() > 0.0) {
                    finesByTypes.put(
                            violation.getType(),
                            finesByTypes.getOrDefault(violation.getType(), 0.0) + violation.getFineAmount()
                    );
                }
                break;

            case "type":
                violation.setType(content);
                break;

            case "fine_amount":
                try {
                    violation.setFineAmount(Double.parseDouble(content));
                }
                catch (NumberFormatException e) {
                    System.out.println("NULL is not allowed to fine_amount field.");
                }
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }


}
