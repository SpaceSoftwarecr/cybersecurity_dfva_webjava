package dfva_webjava.java.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;

import dfva_webjava.java.demo.data.DownloadResponse;
import dfva_webjava.java.demo.service.SignerService;
import dfva_webjava.java.demo.util.SignerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import dfva_webjava.java.demo.DocumentSign;

@Controller
public class StartController {

    @Autowired
    SignerService signerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String display() {
        return "index";
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public HttpEntity<byte[]> download(@PathVariable("id") String id) {
        DownloadResponse downloadResponse = signerService.download(id);
        return new HttpEntity<>(downloadResponse.getFile(), downloadResponse.getHeaders());
    }

    @RequestMapping(value = "/create_sign", method = RequestMethod.POST)
    @ModelAttribute
    public String createsign(HttpServletRequest request, @RequestParam("file_uploaded") MultipartFile file, @RequestParam("identificacion") String identification, @RequestParam("resumen") String resume, @RequestParam("doc_format") String doc_format, @RequestParam(value = "razon", required = false) String razon, @RequestParam(value = "lugar", required = false) String lugar, Model model) throws IOException {

        DocumentSign doc = SignerUtil.createDocumentSign(file, identification, resume, doc_format, razon, lugar);
        String result = new ObjectMapper().writeValueAsString(doc);
        String domain = request.getScheme() + "://" + request.getHeader("host");

        String name = "demo_" + result.hashCode();
        model.addAttribute("id_doc", result.hashCode());
        model.addAttribute("domain", domain);
        File serverFile = new File("C:\\Users\\mario.flores\\" + name);
        BufferedOutputStream stream = new BufferedOutputStream(Files.newOutputStream(serverFile.toPath()));
        stream.write(result.getBytes());
        stream.close();

        return "signbtn";
    }

}  