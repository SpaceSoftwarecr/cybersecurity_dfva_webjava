<jsp:include page="header.jsp"/>


<div class="row">
    <div class="col-md-10 offset-md-1">

        <br><br><br>
        <h1 class="text-heading"> Documento a firmar:</h1>
        <pre>
            
            </pre>

        <button class="btn btn-success" id="BotonDeFirmar"
                data-fva="true" data-url="/dfva_webjava/sign/${ id_doc }"
                data-img_ayuda="/dfva_webjava/static/Imagenes/Ayuda.png"
                data-img_autenticador="/dfva_webjava/static/Imagenes/Autenticador-v2.png"
                data-img_firma="/dfva_webjava/static/Imagenes/Firmador-v2.png"
                data-urlcss="/dfva_webjava/static/css/Bccr.Fva.ClienteInterno.Firmador-1.0.2.css"

                data-urlconsultafirma="/dfva_webjava/check_sign/${id_doc}"
                data-dominio="${ domain }"
                data-successurl="/dfva_webjava/download/${id_doc}"
                data-parautenticarse="al Sitio de Verificaci&oacute;n"
                data-mensajedeerror="No se pudo realizar la firma en el sitio de verificaci&oacute;n."
        > Firmar documento
        </button>


    </div>
</div>
<br><br>
<script src="static/js/jquery-1.9.1.min.js"></script>
<script src="static/js/Bccr.Fva.ClienteInterno.Firmador-1.0.3.js" type="text/javascript"></script>
<script src="static/js/firma-verificacion-1.0.3.js"></script>

<jsp:include page="footer.jsp"/>

