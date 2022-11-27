package TaskTwo;

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

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {

        switch (qName) {
            case "violation":
                finesByTypes.put(
                        violation.getType(),
                        finesByTypes.getOrDefault(violation.getType(), 0.0) + violation.getFineAmount()
                );
                break;

            case "type":
                violation.setType(content);
                break;
//                TODO check for non double variables
            case "fine_amount":
                violation.setFineAmount(Double.parseDouble(content));
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }


}
