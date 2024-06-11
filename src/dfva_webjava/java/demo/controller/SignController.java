package dfva_webjava.java.demo.controller;

import dfva_webjava.java.demo.service.SignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dfva_webjava.java.demo.DfvaRespuesta;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class SignController {

    @Autowired
    SignerService signerService;

    @RequestMapping(value = "/sign/{id}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<DfvaRespuesta> firme(@PathVariable("id") String id) {
        return new ResponseEntity<>(signerService.sign(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/check_sign/{id}", method = RequestMethod.GET)
    public String check_firma(@RequestParam("callback") String callback, @RequestParam("IdDeLaSolicitud") String requestId, @PathVariable("id") String id) throws IOException {
        return signerService.check(id, requestId, callback);
    }
}
