# meli-forecast-core-ms
CONTROLADOR PRINCIPAL PARA LAS PREDICCIONES CLIMATICAS

En una galaxia lejana, existen tres civilizaciones. Vulcanos, Ferengis y Betasoides. Cada
civilización vive en paz en su respectivo planeta.
Dominan la predicción del clima mediante un complejo sistema informático.
A continuación el diagrama del sistema solar.

Premisas:
* El planeta Ferengi se desplaza con una velocidad angular de 1 grados/día en sentido
horario. Su distancia con respecto al sol es de 500Km.
* El planeta Betasoide se desplaza con una velocidad angular de 3 grados/día en sentido
horario. Su distancia con respecto al sol es de 2000Km.
* El planeta Vulcano se desplaza con una velocidad angular de 5 grados/día en sentido
anti­horario, su distancia con respecto al sol es de 1000Km.
* Todas las órbitas son circulares.
Cuando los tres planetas están alineados entre sí y a su vez alineados con respecto al sol, el
sistema solar experimenta un período de sequía.

  ![Con titulo](pictures/alineadosconsol.png "JSONDocs")
  
  _Antes que nada debemos calcular las posiciones X,Y de cada planeta y del sol para poder aplicar los modelos geométricos de línea, área y perímetro. teniendo la velocidad angular(w) y el radio podemos determinar el ángulo de desplazamiento del planeta en un dia especifico.
  
         w = anguloDesp / tiempo  => anguloDesp = w * tiempo
	 
	 una vez calculado el angulo de desplazamiento tenemos convertimos el angulo que esta en grados a radianes y sustituimos en los modelos X = r * cos(angulo en radianes) y Y = r * sin(angulo en radianes)_
  
  _Para determinar la alineacion de planetas entre si y a su vez con el sol, utilicé el modelo de colinealidad que es una derivación del modelo de la recta, pero sin tener en consideración cuando la recta es paralela al eje Y, debido a que la pendiente es infinita, una ventaja de usar este modelo es que la ejecución se mantiene en O(1)_
   dos puntos que forman una recta P1(x1,y1) y P2(x2,y2) un terce punto P3(x,y) es colineal si al sustituir los valores de X,Y en el modelo el resultado es cero.
  	
  	s = (y2 - y1) * x + (x1 - x2) * y + (x2 * y1 - x1 * y2); | alineados si s=0
	en el caso del sol P3(0,0)=> x=0, y=0
        
## Descripción

Operaciones pertinentes al control de data e información del clima, genera la informacion que se almacena en la base de datos

### Calculo del clima de un día en particular
| URL               | https://meli-forecast-core-ms.herokuapp.com/api/forecast/dayforecast/{id}  |
| ----------        | ------------------------------- |
| __API__               | /api/forecast/dayforecast/{id}  |
| __Método__            | GET                        |
| __Produces__          | application/json                |
| __Consumes__          | application/json                |
| __Path Parametes__    | {id}=Numero del dia a consultar |
| __Response Status__   | 200 OK - OK                     |
| __Response Object__   | String                          |
| __Descripcion__       | Clima del dia consultado a partir de calculo matemático, el valor no está almacenado        |


### Para probar conectividad con el microservicio
| URL               | https://meli-forecast-core-ms.herokuapp.com/api/forecast/fakefordebugg/{fromday}/{atDay}  |
| ----------        | ------------------------------- |
| __API__            | GET                        |
| __Método__            | GET                        |
| __Produces__          | application/json                |
| __Consumes__          | application/json                |
| __Path Parametes__    | {fromday} = dia de inicior {atDay}</code> = Dia final |
| __Response Status__   | 200 OK - OK                     |
| __Response Object__   | String                          |
| __Descripcion__       | su única función es chequear conexion con el microservicio que accede a la base de datos        |


### Genera todos los registros climáticos diarios en un periodo de 10 años
| URL               | https://meli-forecast-core-ms.herokuapp.com/api/forecast/generaterecords/{fromday}/{atday}  |
| ----------        | ------------------------------- |
| __API__            | GET                        |
| __Método__            | GET                        |
| __Produces__          | application/json                |
| __Consumes__          | application/json                |
| __Path Parametes__    | {fromday} = dia de inicior {atDay}</code> = Dia final |
| __Response Status__   | 200 OK - OK                     |
| __Response Object__   | String                          |
| __Descripcion__       | Genera los registros climatologicos diarios, es activado por el Job scheduler y le realiza las request de escritura al microservicio de la BD        |


### Genera un reporte general en base al calculo de los periodos de sequía, lluvia pico máximo de lluvia, y condiciones óptimas de presion y temperatura
| URL               | https://meli-forecast-core-ms.herokuapp.com/api/forecast/totalreport  |
| ----------        | ------------------------------- |
| __API__            | GET                        |
| __Método__            | GET                        |
| __Produces__          | application/json                |
| __Consumes__          | application/json                |
| __Path Parametes__    | N/A |
| __Response Status__   | 200 OK - OK                     |
| __Response Object__   | String                          |
| __Descripcion__       | Genera el reporte total de periodos de clima        |


## La solución del ejercicio a las preguntas

 1.- ¿Cuantos periodos de sequia habrá?
 2.- ¿Cuantos periodos de lluvia habra y que dia sera el pico maximo de lluvia?
 3.- Cuantos periodos de condiciones optimas de presión y temperatura habrá?
 
  Debe ingresar la siguiente URL</p>
 
		https://meli-forecast-core-ms.herokuapp.com/api/forecast/totalreport
  Este genera la siguiente respuesta
  
  	:::Json
		{		
		"responseText": "Total periodos de lluvia: 1760 Total periodos de Sequia: 20 Total periodos de Condiciones optimas: 20 Total de Periodos Nublados: 1839 Dia con pico maximo de lluvia: 67",
		"status": 20
		}
		
## Procedimientos para el funcionamiento

### Direccion de la aplicacion desplegada en Heroku

		https://meli-forecast-core-ms.herokuapp.com/


### Direccion del repositorio en heroku para ser clonadonado

	https://git.heroku.com/meli-forecast-core-ms.git
### Direccion del repositorio en GitHub para ser clonado o ver su codigo en linea

	https://github.com/hectorjcr/meli-forecast-weather-core.git
    
### Direccion de la documentacion para la API REST

		https://meli-forecast-core-ms.herokuapp.com/jsondoc-ui.html
	
	dentro de la barra de busqueda ingresar esta direccion: 
		https://meli-forecast-core-ms.herokuapp.com/jsondoc

![Con titulo](pictures/captura.png "JSONDocs")



