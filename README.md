Atmosphere_ActiveMQ
===================
Prueba de concepto que combina un proyecto con {Atmosphere (v2.2.0) y Jersey (v1.18.1)} y lo alimenta a través de una cola ActiveMQ (v5.1.0).
El proyecto lo hemos probado desplegándolo sobre Tomcat (apache-tomcat-7.0.42).

Para realizar una prueba primero debes abrir un navegador y 2 pestañas e introducir la misma URL en ambas:
http://localhost:8888/atmosphere-activemq-chat/indexMap.html
Conforme vas haciendo click en la pantalla con el ratón se van generando diferentes "markers" en ambos navegadores (están conectados a través del proyecto Atmosphere-Jersey).
A continuación vamos a probar la recepción de mensajes a través de ActiveMQ:
1º.- Hay que levantar la cola (en la prueba de concepto a través de un servlet): 
http://localhost:8888/atmosphere-activemq-chat/receiveMessageListener
2º.- Enviar un mensaje con la posicíon deseada:
http://localhost:8888/atmosphere-activemq-chat/sendMessage

**********************************
Por otro lado también está disponible el ejemplo pero en lugar de con mapas y markers, partiendo del proyecto de un chat que tiene Atmosphere en GitHub.
Para probar el chat debemos abrir un navegador y 2 pestañas e introducir la misma URL en ambas:
http://localhost:8888/atmosphere-activemq-chat/indexTopic.html
Para probar la recepción de mensajes a través de ActiveMQ:
http://localhost:8888/atmosphere-activemq-chat/receiveMessageListenerChat
http://localhost:8888/atmosphere-activemq-chat/sendMessageChat

**********************************
Resumen de Pasos:

Habría que realizar los siguientes pasos para mostrarlo en una demo:

0.- Generar .war y desplegarlo en un servidor de aplicaciones

1.- Conectar varios clientes web (un par de ellos)
http://localhost:8888/atmosphere-activemq-chat/indexMap.html
http://localhost:8888/atmosphere-activemq-chat/indexMap.html

2.- Activar el listener para que escuche los mensajes de la cola ActiveMQ
http://localhost:8080/atmosphere-activemq-chat/receiveMessageListener

3.- Enviar un mensaje a la cola ActiveMQ
http://localhost:8080/atmosphere-activemq-chat/sendMessage

4.- Comprobar que en los clientes web se reciben los localizadores enviados desde la cola
