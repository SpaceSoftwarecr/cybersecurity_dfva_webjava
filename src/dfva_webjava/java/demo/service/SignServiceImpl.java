package dfva_webjava.java.demo.service;

import dfva_java.client.Client;
import dfva_java.client.SettingsManager;
import dfva_webjava.java.demo.DfvaRespuesta;
import dfva_webjava.java.demo.DocumentSign;
import dfva_webjava.java.demo.data.DownloadResponse;
import dfva_webjava.java.demo.util.SignerUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.json.JsonObject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

public class SignServiceImpl implements SignerService {

    Client client;
    SettingsManager manager = SettingsManager.getInstance();

    @Override
    public DfvaRespuesta sign(String id) {
        SignerUtil.setSettingsManagerProperties();
        DocumentSign doc = SignerUtil.getSignedFile(id);
        Map<String, String> values = doc.getProperties();
        client = new Client(manager.get_and_create_settings());

        String razon = null;
        String lugar = null;
        String doc_format = values.get("doc_format");

        if (doc_format.equals("pdf")) {
            razon = values.get("razon");
            lugar = values.get("lugar");
        }

        JsonObject signResponse = client.sign(values.get("identificacion"), new ByteArrayInputStream(Base64.decodeBase64(values.get("file_uploaded"))), doc_format, values.get("resumen"), "sha512", lugar, razon);
        return SignerUtil.buildDfvaResponse(signResponse);
    }

    @Override
    public String check(String id, String requestId, String callback) throws IOException {
        JsonObject signResponse = client.sign_check(requestId);
        System.out.println(signResponse);
        int status = signResponse.getInt("status");

        boolean isSuccess = status == 0;
        boolean received_notification = signResponse.getBoolean("received_notification");
        if (isSuccess && received_notification) {
            DocumentSign doc = SignerUtil.getSignedFile(id);
            doc.addProperty("doc_signed", signResponse.getString("signed_document"));
            doc.addProperty("hash_signed", signResponse.getString("hash_docsigned"));
            SignerUtil.saveDocument(id, doc);
        }
        return callback + "(" + SignerUtil.createResponseFromJson(signResponse, isSuccess).toString() + ")";
    }

    @Override
    public DownloadResponse download(String id) {
        DocumentSign doc = SignerUtil.getSignedFile(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/octet-stream"));
        headers.setContentDispositionFormData("attachment", doc.getProperties().get("file_name"));

        return DownloadResponse
                .builder()
                .file(Base64.decodeBase64(doc.getProperties().get("doc_signed")))
                .headers(headers)
                .build();

    }
}
