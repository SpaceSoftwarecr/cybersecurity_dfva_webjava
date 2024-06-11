package dfva_webjava.java.demo.data;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpHeaders;

@Data
@Builder
public class DownloadResponse {

    HttpHeaders headers;
    byte[] file;
}
