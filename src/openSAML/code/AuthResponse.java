package openSAML.code;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.opensaml.DefaultBootstrap;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.EncryptedAssertion;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.encryption.Decrypter;
import org.opensaml.xml.Configuration;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.encryption.DecryptionException;
import org.opensaml.xml.encryption.EncryptedKey;
import org.opensaml.xml.io.Unmarshaller;
import org.opensaml.xml.io.UnmarshallingException;
import org.opensaml.xml.security.SecurityHelper;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.keyinfo.KeyInfoCredentialResolver;
import org.opensaml.xml.security.keyinfo.StaticKeyInfoCredentialResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class AuthResponse {
	
	public AuthResponse() {
		try {
			DefaultBootstrap.bootstrap();
		
		} catch (ConfigurationException ce) {
			ce.printStackTrace();
		}
	}
	
	public void processResponse(String responseMessage) throws ParserConfigurationException, SAXException, IOException, UnmarshallingException {
		
		Response fetchedResponse = this.fetchResponse(responseMessage);
		this.getFieldsFromResponse(fetchedResponse);
	}
	
	public Response fetchResponse(String responseMessage) throws ParserConfigurationException, SAXException, IOException, UnmarshallingException {
		
		Base64 base64 = new Base64();
		byte[] decodedB = base64.decode(responseMessage);

		ByteArrayInputStream is = new ByteArrayInputStream(decodedB);

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
		DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();

		Document document = docBuilder.parse(is);
		Element element = document.getDocumentElement();
		System.out.println(element);
	
		Unmarshaller unmarshaller = Configuration.getUnmarshallerFactory().getUnmarshaller(element);
		if (unmarshaller == null)
			throw new IllegalStateException("Could not obtain a SAML unmarshaller for element: " + element.getLocalName());

		XMLObject xmlobj = unmarshaller.unmarshall(element);
		Response respObject = (Response) xmlobj;
		System.out.println(respObject);
		
		return respObject;
	}
	
	public void getFieldsFromResponse(Response respObject) {
		
		List<EncryptedAssertion> encryptedAssertion = respObject.getEncryptedAssertions();
		EncryptedAssertion firstAssertion = encryptedAssertion.get(0);
		System.out.println(encryptedAssertion.get(0));
		
		EncryptedKey key = firstAssertion.getEncryptedData().getKeyInfo().getEncryptedKeys().get(0);
		 
		System.out.println(key.toString());
//		Decrypter decrypter = new Decrypter();
//		decrypter.decrypt(firstAssertion);
//		System.out.println(respObject.getAssertions().isEmpty());
		//Assertion assertion = respObject.getAssertions().isEmpty();
		
//		String subject = assertion.getSubject().getNameID().getValue();
//		String issuer = assertion.getIssuer().getValue();
//		String audience = assertion.getConditions().getAudienceRestrictions().get(0).getAudiences().get(0).getAudienceURI();
//		String statusCode = respObject.getStatus().getStatusCode().getValue();
//		
//		System.out.println(subject + issuer + audience + statusCode);

	}
	
	public Assertion decrypt(EncryptedAssertion enc, Credential credential) throws DecryptionException {
	    
		//credential is the receiver's private key
		KeyInfoCredentialResolver keyResolver = new StaticKeyInfoCredentialResolver(credential);
	    EncryptedKey key = enc.getEncryptedData().getKeyInfo().getEncryptedKeys().get(0);
	    
	    //receiver's private key is used to extract the shared key (public key)
	    Decrypter decrypter = new Decrypter(null, keyResolver, null);
	    SecretKey dkey = (SecretKey) decrypter.decryptKey(key, enc.getEncryptedData().getEncryptionMethod().getAlgorithm());
	    

	    Credential shared = SecurityHelper.getSimpleCredential(dkey);
	    
	    //Decrypt the data
	    decrypter = new Decrypter(new StaticKeyInfoCredentialResolver(shared), null, null);
	    return decrypter.decrypt(enc);
	  }
}
