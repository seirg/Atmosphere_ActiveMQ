Atmosphere_ActiveMQ
===================
He adaptado el ejemplo de un chat que existía de en Atmosphere usando REST y Jersey. 
Todos los mensajes que se envían (a través del servidor) vía la cola ActiveMQ les llegan a los que están conectados al chat (además de los mensajes entre ellos).

Habría que realizar los siguientes pasos para mostrarlo en una demo:

0.- Generar .war y desplegarlo en un servidor de aplicaciones (yo he usado un Tomcat 7)

1.- Conectar varios clientes web (un par de ellos)
http://localhost:8080/atmosphere-activemq-chat/
http://localhost:8080/atmosphere-activemq-chat/

2.- Activar el listener para que escuche los mensajes de la cola ActiveMQ
http://localhost:8080/atmosphere-activemq-chat/receiveMessageListener

3.- Enviar un mensaje a la cola ActiveMQ
http://localhost:8080/atmosphere-activemq-chat/sendMessage

4.- Comprobar que en los clientes web se recibe el mensaje enviado desde la cola
