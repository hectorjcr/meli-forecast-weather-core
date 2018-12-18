# meli-forecast-core-ms
CONTROLADOR PRINCIPAL PARA LAS PREDICCIONES CLIMATICAS


## Descripción

Operaciones pertinentes al control de data e información del clima, genera la informacion que se almacena en la base de datos

| URL               | https://meli-forecast-core-ms.herokuapp.com/api/forecast/dayforecast/{id}  |
| ----------        | ------------------------------- |
| API               | /api/forecast/dayforecast/{id}  |
| Método            | GET                        |
| Produces          | application/json                |
| Consumes          | application/json                |
| Path Parametes    | {id}=Numero del dia a consultar |
| Response Status   | 200 OK - OK                     |
| Response Object   | String                          |
| __Descripcion__       | Clima del dia consultado a partir de calculo matemático, el valor no está almacenado        |

| URL               | https://meli-forecast-core-ms.herokuapp.com/api/forecast/fakefordebugg/{fromday}/{atDay}  |
| ----------        | ------------------------------- |
| API            | GET                        |
| Método            | GET                        |
| Produces          | application/json                |
| Consumes          | application/json                |
| Path Parametes    | {fromday} = dia de inicior {atDay}</code> = Dia final |
| Response Status   | 200 OK - OK                     |
| Response Object   | String                          |
| Descripcion       | su única función es chequear conexion con el microservicio que accede a la base de datos        |

| URL               | https://meli-forecast-core-ms.herokuapp.com/api/forecast/generaterecords/{fromday}/{atday}  |
| ----------        | ------------------------------- |
| API            | GET                        |
| Método            | GET                        |
| Produces          | application/json                |
| Consumes          | application/json                |
| Path Parametes    | {fromday} = dia de inicior {atDay}</code> = Dia final |
| Response Status   | 200 OK - OK                     |
| Response Object   | String                          |
| Descripcion       | Genera los registros climatologicos diarios, es activado por el Job scheduler y le realiza las request de escritura al microservicio de la BD        |

| URL               | https://meli-forecast-core-ms.herokuapp.com/api/forecast/totalreport  |
| ----------        | ------------------------------- |
| API            | GET                        |
| Método            | GET                        |
| Produces          | application/json                |
| Consumes          | application/json                |
| Path Parametes    | N/A |
| Response Status   | 200 OK - OK                     |
| Response Object   | String                          |
| Descripcion       | Genera el reporte total de periodos de clima        |


La solución del ejercicio a las preguntas
<ul>
 <li>¿Cuantos periodos de sequia habrá?</li>
 <li>¿Cuantos periodos de lluvia habra y que dia sera el pico maximo de lluvia?</li>
 <li>Cuantos periodos de condiciones optimas de presión y temperatura habrá?</li>
 </ul>
 <p>debe ingresar la siguiente URL</p>
		<p><code>https://meli-forecast-core-ms.herokuapp.com/api/forecast/totalreport</code></p>
	<p>Este genera la siguiente respuesta</p>
    <code>
		{<br>			
			"responseText": "Total periodos de lluvia: 1760 Total periodos de Sequia: 20 Total periodos de Condiciones optimas: 20 Total de Periodos Nublados: 1839 Dia con pico maximo de lluvia: 67",
			"status": 200		<br>
		}
        </code>

##Procedimientos para el funcionamiento

###Direccion de la aplicacion desplegada en Heroku

		https://meli-forecast-core-ms.herokuapp.com/


###Direccion del repositorio en heroku para ser clonadonado

	https://git.heroku.com/meli-forecast-core-ms.git
###Direccion del repositorio en GitHub para ser clonado o ver su codigo en linea

	https://github.com/hectorjcr/meli-forecast-weather-core.git
    
###Direccion de la documentacion para la API REST

		https://meli-forecast-core-ms.herokuapp.com/jsondoc-ui.html
		dentro de la barra de busqueda ingresar esta direccion: https://meli-forecast-core-ms.herokuapp.com/jsondoc




