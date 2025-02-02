package pe.edu.cibertec.auditoria_interna_covisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Empleado;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Evaluacion;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer> {

    //METODOS GENESIS DESPOUX
    List<Evaluacion> findByEmpleado(Empleado empleado);

    @Query("SELECT e FROM Evaluacion e WHERE e.llamada.numeroOrden = ?1")
    Evaluacion findByNumeroOrden(int numeroOrden);

    /*Metodo para obtener los detalles evaluación relacionada a una llamada( es para llamar los datos de una evaluacion especifica relacionada al numero_orden en relacion) para la vista del empleado*/
    @Query("SELECT e FROM Evaluacion e JOIN FETCH e.llamada WHERE e.idEvaluacion = :idEvaluacion AND e.llamada.numeroOrden = :numeroOrden")
    Evaluacion findEvaluacionAndLlamadaByIdAndNumeroOrden(@Param("idEvaluacion") Long idEvaluacion, @Param("numeroOrden") int numeroOrden);

    //METODO WILMER
    @Query(value = "SELECT * FROM Evaluaciones WHERE numero_orden = :numeroOrden", nativeQuery = true)
    Evaluacion findByOrden(@Param("numeroOrden") int numeroOrden);

    //Obteniendo Lista de Evaluaciones de distintos empleados por su area
    @Query("SELECT e FROM Evaluacion e JOIN e.empleado em WHERE em.area = :areaEmpleado")
    List<Evaluacion> listaEvaluacionesPorArea(@Param("areaEmpleado") String area);

    /*Metodo para obtener UNA Evaluacion por ID_EVALUACION
        Lo uso en mi AJAX de frmlistaevaluaciones de la carpeta lider*/
    @Query(value = "SELECT * FROM Evaluaciones WHERE id_evaluacion = :idEvaluacion", nativeQuery = true)
    Evaluacion obtenerEvaluacionPorId(@Param("idEvaluacion") int id);

    //METODOS PARA CHART JS (ANALISIS DE DATOS MEDIANTE JS) HUGO
    //VER AMBOS 2 PRIMEROS
    @Query(value = "select count(*) from Evaluaciones e inner join empleados em on e.dni_empleado = em.dni_empleado where em.area =:area AND e.estado_lider= 1", nativeQuery = true)
    int cantidadEvaluacionesVistasPorLider(@Param("area") String area);

    @Query(value = "select count(*) from Evaluaciones e inner join empleados em on e.dni_empleado = em.dni_empleado where em.area =:area AND e.estado_lider= 0", nativeQuery = true)
    int cantidadEvaluacionesNoVistasPorLider(@Param("area") String area);

    @Query(value = "select cast(avg(e.nota) as decimal(10,2)) from Evaluaciones e inner join empleados em on e.dni_empleado = em.dni_empleado where em.area =:area", nativeQuery = true)
    public Double promedioNotasPorArea(@Param("area") String area);

    Optional<List<Evaluacion>> findEvaluacionByNotaBetween(Integer from, Integer to);

    @Query(value = "SELECT e.id_evaluacion, emp.nombre_empleado, cli.nombre_cliente, e.observaciones_evaluacion, e.nota, l.numero_orden FROM Evaluaciones e JOIN Empleados emp ON e.dni_empleado = emp.dni_empleado JOIN Llamadas l ON e.numero_orden = l.numero_orden JOIN Cliente cli ON l.dni_cliente = cli.dni_cliente WHERE e.nota BETWEEN :from AND :to", nativeQuery = true)
    Optional<List<Object[]>> findEvaluacionByNotaBetweenn(@Param("from") Integer from, @Param("to") Integer to);

    @Query(value = "select count(*) from evaluaciones e inner join empleados em on e.dni_empleado = em.dni_empleado where em.area =:area", nativeQuery = true)
    int cantidadEvaluaciones(@Param("area") String area);

    @Query(value = "select count(*) from evaluaciones e inner join empleados em on e.dni_empleado = em.dni_empleado where em.area =:area and e.nota >30", nativeQuery = true)
    int cantidadEvaluacionesAprobadas(@Param("area") String area);

    @Query(value = "select e.* from Evaluaciones e inner join empleados em on e.dni_empleado = em.dni_empleado where em.area =:area and e.estado_lider = 0", nativeQuery = true)
    List<Evaluacion> listaEvaluacionNotification(@Param("area") String area);

}

