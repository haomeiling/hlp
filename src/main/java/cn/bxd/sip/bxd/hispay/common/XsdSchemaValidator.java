package cn.bxd.sip.bxd.hispay.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.StringReader;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 15:57
 */
public class XsdSchemaValidator {
    protected static final Log LOG = LogFactory.getLog(XsdSchemaValidator.class);

    private int errorCount = 0;

    private StringBuffer sb = new StringBuffer("");

    public int getErrorCount() {
        return errorCount;
    }

    public String getErrorMsg() {
        return sb.toString();
    }

    public boolean validateXML(String schemaFile, String xmlContent) {
        Schema schema = loadSchema(schemaFile);
        if (schema == null) {
            LOG.warn("Schema file " + schemaFile + " not found, suppose validate success.");
            return true;
        } else {
            return validateXml(schema, xmlContent);
        }
    }

    public boolean validateXml(Schema schema, String xmlContent) {
        try {
            // creating a Validator instance
            Validator validator = schema.newValidator();

            // setting my own error handler
            validator.setErrorHandler(new MyErrorHandler(this));

            // preparing the XML file as a SAX source
            SAXSource source = new SAXSource(new InputSource(new StringReader(xmlContent)));

            // validating the SAX source against the schema
            validator.validate(source);

            return (errorCount == 0);
        } catch (Exception e) {
            LOG.error(e.toString());
            return false;
        }
    }

    public Schema loadSchema(String name) {
        Schema schema = null;
        try {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(language);
            schema = factory.newSchema(new File(name));
        } catch (Exception e) {
            LOG.error("Load Schema file " + name + " error:" + e.getMessage());
        }
        return schema;
    }

    private class MyErrorHandler implements ErrorHandler {
        private XsdSchemaValidator validator = null;

        public MyErrorHandler(XsdSchemaValidator validator) {
            this.validator = validator;
        }

        public void warning(SAXParseException e) throws SAXException {
            validator.errorCount++;
            String msg = validator.errorCount + ": WARN: Line " + e.getLineNumber() + " column " + e.getColumnNumber()
                    + " " + e.getMessage();
            LOG.info(msg);
            validator.sb.append(msg);
        }

        public void error(SAXParseException e) throws SAXException {
            validator.errorCount++;
            String msg = validator.errorCount + ": ERROR: Line " + e.getLineNumber() + " column " + e.getColumnNumber()
                    + " " + e.getMessage();
            LOG.info(msg);
            validator.sb.append(msg);
        }

        public void fatalError(SAXParseException e) throws SAXException {
            validator.errorCount++;
            String msg = validator.errorCount + ": FATAL: Line " + e.getLineNumber() + " column " + e.getColumnNumber()
                    + " " + e.getMessage();
            LOG.info(msg);
            validator.sb.append(msg);
        }
    }
}
