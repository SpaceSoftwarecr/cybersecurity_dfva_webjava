# dfva_webjava
Este proyecto es una demostración de como integrar dfva_java en una aplicación web, permite demostrar cómo se realiza la firma.

Está desarrollado con Maven + Hibernate + dfva_java , pero no conecta con una base de datos.

# Instalación 

Se sigue esta guía como proceso de instalación https://www.vultr.com/docs/how-to-install-apache-tomcat-8-on-centos-7

Instale java 

    yum install -y epel-release
    yum update
    yum install -y java-1.8.0-openjdk.x86_64 haveged

Cree el usuario tomcat

	sudo groupadd tomcat
	sudo mkdir /opt/tomcat
	sudo useradd -s /bin/nologin -g tomcat -d /opt/tomcat tomcat

Descarge tomcat desde la página oficial

    wget http://www-us.apache.org/dist/tomcat/tomcat-8/v8.5.45/bin/apache-tomcat-8.5.45.tar.gz
    tar -zxvf apache-tomcat-8.5.45.tar.gz -C /opt/tomcat --strip-components=1

Cambiando permisos

	cd /opt/tomcat
	sudo chgrp -R tomcat conf
	sudo chmod g+rwx conf
	sudo chmod g+r conf/*
	sudo chown -R tomcat logs/ temp/ webapps/ work/
	
	sudo chgrp -R tomcat bin
	sudo chgrp -R tomcat lib
	sudo chmod g+rwx bin
	sudo chmod g+r bin/*

Cree el archivo `/etc/systemd/system/tomcat.service` según el tutorial de referencia.

Active los servicios de tomcat 

	sudo systemctl start haveged.service
	sudo systemctl enable haveged.service
	sudo systemctl start tomcat.service
	sudo systemctl enable tomcat.service
	
Configure la gestión de tomcat editando /opt/tomcat/conf/tomcat-users.xml

	<role rolename="manager-gui"/>
	<user username="tomcat" password="s3cret" roles="manager-gui,admin-gui"/>
	
Copie `dfva_webjava-0.0.1-SNAPSHOT.war`a /opt/tomcat/

	cp dfva_webjava-0.0.1-SNAPSHOT.war /opt/tomcat/webapps/ROOT.war
	cp dfva_webjava-0.0.1-SNAPSHOT.war /opt/tomcat/webapps/dfva_webjava.war

Algunos archivos estáticos requieren de la ruta dfva_webjava/ por lo que la app se corre en 2 fases

Confie en la CA otorgada

	mv ca_ucr.crt /etc/pki/ca-trust/source/anchors/ca_ucr_cert.pem
	sudo update-ca-trust extract
	
Copie sus archivos de configuración de dfva_java en `/opt/tomcat/.dfva_java`

Verifique que puede firmar en el servicio.

# Archivos de interés 

Debido a que este es un proyecto de demostración y varios archivos no son necesarios para entender el procedimiento se describen cuales 
debe verificar

- `src/dfva_webjava/java/demo/rest/ControlaFirma.java`  las funciones importantes son:
```
    public ResponseEntity<DfvaRespuesta> firme(..)   // Busca el archivo en disco y lo envía a firmar
    public String check_firma(..)  // verifica el estado de la transacción
```

- `src/dfva_webjava/java/demo/rest/StartController.java` las funciones importantes son:
```
    public String createsign(...)   // Muestra el botón de firma y la ventana de proceso
    public HttpEntity<byte[]>  download(...) // al finalizar descarga el documento firmado
```

-   `src/dfva_webjava/java/demo/DfvaRespuesta.java`  las funciones importantes son:
```
    public class DfvaRespuesta implements Serializable 
    // Esta clase permite serializar la respuesta que se le entrega a dfva_html.
```

# Aspectos importantes 

Este proyecto solo pretende mostrar cómo usar dfva_java, por lo que no detalla algunas particulares de seguridad, por ejemplo es recomendable
guardar variables de control en la sesión para prevenir que entes externos puedan consultar las transacciones.
Además la información se almacena en disco y no en base de datos.

