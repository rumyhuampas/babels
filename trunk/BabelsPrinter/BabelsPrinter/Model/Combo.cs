using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MySQLDriverCS;

namespace BabelsPrinter.Model
{
    public class Combo
    {
        public static string TABLENAME = "Combos";
        public static string CPTABLENAME = "Combo_Products";
        public static string FIELD_ID = "Id";
        public static string FIELD_NAME = "Name";
        public static string FIELD_DESCRIPTION = "Description";
        public static string FIELD_PRICE = "Price";
        public static string FIELD_IDCOMBO = "IdCombo";
        public static string FIELD_IDPROD = "IdProduct";

        private MySQLConnection Conn;
        private int _Id;
        private string _Name;
        private string _Description;
        private double _Price;
        private List<Product> _Products;

        public int Id { get { return _Id; } set { _Id = value; } }
        public string Name { get { return _Name; } set { _Name = value; } }
        public string Description { get { return _Description; } set { _Description = value; } }
        public double Price { get { return _Price; } set { _Price = value; } }
        public List<Product> Products { get { return _Products; } set { _Products = value; } }

        public Combo(MySQLConnection conn)
        {
            Conn = conn;
            Products = new List<Product>();
        }

        public void Load(int id)
        {
            string sql = "SELECT * FROM " + TABLENAME +
                " WHERE " + FIELD_ID + "= " + id.ToString();
            MySQLCommand comm = new MySQLCommand(sql, Conn);
            try
            {
                MySQLDataReader reader = comm.ExecuteReaderEx();
                if (reader.HasRows)
                {
                    reader.Read();
                    this.Id = reader.GetInt32(reader.GetOrdinal(FIELD_ID));
                    this.Name = reader.GetString(reader.GetOrdinal(FIELD_NAME));
                    this.Description = "";
                    if (!reader.IsDBNull(reader.GetOrdinal(FIELD_DESCRIPTION)))
                    {
                        this.Description = reader.GetString(reader.GetOrdinal(FIELD_DESCRIPTION));
                    }
                    this.Price = reader.GetDouble(reader.GetOrdinal(FIELD_PRICE));

                    LoadProducts(id);
                }
            }
            catch (Exception ex) { }
            finally
            {
                comm.Dispose();
            }
        }

        private void LoadProducts(int idCombo){
            string sql = "SELECT * FROM " + CPTABLENAME +
                " WHERE " + FIELD_IDCOMBO + "= " + idCombo.ToString();
            MySQLCommand comm = new MySQLCommand(sql, Conn);
            try
            {
                MySQLDataReader reader = comm.ExecuteReaderEx();
                while (reader.Read())
                {
                    Product prod = new Product(this.Conn);
                    prod.Load(reader.GetInt32(reader.GetOrdinal(FIELD_IDPROD)));
                    this.Products.Add(prod);
                }
            }
            catch (Exception ex) { }
            finally
            {
                comm.Dispose();
            }
        }
    }
}
