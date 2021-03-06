package BLL;

import DLL.DBConnect;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PackageProductSupplierDB {


    public static List<PackageProductSupplier> getPckgProductSuppliers() {
        ArrayList<PackageProductSupplier> pckgProductSuppliers = null;


        try{
            // instantiate DB connection
            Connection conn = DBConnect.getConnection();

            // instantiate query
            Statement query = conn.createStatement();

            // query statement
            String q = "select PackageId, PkgName, ProductSupplierId, SupName from packages_products_suppliers " +
                    "left join packages using (PackageId) " +
                    "right join products_suppliers using (ProductSupplierId) " +
                    "inner join suppliers using (SupplierId)";
            // on products_suppliers.ProductId=products.ProductId
            // on products_suppliers.SupplierId=suppliers.SupplierId
            // execute statement
            ResultSet rs = query.executeQuery(q);

            // assign products arrayList
            pckgProductSuppliers = new ArrayList<>();

            // retrieve result set and add to product arrays
            while(rs.next()){
                pckgProductSuppliers.add(new PackageProductSupplier(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4)));
            }

            rs.close();
            conn.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return pckgProductSuppliers;
    }

    public static void addPackageProductSupplier(PackageProductSupplier pckProdSupplier){
        try{
            // instantiate DB connection
            Connection conn = DBConnect.getConnection();

            // call prepared statement
            PreparedStatement stmt = conn.prepareStatement(
                    "Insert into packages_products_suppliers values(?, ?)");

            stmt.setInt(1, pckProdSupplier.getPackageId());
            stmt.setInt(2, pckProdSupplier.getProductSupplierId());

            // checks if the data was inserted

            int numInserts = stmt.executeUpdate();

            if (numInserts == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Package Product Supplier not added. Please try again or contact Tech Support.");
                alert.showAndWait();
            }
            stmt.close();
            conn.close();

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void updatePackageProductSupplier(PackageProductSupplier pckProdSupplier){
        try
        {
            // instantiate DB connection
            Connection conn = DBConnect.getConnection();

            // call prepared statement
            PreparedStatement stmt = conn.prepareStatement(
                    "update packages_products_suppliers set PackageId=?, ProductSupplierId=? where PackageId=?");

            stmt.setInt(1, pckProdSupplier.getPackageId());
            stmt.setInt(2, pckProdSupplier.getProductSupplierId());
            stmt.setInt(3, pckProdSupplier.getPackageId());

            //checks if the data was inserted
            int numInserts = stmt.executeUpdate();
            if (numInserts == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Package product supplier update failed. Please try again or contact Tech Support.");
                alert.showAndWait();
            }
            stmt.close();
            conn.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    //Searches package product suppliers in response to text input
    public static List<PackageProductSupplier> searchPckgProductsSuppliers(String para) {
        List<PackageProductSupplier> pckgProductSuppliers = null;

        try {

            //Instantiate DB connection
            Connection conn = DBConnect.getConnection();


            String q = "select PackageId, PkgName, ProductSupplierId, SupName from packages_products_suppliers " +
                    "left join packages using (PackageId) " +
                    "right join products_suppliers using (ProductSupplierId) " +
                    "inner join suppliers using (SupplierId) " +
                    "where PackageId like ? or " +
                    "PkgName like ? or " +
                    "ProductSupplierId like ? or " +
                    "SupName like ?";

            //Prepare an sql statement
            PreparedStatement stmt = conn.prepareStatement(q);
            stmt.setString(1, '%' + para + '%');
            stmt.setString(2, '%' + para + '%');
            stmt.setString(3, '%' + para + '%');
            stmt.setString(4, '%' + para + '%');

            //Executes statement
            ResultSet rs = stmt.executeQuery();

            pckgProductSuppliers = new ArrayList<>();

            //runs while reader has data
            while (rs.next()) {
                pckgProductSuppliers.add(new PackageProductSupplier(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4)));
            }
            rs.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pckgProductSuppliers;
    }

}
