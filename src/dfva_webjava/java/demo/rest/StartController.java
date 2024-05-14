package dfva_webjava.java.demo.rest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dfva_webjava.java.demo.DocumentSign;

@Controller
public class StartController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String display() {
        return "index";
    }

    private DocumentSign get_document_sign(String name) {
        final ObjectMapper mapper = new ObjectMapper();

        DocumentSign doc = null;
        try {
            doc = mapper.readValue(new File("/tmp/demo_" + name),
                    DocumentSign.class);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return doc;
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)

    public HttpEntity<byte[]> download(@PathVariable("id") String id) {
        DocumentSign doc = get_document_sign(id);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.valueOf("application/octet-stream"));
        header.setContentDispositionFormData("attachment", doc.getProperties().get("file_name"));

        byte[] documentBody = Base64.decodeBase64(doc.getProperties().get("doc_signed"));

        return new HttpEntity<byte[]>(documentBody, header);
    }

    @RequestMapping(value = "/create_sign", method = RequestMethod.POST)
    @ModelAttribute

    public String createsign(HttpServletRequest request, @RequestParam("file_uploaded") MultipartFile file,
                             @RequestParam("identificacion") String identification,
                             @RequestParam("resumen") String resume,
                             @RequestParam("doc_format") String doc_format,
                             @RequestParam(value = "razon", required = false) String razon,
                             @RequestParam(value = "lugar", required = false) String lugar,
                             Model model
    ) throws IOException {


        String uploaded = new String(Base64.encodeBase64(file.getBytes()));
        DocumentSign doc = new DocumentSign();
        doc.addProperty("file_name", file.getOriginalFilename());
        doc.addProperty("file_uploaded", uploaded);
        doc.addProperty("identificacion", identification);
        doc.addProperty("resumen", resume);
        doc.addProperty("doc_format", doc_format);

        if (razon != null)
            doc.addProperty("razon", razon);
        if (lugar != null)
            doc.addProperty("lugar", lugar);

        String result = new ObjectMapper()
                .writeValueAsString(doc);

        String domain = request.getScheme() + "://" + request.getHeader("host");

        String name = "demo_" + result.hashCode();
        model.addAttribute("id_doc", result.hashCode());
        model.addAttribute("domain", domain);
        File serverFile = new File("C:\\Users\\mario.flores\\" + name);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(result.getBytes());
        stream.close();

        return "signbtn";
    }

}  