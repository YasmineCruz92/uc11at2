
/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto (ProdutosDTO produto){
        
        try {
        
            conn = new conectaDAO().connectDB();

            String query = 
                "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, produto.getNome());
            preparedStmt.setInt(2,produto.getValor());
            preparedStmt.setString(3, produto.getStatus());
            
            preparedStmt.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            System.out.println("Deu erro " + e.getMessage());
            return false;
        }
        
    }
    
      public void desconectar() { // método que vai desconectar do banco de dados 
        try {
            conn.close();
            System.out.println("Desconectado!");
        } catch (SQLException ex) {

        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
   String sql = "SELECT * FROM produtos";
    try {
        conn = new conectaDAO().connectDB();

        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        List<ProdutosDTO> listaProdutos = new ArrayList<>();
        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));
            listaProdutos.add(produto);
        }
        return (ArrayList<ProdutosDTO>) listaProdutos;
    } catch (SQLException ex) {
        System.out.println("Erro ao conectar: " + ex.getMessage());
        return null;
    }
}

   
   
}
    
    
        

