# protorepo
Repositorio de prototipos

Para el desarrollo de este proyecto de software se utiliza como referencia la historia de ficción de los X-Men, donde el personaje Magneto requiere
un programa que le permita analizar secuencias de ADN para determinar cuales corresponden a mutantes y cuales corresponden a humanos, para lo cual
se tienen los siguientes criterios:

a. La secuencia de ADN enviada al programa, se corresponde con una matriz de datos de MxN
b. Si se encuentran dos casos de 4 letras repetidas consecutivas en sentido vertical, horizontal, diagonal o diagonal invertida, el resultado del 
análisis debe ser: HTTP 200-OK, lo cual significa que la secuencia de ADN corresponde a un mutante
c. En otro caso, el resultado del análisis debe ser: 403-Forbidden, lo cual significa que la secuencia de ADN corresponde a un humano


Por otra parte, se requiere consultar las estadísticas de los analisis de secuencias de ADN, para lo cual se debe generar una respuesta en formato JSON, como
el siguiente ejemplo: 
{"count_mutant_dna":40, "count_human_dna":100, "ratio":0.4}


Servicios API RESTful implementados

1. Servicio de análisis de secuencias de ADN

	Endpoint: https://my-project-mutant2.rj.r.appspot.com/api/mutant
	
	Especificación del Request HTTP
	Method: POST

	Ejemplo JSON de datos a enviar:
	{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}

	Respuestas esperadas:
	a. HTTP 200-OK
	b. 403-Forbidden

2. Servicio de consulta de las estadísticas

	Endpoint: https://my-project-mutant2.rj.r.appspot.com/api/stats

	Especificación de Request HTTP

	Method: POST

	Datos a enviar:
	No se envian datos

	Respuesta esperada (ejemplo):
	{"count_mutant_dna":40, "count_human_dna":100, "ratio":0.4}




Tecnologías de software utilizadas:

a. Java Spring Boot framework

b. Google Cloud App Engine

c. MongoDB Atlas Cloud
