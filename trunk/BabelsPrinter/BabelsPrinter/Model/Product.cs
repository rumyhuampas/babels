using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MySQLDriverCS;

namespace BabelsPrinter.Model
{
    public class Product
    {
        public static string TABLENAME = "Products";
        public static string FIELD_ID = "Id";
        public static string FIELD_NAME = "Name";
        public static string FIELD_DESCRIPTION = "Description";
        public static string FIELD_PRICE = "Price";
        public static string FIELD_IDKITCHEN = "IdKitchen";

        private MySQLConnection Conn;
        private int _Id;
        private string _Name;
        private string _Description;
        private double _Price;
        private int _IdKitchen;

        public int Id { get { return _Id; } set { _Id = value; } }
        public string Name { get { return _Name; } set { _Name = value; } }
        public string Description { get { return _Description; } set { _Description = value; } }
        public double Price { get { return _Price; } set { _Price = value; } }
        public int IdKitchen { get { return _IdKitchen; } set { _IdKitchen = value; } }

        public Product(MySQLConnection conn)
        {
            Conn = conn;
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
                    this.IdKitchen = reader.GetInt32(reader.GetOrdinal(FIELD_IDKITCHEN));
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
