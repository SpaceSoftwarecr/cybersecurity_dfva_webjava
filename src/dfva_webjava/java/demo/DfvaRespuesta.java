package dfva_webjava.java.demo;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
public class DfvaRespuesta implements Serializable {
      private static final long serialVersionUID = 1L;

       @JsonProperty("FueExitosaLaSolicitud")
       private boolean FueExitosaLaSolicitud;

       @JsonProperty("TiempoMaximoDeFirmaEnSegundos")
       private int TiempoMaximoDeFirmaEnSegundos;

       @JsonProperty("TiempoDeEsperaParaConsultarLaFirmaEnSegundos")
       private int TiempoDeEsperaParaConsultarLaFirmaEnSegundos;

       @JsonProperty("CodigoDeVerificacion")
       private String CodigoDeVerificacion;

       @JsonProperty("IdDeLaSolicitud")
       private int IdDeLaSolicitud;

       @JsonProperty("DebeMostrarElError")
       private boolean DebeMostrarElError;
       
       @JsonProperty("DescripcionDelError")
       private String DescripcionDelError;
       
       @JsonProperty("ResumenDelDocumento")
       private String ResumenDelDocumento;
       
	public DfvaRespuesta(boolean fueExitosaLaSolicitud, int tiempoMaximoDeFirmaEnSegundos,
			int tiempoDeEsperaParaConsultarLaFirmaEnSegundos, String codigoDeVerificacion, int idDeLaSolicitud,
			boolean debeMostrarElError, String descripcionDelError,
			String resumenDelDocumento) {
		super();
		FueExitosaLaSolicitud = fueExitosaLaSolicitud;
		TiempoMaximoDeFirmaEnSegundos = tiempoMaximoDeFirmaEnSegundos;
		TiempoDeEsperaParaConsultarLaFirmaEnSegundos = tiempoDeEsperaParaConsultarLaFirmaEnSegundos;
		CodigoDeVerificacion = codigoDeVerificacion;
		IdDeLaSolicitud = idDeLaSolicitud;
		DebeMostrarElError = debeMostrarElError;
		DescripcionDelError = descripcionDelError;
		ResumenDelDocumento = resumenDelDocumento;
	}

	public boolean isFueExitosaLaSolicitud() {
		return FueExitosaLaSolicitud;
	}

	public void setFueExitosaLaSolicitud(boolean fueExitosaLaSolicitud) {
		FueExitosaLaSolicitud = fueExitosaLaSolicitud;
	}

	public int getTiempoMaximoDeFirmaEnSegundos() {
		return TiempoMaximoDeFirmaEnSegundos;
	}

	public void setTiempoMaximoDeFirmaEnSegundos(int tiempoMaximoDeFirmaEnSegundos) {
		TiempoMaximoDeFirmaEnSegundos = tiempoMaximoDeFirmaEnSegundos;
	}

	public int getTiempoDeEsperaParaConsultarLaFirmaEnSegundos() {
		return TiempoDeEsperaParaConsultarLaFirmaEnSegundos;
	}

	public void setTiempoDeEsperaParaConsultarLaFirmaEnSegundos(int tiempoDeEsperaParaConsultarLaFirmaEnSegundos) {
		TiempoDeEsperaParaConsultarLaFirmaEnSegundos = tiempoDeEsperaParaConsultarLaFirmaEnSegundos;
	}

	public String getCodigoDeVerificacion() {
		return CodigoDeVerificacion;
	}

	public void setCodigoDeVerificacion(String codigoDeVerificacion) {
		CodigoDeVerificacion = codigoDeVerificacion;
	}

	public int getIdDeLaSolicitud() {
		return IdDeLaSolicitud;
	}

	public void setIdDeLaSolicitud(int idDeLaSolicitud) {
		IdDeLaSolicitud = idDeLaSolicitud;
	}

	public boolean isDebeMostrarElError() {
		return DebeMostrarElError;
	}

	public void setDebeMostrarElError(boolean debeMostrarElError) {
		DebeMostrarElError = debeMostrarElError;
	}

	public String getDescripcionDelError() {
		return DescripcionDelError;
	}

	public void setDescripcionDelError(String descripcionDelError) {
		DescripcionDelError = descripcionDelError;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getResumenDelDocumento() {
		return ResumenDelDocumento;
	}

	public void setResumenDelDocumento(String resumenDelDocumento) {
		ResumenDelDocumento = resumenDelDocumento;
	}

}
