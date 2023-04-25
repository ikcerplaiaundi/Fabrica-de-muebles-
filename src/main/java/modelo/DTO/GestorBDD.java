package modelo.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class GestorBDD extends Conexion{
	public Boolean[] ChekUser(modelo.DAO.User user, String tabla) {
		
		String selectUsuarios = "SELECT * FROM ? WHERE Nombre= ?";
		Boolean[] Chek =new Boolean[2];
		Chek[0]=false;
		Chek[1]=false;
		PreparedStatement mostrarUsuarios;
		try {
			mostrarUsuarios = super.BBDDcon.prepareStatement(selectUsuarios);
			mostrarUsuarios.setString(1, tabla);
			mostrarUsuarios.setString(2, user.getNombre());
			ResultSet resultSet = mostrarUsuarios.executeQuery();
			resultSet.next();
			
			
			if(user.getNombre().equals(resultSet.getString("Nombre"))) {
				Chek[0]=true;
			}
			if(user.getContra().equals(resultSet.getString("Contra"))) {
				Chek[1]=true;
			}
		
		} catch (SQLException e1) {
			e1.printStackTrace();
			Chek[0]=false;
			Chek[1]=false;
		}
		
		return Chek;
	}
	
}
