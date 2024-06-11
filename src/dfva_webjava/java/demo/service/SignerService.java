package dfva_webjava.java.demo.service;

import dfva_webjava.java.demo.DfvaRespuesta;
import dfva_webjava.java.demo.data.DownloadResponse;

import java.io.IOException;

public interface SignerService {

    DfvaRespuesta sign(String id);

    String check(String id, String requestId, String callback) throws IOException;

    DownloadResponse download(String id);
}
