
/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


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
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

