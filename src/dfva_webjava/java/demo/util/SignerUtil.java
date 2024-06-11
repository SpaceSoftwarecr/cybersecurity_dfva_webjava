package dfva_webjava.java.demo.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dfva_java.client.SettingsManager;
import dfva_webjava.java.demo.DfvaRespuesta;
import dfva_webjava.java.demo.DocumentSign;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SignerUtil {

    public static JsonObject createResponseFromJson(JsonObject signResponse, boolean isSuccess) {
        return Json.createObjectBuilder()
                .add("ExtensionData", "{}")
                .add("DebeMostrarElError", !isSuccess)
                .add("DescripcionDelError", signResponse.getString("status_text"))
                .add("FueExitosa", isSuccess)
                .add("SeRealizo", signResponse.getBoolean("received_notification")).build();
    }

    public static DfvaRespuesta buildDfvaResponse(JsonObject signResponse) {
        int status = signResponse.getInt("status");
        boolean isSuccess = status == 0;
        return new DfvaRespuesta(
                isSuccess, // fueExitosaLaSolicitud
                240, // tiempoMaximoDeFirmaEnSegundos
                3, // tiempoDeEsperaParaConsultarLaFirmaEnSegundos
                signResponse.getString("code"), // codigoDeVerificacion
                signResponse.getInt("id_transaction"), // idDeLaSolicitud
                !isSuccess, // debeMostrarElError
                signResponse.getString("status_text"), //descripcionDelError
                signResponse.getString("resume") // ResumenDelDocumento
        );
    }

    public static void saveDocument(String name, DocumentSign doc) throws IOException {
        String result = new ObjectMapper()
                .writeValueAsString(doc);
        System.out.println(result);
        File serverFile = new File("/tmp/demo_" + name);
        System.out.println("Guardando en " + "/tmp/demo_" + name);
        BufferedOutputStream stream = new BufferedOutputStream(Files.newOutputStream(serverFile.toPath()));
        stream.write(result.getBytes());
        stream.close();
    }

    public static void setSettingsManagerProperties() {
        SettingsManager manager = SettingsManager.getInstance();
        manager.setPath("C:\\Users\\mario.flores\\.dfva_java\\config.properties");
        manager.setSecretDir("C:\\Users\\mario.flores\\.dfva_java\\");
        manager.setProperty("publicCertificate", "C:\\Users\\mario.flores\\.dfva_java\\certificate.pem");
        manager.setProperty("publicKey", "C:\\Users\\mario.flores\\.dfva_java\\public_key.pem");
        manager.setProperty("privateKey", "C:\\Users\\mario.flores\\.dfva_java\\private_key.pem");
        manager.setProperty("baseUrl", "https://fva.ucr.ac.cr");
    }

    public static DocumentSign createDocumentSign(MultipartFile file, String identification, String resume, String docFormat, String razon, String lugar) throws IOException {
        DocumentSign doc = new DocumentSign();

        String uploaded = new String(Base64.encodeBase64(file.getBytes()));
        doc.addProperty("file_name", file.getOriginalFilename());
        doc.addProperty("file_uploaded", uploaded);
        doc.addProperty("identificacion", identification);
        doc.addProperty("resumen", resume);
        doc.addProperty("doc_format", docFormat);

        if (razon != null)
            doc.addProperty("razon", razon);
        if (lugar != null)
            doc.addProperty("lugar", lugar);

        return doc;
    }

    public static DocumentSign getSignedFile(String name) {
        final ObjectMapper mapper = new ObjectMapper();
        DocumentSign doc = null;
        try {
            doc = mapper.readValue(new File("C:\\Users\\mario.flores\\demo_" + name), DocumentSign.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

}
