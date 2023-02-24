package com.ejerciciohashmap.ejerciciohashmap;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner; //Importamos la librería para pedir datos al ususario.
import java.util.HashMap; //Importamor la  librería para usar HashMap.
import java.util.ArrayList; //Importamnos la librería para ocupar arrays.

@SpringBootApplication
public class EjerciciohashmapApplication {

	//Función para obtener el promedio de notas por cada alumno.
	public static Double promedioNotas(ArrayList<Double> notas){ //Inicializamos la función, asignando como parámetro un array.
		Double suma = 0.0; //Creamos una variable acumuladora que sume las notas de los alumnos.
		for (int i = 0; i < notas.size(); i++) { //Creamos un for para recorrer el tamaño del array.
			suma += notas.get(i); //Hacemos la sumatoria de las notas.
		}
		return suma/notas.size(); //Retorna el resultado de la suma y se divide en el tamaño del array de notas (2).
	}

	//Función para obtener si el alumno aprueba /reprueba a partir del promedio de notas por alumno.
	public static Boolean aprobado(ArrayList<Double> notas, Double notaAprobatoria){ //Inicializamos la función con el nombre de aprobado y de parámetros el array "notas" y la nota aprobatoria.
		Double promedio = promedioNotas(notas); //Creamos la variable decimal que  se leasigne el valor de la función promedio.
		if(promedio >= notaAprobatoria){ 
			return true; //Si la nota es mayor o igual a nota aprobatoria, se da por aprobado.
		}else{
			return false; //Si la nota es menor a la nota aprobatoria, se da por reprobado.
		}
	}

	//Función para obtener si el alumno está sobre/debajo el promedio general del curso.
	public static void sobrePromedio(Double promedioGeneral, ArrayList<Double> notas, String nombreAlum){ //Inicializamos la función con el nombre y de parámetros el promedio general, el array de notas y el nombre del alumno.
		Double promedioAlumno = promedioNotas(notas); //Se le asigna a la variable promedio del alumno la función del promedio de notas del alumno.
		if(promedioAlumno>promedioGeneral){
			System.out.println("El alumno "+nombreAlum+" está sobre el promedio"); //Si el promedio del alumno está sobre el promedio general.
		}else if(promedioAlumno == promedioGeneral){
			System.out.println("El alumno "+nombreAlum+" es igual al promedio general"); //Si " está igual que el promedio general.
		}else{
			System.out.println("El alumno "+nombreAlum+" está bajo el promedio"); //Si está debajo del promedio general.
		}
	}

