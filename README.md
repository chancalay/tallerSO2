# Taller 1: Comunicación entre Procesos y Objetos Distribuidos

Este proyecto implementa un sistema distribuido utilizando **Java RMI (Remote Method Invocation)** para coordinar la sincronización de tareas concurrentes entre dos procesos independientes: un **Albañil** y un **Cadete**. El sistema resuelve el clásico problema del *Productor-Consumidor* mediante un monitor distribuido que gestiona el acceso a un recurso compartido (un balde de cemento).

## 🎯 Objetivos del Taller
* Implementar mecanismos de comunicación y sincronización entre procesos distribuidos.
* Utilizar la invocación de métodos remotos (RMI) sobre una arquitectura de red.
* Gestionar el ciclo de vida de objetos distribuidos en Java.
* Trabajar de forma productiva aplicando herramientas de control de versiones.

## 🛠️ Recursos Tecnológicos
* **Lenguaje:** Java (JDK 17 o superior)
* **Tecnología de Distribución:** Java RMI (Remote Method Invocation)
* **Entorno de Desarrollo:** Visual Studio Code
* **Control de Versiones:** Git / TortoiseGit / GitHub

## 🏗️ Arquitectura del Sistema
El sistema se basa en un **Objeto Distribuido** centralizado que actúa como Monitor Sincronizado. Los procesos interactúan de la siguiente manera:
* **El Balde (Objeto Remoto):** Mantiene el estado de su ubicación (`baldeArriba`). Expone métodos sincronizados utilizando `wait()` y `notifyAll()` para garantizar la exclusión mutua y evitar condiciones de carrera.
* **El Albañil (Proceso Cliente 1):** Ubicado en el primer piso. Baja el balde vacío y espera a que el cadete lo llene y lo suba.
* **El Cadete (Proceso Cliente 2):** Ubicado en la planta baja. Llena el balde con cemento y lo sube, esperando si el balde ya se encuentra arriba.

## 📁 Estructura del Proyecto
* `BaldeInterface.java`: Interfaz remota que define el contrato de operaciones permitidas sobre el balde.
* `BaldeImpl.java`: Implementación del objeto remoto y lógica del monitor (sincronización de hilos).
* `ServidorRMI.java`: Inicializa el registro RMI (puerto 1099) y publica el objeto remoto bajo el alias "ObjetoBalde".
* `AlbanilProceso.java`: Proceso cliente distribuido que simula las acciones del albañil en ciclos de trabajo.
* `CadeteProceso.java`: Proceso cliente distribuido que simula las acciones del cadete en ciclos de trabajo.

## 🚀 Instrucciones de Ejecución

Para simular el entorno distribuido, se deben abrir **tres terminales independientes** en el entorno de desarrollo (o ejecutar los procesos en computadoras distintas dentro de la misma red local):

### 1. Compilación
En cualquiera de las terminales, compilar todos los archivos `.java` del proyecto:
```bash
"javac *.java"

### 2. Levantar el Servidor RMI En la Terminal 1, iniciar el servidor que instanciará y publicará el objeto distribuido:

Bash
java ServidorRMI
Nota: Esta terminal quedará activa en modo de escucha permanente.

3. Ejecutar el Proceso Cadete
En la Terminal 2, iniciar el cliente correspondiente a la planta baja:

Bash
java CadeteProceso
Observarás que se bloquea inicialmente esperando que el balde sea bajado por el albañil.

4. Ejecutar el Proceso Albañil
En la Terminal 3, iniciar el cliente correspondiente al primer piso:

Bash
java AlbanilProceso
A partir de este momento, ambos procesos comenzarán a coordinar sus tareas de forma automática a través de la red local.

🛑 Detener el Sistema
Una vez finalizada la simulación y completadas las jornadas de los clientes, dirígete a la Terminal 1 (donde corre el ServidorRMI) y presiona las teclas Ctrl + C para liberar el puerto 1099 y detener el servicio.