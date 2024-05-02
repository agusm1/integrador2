package com.example.integrador2;

import com.example.integrador2.DTO.CarreraDTO;
import com.example.integrador2.DTO.ReporteCarreraDTO;
import com.example.integrador2.entities.Carrera;
import com.example.integrador2.entities.Estudiante;
import com.example.integrador2.factory.AbstractFactory;
import com.example.integrador2.factory.FactoryMySQL;
import com.example.integrador2.repository.CERepository;
import com.example.integrador2.repository.CarrRepository;
import com.example.integrador2.repository.EstuRepository;
import com.example.integrador2.utils.CargarDatos;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {
        FactoryMySQL mysql = (FactoryMySQL) AbstractFactory.getFactory(1);
        String urlEstudiantes="./src/main/resources/estudiantes.csv";
        String urlCarreras="./src/main/resources/carreras.csv";
        String urlCarreEstu="./src/main/resources/estudianteCarrera.csv";
        CargarDatos cargarCsv=new CargarDatos(mysql.getEntManager());
        cargarCsv.insertarCarreras(urlCarreras);
        cargarCsv.insertarEstudiantes(urlEstudiantes);
        cargarCsv.insertarCarreraEstudiante(urlCarreEstu);

        /**
         * Dar de alta un estudiante
        */
        Estudiante ingresante=new Estudiante(41542103L,8319L,"Edinson","Cavani",31,"Male","La Boca");
        EstuRepository repoEstudiante=mysql.getEstudiante();
        repoEstudiante.InsertarEstudiante(ingresante);
        System.out.println("--------------------------------------------------------------");
        System.out.println("Se registro correctamente al alumno "+ingresante.getNombres()+" "+ingresante.getApellido());
        System.out.println("--------------------------------------------------------------");
        /**
         * Matricular un estudiante en una carrera
        */
        CarrRepository repoCarrera=mysql.getCarrera();
        CERepository repCE=mysql.getCarreraEstudiante();
        Carrera c=repoCarrera.getCarrera(2L);
        Estudiante e=repoEstudiante.getEstudianteByNumLibreta(8319L);
        Long id=130L;
        repCE.matricular(e,c,id);
        System.out.println("----------------------------------------------------");
        System.out.println("El estudiante "+e.getNombres()+" "+e.getApellido()+" se matriculó en la carrera : "+c.getCarrera());
        System.out.println("----------------------------------------------------");
        /**
         * Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.(Orden descendente x apellidos)
        */
        List<Estudiante> estudiantesOrd=repoEstudiante.getEstudiantesByOrdenDelApellido();
        System.out.println("Alumnos ordenados de forma descendente por su apellido: ");
        imprimirLista(estudiantesOrd);
        System.out.println("------------------------------------------------");
        /**
         * Recuperar un estudiante, con base en su número de libreta universitaria.
        */
        Long lU=96779L;
        Estudiante estudiantexLU=repoEstudiante.getEstudianteByNumLibreta(96779L);
        System.out.println("La libreta universitaria  "+lU+" corresponde al estudiante: ");
        System.out.println(estudiantexLU.toString());
        System.out.println("-----------------------------------------------");
        /**
         * Recuperar todos los estudiantes, en base a su género.
        */
        String genero="Male";
        List<Estudiante> generoX=repoEstudiante.GetEstudiantesByGenero(genero);
        System.out.println("Estudiantes de genero: "+genero);
        imprimirLista(generoX);
        System.out.println("------------------------------------------------");
        /**
         * Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
        */
        List<CarreraDTO> carrerasInscriptos=repoCarrera.getCarrerasConInscriptos();
        imprimirCarrerasConInscriptos(carrerasInscriptos);
        System.out.println("--------------------------------------------------");
        /**
         * Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
        */
        String ciudad="La Boca";
        Carrera ca=repoCarrera.getCarrera(2L);
        List<Estudiante> estudiantesxCarrera=repoEstudiante.GetEstudiantesByCarrera(c,ciudad);
        imprimirAlumnosxCarreraFiltrado(estudiantesxCarrera, ciudad, ca.getCarrera());
        System.out.println("--------------------------------------------------------");
        /** Generar un reporte de las carreras, que para cada carrera incluya
         *  información de los inscriptos y egresados por año. Se deben ordenar
         *  las carreras alfabéticamente, y presentar los años de manera cronológica.
         */
        List<ReporteCarreraDTO> listaReportes=repoCarrera.getCarrerasConEgresados();
        imprimirReportes(listaReportes);
    }

    private static void imprimirCarrerasConInscriptos(List<CarreraDTO> carrerasInscriptos) {
        Iterator<CarreraDTO> it=carrerasInscriptos.iterator();
        System.out.println("Carreras ordenadas por cantidad de inscriptos: ");
        while (it.hasNext()){
            CarreraDTO carrera=it.next();
            System.out.println(carrera);
        }
    }

    private static void imprimirAlumnosxCarreraFiltrado(List<Estudiante> estudiantesxCarrera, String ciudad, String carrera) {
        Iterator<Estudiante> ite=estudiantesxCarrera.iterator();
        System.out.println("Alumnos de la carrera: "+ carrera + " provenientes de la ciudad "+ ciudad);
        while(ite.hasNext()){
            Estudiante estudiante=ite.next();
            System.out.println(estudiante);
        }
    }
    private static void imprimirReportes(List<ReporteCarreraDTO> listaReportes) {
        for (ReporteCarreraDTO rc : listaReportes) {
            System.out.println(rc.toString());
        }
    }


    private static void imprimirLista(List<Estudiante> lista) {
        Iterator<Estudiante> it=lista.iterator();
        while (it.hasNext()){
            Estudiante nuevo=it.next();
            System.out.println(nuevo.toString());
        }
        System.out.println();
    }
}
