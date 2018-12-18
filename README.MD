# meli-forecast-core-ms
CONTROLADOR PRINCIPAL PARA LAS PREDICCIONES CLIMATICAS


## Descripción

Operaciones pertinentes al control de data e información del clima, genera la informacion que se almacena en la base de datos
|Método|API|Produces|Consumes|Path Parameters|Response Status|Response Object|Descripcion|
|:GET:|:/api/forecast/dayforecast/{id}:|:application/json:|:application/json:|:{id}=Numero del dia a consultar:|:200 OK - OK:|:String:|:Clima del dia consultado:|
<table>
<tr>
    <td>Método</td>
    <td>API</td>
    <td>Produces</td>
    <td>Consumes</td>
    <td>Path Parameters</td>
    <td>Response status code</td>
    <td>Response Object</td>
    <td>Descripción</td>    
</tr>
<tr>
    <td>GET</td>    
    <td><code>/api/forecast/dayforecast/{id}</code></td>
    <td><code>application/json</code></td>
    <td><code>application/json</code></td>
    <td><code>{id}</code> = Numero del dia a consultar</td>
    <td><code>200 OK - OK</code></td>
    <td>String</td>
    <td>Clima del dia consultado</td>
</tr>
<tr>
    <td>GET</td>
    <td><code>/api/forecast/fakefordebugg/{fromday}/{atDay}</code></td>
    <td><code>application/json</code></td>
    <td><code>application/json</code></td>
    <td><code>{fromday}</code> = dia de inicio | <code>{atDay}</code> = Dia final</td>
    <td><code>200 OK - OK</code></td>
    <td>String</td>
    <td>su única función es chequear conexion con el microservicio que accede a la base de datos</td>
</tr>
<tr>
    <td>GET</td>
    <td><code>/api/forecast/generaterecords/{fromday}/{atday}</code></td>
    <td><code>application/json</code></td>
    <td><code>application/json</code></td>
    <td><code>{fromday}</code> = dia de inicio | <code>{atDay}</code> = Dia final</td>
    <td><code>200 OK - OK</code></td>
    <td>String</td>
    <td>Genera los registros climatologicos diarios, es activado por el Job scheduler y le realiza las request de escritura al microservicio de la BD</td>
</tr>
<tr>
    <td>GET</td>
    <td><code>/api/forecast/totalreport</code></td>
    <td><code>application/json</code></td>
    <td><code>application/json</code></td>
    <td><code>N/A</td>
    <td><code>200 OK - OK</code></td>
    <td>String</td>
    <td>Genera el reporte total de periodos de clima</td>
</tr>
</table>

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




