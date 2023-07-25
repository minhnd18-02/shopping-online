/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import minhnd.entity.Category;
import minhnd.entity.Product;
import minhnd.utils.DBContext;

/**
 *
 * @author ADMIN
 */
public class ProductDAO {

    private static final String LIST_PRODUCT = "Select* from product Order by price";
    private static final String LIST_CATEGORY = "SELECT * FROM Category";
    private static final String LIST_PRODUCT_BY_CID = "SELECT * FROM product WHERE cateID = ?";
    private static final String DETAIL = "SELECT * FROM product WHERE id = ?";
    private static final String SEARCH = "SELECT * FROM product WHERE [name] like ?";
    private static final String LIST_PRODUCT_BY_SELLID = "SELECT * FROM product WHERE sell_ID = ? Order by price";
    private static final String DELETE_PRODUCT = "DELETE FROM product WHERE id = ?";
    private static final String INSERT_PRODUCT = "INSERT dbo.product ([name], [image], [price], [title], [description], [cateID], [sell_ID]) VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE_PRODUCT = "UPDATE product SET [name] = ?, [image] = ? ,[price] = ?, [title] = ?, [description] = ?, [cateID] = ? WHERE id = ? ";
    private static final String GET_PAGE = "Select count (*) From product";
    private static final String PAGING = "SELECT id, name FROM product ORDER BY id OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";
//    private static final String DESC = "Select* from product Order by price"
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LIST_PRODUCT);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    list.add(new Product (rs.getInt(1), rs.getString(2), 
                    rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6)));

                }
            }
        }catch (Exception e) {
            
        }
        return list;
    }
    
    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LIST_CATEGORY);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    list.add(new Category(rs.getInt(1), rs.getString(2)));
                }
            }
        }catch (Exception e) {
            
        }
        return list;
    }
    
    public List<Product> getProductByCID(String cid) {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LIST_PRODUCT_BY_CID);
                ptm.setString(1, cid);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    list.add(new Product (rs.getInt(1), rs.getString(2), 
                    rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6)));
                }
            }
        }catch (Exception e) {
            
        }
        return list;
    }
    
    public Product getProductByID(String id) {
        
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DETAIL);
                ptm.setString(1, id);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    return new Product (rs.getInt(1), rs.getString(2), 
                    rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6));
                }
            }
        }catch (Exception e) {
            
        }
        return null;
    }
    
    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + txtSearch + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    list.add(new Product (rs.getInt(1), rs.getString(2), 
                    rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6)));
                }
            }
        }catch (Exception e) {
            
        }
        return list;
    }
    
    public List<Product> getProductSellID(int sid) {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LIST_PRODUCT_BY_SELLID);
                ptm.setInt(1, sid);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    list.add(new Product (rs.getInt(1), rs.getString(2), 
                    rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6)));
                }
            }
        }catch (Exception e) {
            
        }
        return list;
    }
    
    public void deleteProduct (String pid){
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_PRODUCT);
                ptm.setString(1, pid);
                ptm.executeUpdate();

            }
        }catch (Exception e) {
            
        }
    }
    
    public void insertProduct(String name, String image, String price,
            String title, String description, String category, int sid){
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_PRODUCT);
                ptm.setString(1, name);
                ptm.setString(2, image);
                ptm.setString(3, price);
                ptm.setString(4, title);
                ptm.setString(5, description);
                ptm.setString(6, category);
                ptm.setInt(7, sid);
                ptm.executeUpdate();

            }
        }catch (Exception e) {
            
        }
    }
    
    public void updateProduct(String name, String image, String price,
            String title, String description, String category, String pid){
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PRODUCT);
                ptm.setString(1, name);
                ptm.setString(2, image);
                ptm.setString(3, price);
                ptm.setString(4, title);
                ptm.setString(5, description);
                ptm.setString(6, category);
                ptm.setString(7, pid);
                ptm.executeUpdate();

            }
        }catch (Exception e) {
            
        }
    }
    
    public int getNumberPage() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PAGE);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int total = rs.getInt(1);
                    int countPage = 0;
                    countPage = total / 3;
                    if (total % 3 != 0) {
                        countPage++;
                    }
                    return countPage;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return 0;
    }
    
//    public List<Product> pagingId(int index) throws SQLException {
//        List listPage = new ArrayList();
//        Connection conn = null;
//        PreparedStatement ptm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBContext.getConnnection();
//            if (conn != null) {
//                ptm = conn.prepareStatement(PAGING);
//                ptm.setInt(1, (index - 1)*10 );
//                rs = ptm.executeQuery();
//                while (rs.next()) {
//                    int id = Integer.parseInt(rs.getString("id"));
//                    String name = rs.getString("name");
//                    listPage.add(new Product(id, name));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ptm != null) {
//                ptm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return listPage;
//    }
    
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();
        for (Category category : listC) {
            System.out.println(category);
        }
    }
}
