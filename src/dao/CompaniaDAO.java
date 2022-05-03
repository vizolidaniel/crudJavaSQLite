
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import acesso.Database;


public class CompaniaDAO {
    private Connection con;
	
	public CompaniaDAO() {
		con = Database.getInstance().getConnection();
	}
	
	
	public void insert(Compania compania) throws SQLException {
		
		PreparedStatement stat = con.prepareStatement(
				"insert into compania values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		stat.setString(1, compania.getId());
		stat.setString(2, compania.getNome());
		stat.setString(3, compania.getDominio());
		stat.setString(4, compania.getAno());
		stat.setString(5, compania.getIndustria());
		stat.setString(6, compania.getTamanho());
		stat.setString(7, compania.getLocalizacao());
		stat.setString(8, compania.getPais());
		stat.setString(9, compania.getLinkedin());
		stat.setInt(10, compania.getEmpregados_atual());
		stat.setInt(11, compania.getEmpregados_total());
		
		stat.execute();
		
		stat.close();
	}
	
	
	public void delete(String nome) throws SQLException {
		Statement stat = con.createStatement();
		stat.executeUpdate("delete from compania where compania.name like '%" + nome + "%'");
		stat.close();
	}
	
	
	public List<Compania> getAll() throws SQLException {
		List<Compania> companias = new ArrayList<Compania>();
		
		Statement stat = con.createStatement();
		ResultSet rs = stat.executeQuery("select * from compania");
		
		while (rs.next()) {
			// read the result set
			Compania p = new Compania();
			//p.setId(rs.getString("id"));
                        p.setNome(rs.getString("name"));
                        p.setPais(rs.getString("country"));
			p.setDominio(rs.getString("domain"));
			p.setAno(rs.getString("year founded"));
			p.setIndustria(rs.getString("industry"));
			p.setTamanho(rs.getString("size range"));
			p.setLocalizacao(rs.getString("locality"));
			p.setLinkedin(rs.getString("linkedin url"));
			p.setEmpregados_atual(rs.getInt("current employee estimate"));
			p.setEmpregados_total(rs.getInt("total employee estimate"));
			companias.add(p);
		}
		
		return companias;
	}
	
	
	public Compania getByName(String nome) throws SQLException {
		
		Statement stat = con.createStatement();
		ResultSet rs = stat.executeQuery("select * from compania where name LIKE"+ "'%" + nome +"%'");
		
		if (rs.next()) {
			Compania p = new Compania();
			p.setNome(rs.getString("name"));
                        p.setPais(rs.getString("country"));
			p.setDominio(rs.getString("domain"));
			p.setAno(rs.getString("year founded"));
			p.setIndustria(rs.getString("industry"));
			p.setTamanho(rs.getString("size range"));
			p.setLocalizacao(rs.getString("locality"));
			p.setLinkedin(rs.getString("linkedin url"));
			p.setEmpregados_atual(rs.getInt("current employee estimate"));
			p.setEmpregados_total(rs.getInt("total employee estimate"));
			return p;
		}
		
		stat.close();
		return null;
		
	}
	
	
	public void update(Compania p, String nome) throws SQLException {
		
		Statement stat = con.createStatement();
		/*
		 *  UPDATE table
			SET column_1 = new_value_1,
			    column_2 = new_value_2
			WHERE
			    search_condition
		 */
		stat.executeUpdate("update compania set name = '" + p.getNome() + "' " + 
								 " where compania.name LIKE '%" + nome +"%'");
		
		stat.close();
	}
}