	public static void main(String[] args) {
		
		//Crear un escaner para poder ingresar los nombres de los alumnos, la cantidad de alumnos y sus respectivas notas. Además definimos nota aprobatoria.
		Scanner usuario = new Scanner(System.in);
		String nomAlum = ""; //Creamos una variable general para los nombres
		Double notaAprobatoria = 4.0; //Creamos una constante general para la nota aprobatoria
		HashMap <String, ArrayList<Double>> libroClase = new HashMap<String, ArrayList<Double>>(); //Creamos el HashMap en donde estarán los estudiantes con sus notas.
		
		//Creamos dos variables acumulativas, en una se ingresará la cantidad e alumnos, en la otra la cantidad de notas.
		int cantAlum; //Variable general que acumule la cantidad de alumnos ingresados.
		int cantNotas; //Variable general que acumule la cantidad de notas por alumno ingresadas.
		
		//Hacemos un do...while para que se ingrese bien la cantidad de alumnos. Se cumplirá lo primero, siempre y cuando no se haga lo segundo.
		do{
			System.out.print("Indique la cantidad de alumnos que va a ingresar: ");
			cantAlum = usuario.nextInt();
			if(cantAlum <= 0){
				System.out.print("La cantidad de alumnos debe ser mayor a cero, porfavor intente de nuevo");
			}

		}while(cantAlum <= 0);

		//Volvemos a hacer un do...while para indicar las notas por alumno, usando la misma lógica anterior.
		do{
			System.out.print("Indique la cantidad de notas por alumnos: ");
			cantNotas = usuario.nextInt();
			if(cantNotas <= 0){
				System.out.print("La cantidad de notas debe ser mayor a cero, por favor entente mas tarde");
			}
		}while(cantNotas <= 0);
		
		//Se ingresan los nombres del alumnos en relación al recorrido hecho según la cantidad de alumnos.
		for(int i = 1; i <= cantAlum; i++){ //Creamos un for para recorrer la cantidad máxima a solicitar de alumnos.
			usuario.nextLine(); //El scanner funcionará como String.
			ArrayList <Double> notasAlumnos = new ArrayList<Double>(); //creamos el array para almacenar las notas de los alumnos.
			System.out.print("Ingresa nombre del alumno: "); //Se solicita al usuario que ingrese los nombres.
			nomAlum = usuario.nextLine(); //Variable en donde se ingresan los nombres definida anteriormente como variable general.
			for(int x = 1; x <= cantNotas; x++){ //Creamos un for para recorrer la cantidad de notas máximas a solicitar por alumno.
				System.out.print("Ingresa nota " +x+ " del alumno "+ nomAlum +": "); //Se solicita que se ingresen las notas.
				Double nota = usuario.nextDouble(); //Variable local en donde se guarden las notas que ingrese el ususario.
				notasAlumnos.add(nota); //Se agregan las notas al array.
			}
			libroClase.put(nomAlum, notasAlumnos); //Se agregan los nombres de los alumnos (llaves) y las notas de los alumnos (array) al HashMap.
		}
		
		//Creamos la interfaz del munú mediante una variable general en inicio 0 y con un do...while que permita unicamente cuatro selecciones de usuario.
		int opcion = 1; 

		while(opcion != 0){

			do{
				System.out.println("**************Comienzo de Menú***************");
				System.out.println("Bienvenido/a");
				System.out.println("Seleccione 1 si quiere obtener el promedio de las notas por alumno.");
				System.out.println("Seleccione 2 si quiere ver si el alumno aprueba o reprueba");
				System.out.println("Seleccione 3 para ver si la nota está sobre o por debajo del promedio general");
				System.out.println("Seleccione 0 para salir del menú");
				System.out.print("Seleccione su opción: ");
				opcion = usuario.nextInt();
				
			}while(opcion < 0 || opcion > 3);

			//Creamos las condiciones del menú, es decir, las diferentes opciones que debe ingresar el usuario.
			if(opcion == 1){
				for(String i : libroClase.keySet()){ //Creamos un for con el set de llaves del HashMap.
					Double promAlum = promedioNotas(libroClase.get(i)); //Creamos variable local definida como la función promedio de notas del alumno más el HashMap, obteniendo i como la llave.
					System.out.println("El promedio del alumno: "+ i +" es de: " + promAlum);
				}
			}else if (opcion == 2){
				for(String i : libroClase.keySet()){ //La misma lógica anterior, obtenermos el set de llaves del HashMap
					Boolean aprobar = aprobado(libroClase.get(i), notaAprobatoria); //Creamos un Boolean que determine si aprueba o reprueba, asignandole la función aprobado, obteniendo i como la llave.
					if(aprobar){
						System.out.println("El alumno/a "+i+" está aprobado"); //Si "aprobar" es mayor a 4.0.
					}else{
						System.out.println("El alumno/a "+i+" está reprobado"); //Si "aprobar" es menor a 4.0
					}
					
				}
			}else if(opcion == 3){
				Double sumaPromedio = 0.0; //Se define variable local como dato neutro (0.0)
				for(String i : libroClase.keySet()){ //Se realiza un for nuevamente.
					sumaPromedio = sumaPromedio + promedioNotas(libroClase.get(i)); //La variable se define como la suma de los promedios de los alumnos.
					
				}
				Double promedioGeneral = sumaPromedio / cantAlum; //See crea una variable local del promedio general definida como la suma de los proemdios deividido en la cantidad de alumnos.
				for(String i : libroClase.keySet()){ //Se crea un for nuevamente.
					sobrePromedio(promedioGeneral, libroClase.get(i), i); //Se llama a la función sobrePromedio  en donde se determina si el promedio del alumno está sobre/debajo/igual al promedio general. Se determina según promedio general y el HashMap.
				}
			}else{
				System.out.println("Gracias por cerrar el menú, hasta pronto :D"); //Se cierra el menú.
			}

		}

		

		
	}

}
