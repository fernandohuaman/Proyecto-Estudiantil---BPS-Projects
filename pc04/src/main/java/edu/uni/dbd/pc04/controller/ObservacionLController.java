package edu.uni.dbd.pc04.controller;

import edu.uni.dbd.pc04.bean.ActividadLResponse;
import edu.uni.dbd.pc04.bean.InformeResponse;
import edu.uni.dbd.pc04.bean.ObservacionLResponse;
import edu.uni.dbd.pc04.bean.ObservacionResponse;
import edu.uni.dbd.pc04.request.IdRequest;
import edu.uni.dbd.pc04.request.ObservacionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@RestController
public class ObservacionLController {
    @Autowired
    JdbcTemplate template;
    @PostMapping("/listaObservaciones")
    public ArrayList<ObservacionLResponse> getObservaciones(@RequestBody IdRequest p) throws Exception{
        ArrayList<ObservacionLResponse> obs =new ArrayList<>();
        Connection conn = template.getDataSource().getConnection();
        String sql= "SELECT CAST(O.ID AS VARCHAR),  TO_CHAR( O.FECHA_CREAC,'DD-MM-YYYY' ), TO_CHAR(O.FECHA_CREAC,'HH:MM'), O.ESTADO FROM OBSERVACION O JOIN INFORME I ON O.CODIGO_INF = I.CODIGO WHERE I.CODIGO = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,p.getId());
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            obs.add(new ObservacionLResponse(rs.getString(1),rs.getString(3),rs.getString(2),rs.getString(4)));
        }
        rs.close();
        pst.close();
        conn.close();
        return obs;
    }

    @PostMapping("/obtenerObservacion")
    public ObservacionResponse getObservacion(@RequestBody IdRequest a) throws Exception{
        Connection conn = template.getDataSource().getConnection();
        String sql= "SELECT CAST(O.ID AS VARCHAR),TO_CHAR(O.FECHA_CREAC,'DD/MM/YYYY'), " +
                "TO_CHAR(O.FECHA_CREAC,'HH:MM' ), " +
                "COALESCE(TO_CHAR(O.FECHA_LEV,'DD/MM/YYYY'),'-'), " +
                "COALESCE(TO_CHAR(O.FECHA_LEV,'DD/MM/YYYY'),'-'), O.ESTADO, O.CONTENIDO " +
                "FROM OBSERVACION O " +
                "JOIN INFORME I ON O.CODIGO_INF = I.CODIGO " +
                "WHERE O.ID = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, Integer.parseInt(a.getId()));
        ResultSet rs = pst.executeQuery();
        rs.next();
        ObservacionResponse obs = new ObservacionResponse(rs.getString(1),rs.getString(3),rs.getString(5),rs.getString(2),rs.getString(4),rs.getString(6));
        rs.close();
        pst.close();
        conn.close();
        return obs;
    }



}
