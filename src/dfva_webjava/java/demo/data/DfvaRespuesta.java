package dfva_webjava.java.demo.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DfvaRespuesta implements Serializable {
      private static final long serialVersionUID = 1L;

       @JsonProperty("FueExitosaLaSolicitud")
       private boolean fueExitosaLaSolicitud;

       @JsonProperty("TiempoMaximoDeFirmaEnSegundos")
       private int tiempoMaximoDeFirmaEnSegundos;

       @JsonProperty("TiempoDeEsperaParaConsultarLaFirmaEnSegundos")
       private int tiempoDeEsperaParaConsultarLaFirmaEnSegundos;

       @JsonProperty("CodigoDeVerificacion")
       private String codigoDeVerificacion;

       @JsonProperty("IdDeLaSolicitud")
       private int idDeLaSolicitud;

       @JsonProperty("DebeMostrarElError")
       private boolean debeMostrarElError;

       @JsonProperty("DescripcionDelError")
       private String descripcionDelError;

       @JsonProperty("ResumenDelDocumento")
       private String resumenDelDocumento;

	public static DfvaRespuesta build(BrandRequest request) {
		return DfvaRespuesta.builder()
				.fueExitosaLaSolicitud(request.getName())
				.tiempoMaximoDeFirmaEnSegundos(request.getIdentifier())
				.tiempoDeEsperaParaConsultarLaFirmaEnSegundos(request.getAccount())
				.build();
	}

}
