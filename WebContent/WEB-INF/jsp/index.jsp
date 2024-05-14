<jsp:include page="header.jsp"/>

<h1 class="text-center"> Agregue un archivo para firmar </h1>
<br>

<form method="POST" action="create_sign" enctype="multipart/form-data">


    <div class="form-group">
        <label for="file_uploaded">Archivo a firmar</label>
        <input type="file" class="form-control-file" name="file_uploaded" id="file_uploaded">
    </div>
    <div class="form-group">
        <label for="identificacion">Identificaci�n</label>
        <input type="text" maxlength="250" required class="form-control" name="identificacion"
               pattern="(0\d-\d{4}-\d{4})|([5|1]\d{11})"
               id="identificacion" placeholder="01-0123-0456">
        <p class="help-text">Ayuda: Para un nacional 0#-####-#### para un DIDI 5########### para un DIMEX
            1###########</p>

    </div>
    <div class="form-group">
        <label for="resumen">Resumen</label>
        <input type="text" maxlength="250" required class="form-control" name="resumen"
               id="resumen">
    </div>
    <div class="form-group">
        <label for="doc_format">Formato</label>
        <select class="form-control" name="doc_format" id="doc_format">
            <option value="xml_cofirma"> Xml Cofirma</option>
            <option value="xml_contrafirma">Xml Contrafirma</option>
            <option value="odf">Open Document Format</option>
            <option value="msoffice"> Microsoft Office</option>
            <option value="pdf" selected>PDF</option>
        </select>
    </div>
    <div class="form-group">
        <label for="razon">Razon (Solo requerida en pdf)</label>
        <input type="text" maxlength="150" class="form-control" name="razon" id="razon">
    </div>

    <div class="form-group">
        <label for="lugar">Lugar (Solo requerida en pdf)</label>
        <input type="text" maxlength="125" class="form-control" name="lugar" id="lugar">
    </div>
    <button type="submit" class="btn btn-primary">Enviar a firmar</button>
</form>


<br><br>
<jsp:include page="footer.jsp"/>