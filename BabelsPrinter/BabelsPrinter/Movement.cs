﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MySQLDriverCS;

namespace BabelsPrinter
{
    public class Movement
    {
        public static string TABLENAME = "Movements";
        public static string FIELD_ID = "Id";
        public static string FIELD_TYPE = "Type";
        public static string FIELD_DATEPOSTED = "DatePosted";
        public static string FIELD_AMOUNT = "Amount";
        public static string FIELD_IDUSER = "IdUser";
        public static string FIELD_DESCRIPTION = "Description";

        public const string MT_VENTAA = "VENTA_A";
        public const string MT_VENTAB = "VENTA_B";
        public const string MT_CANCELACION = "CANCELACION";
        public const string MT_APERTURA = "APERTURA";
        public const string MT_CIERRE = "CIERRE";
        public const string MT_CIERREPARCIAL = "CIERREPARCIAL";
        public const string MT_DEPOSITO = "DEPOSITO";
        public const string MT_EXTRACCION = "EXTRACCION";

        private MySQLConnection Conn;
        private int _Id;
        private MovementType _Type;
        private DateTime _DatePosted;
        private double _Amount;
        private int _IdUser;
        private string _Description;
        private List<SaleItem> _Items;

        public int Id { get { return _Id; } set { _Id = value; } }
        public MovementType Type { get { return _Type; } set { _Type = value; } }
        public DateTime DatePosted { get { return _DatePosted; } set { _DatePosted = value; } }
        public double Amount { get { return _Amount; } set { _Amount = value; } }
        public int IdUser { get { return _IdUser; } set { _IdUser = value; } }
        public string Description { get { return _Description; } set { _Description = value; } }
        public List<SaleItem> Items { get { return _Items; } set { _Items = value; } }

        public Movement(MySQLConnection conn)
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
                    MovementType type = new MovementType(Conn);
                    type.Load(reader.GetInt32(reader.GetOrdinal(FIELD_TYPE)));
                    this.Type = type;
                    this.DatePosted = reader.GetDateTime(reader.GetOrdinal(FIELD_DATEPOSTED));
                    this.Amount = reader.GetDouble(reader.GetOrdinal(FIELD_AMOUNT));
                    if (!reader.IsDBNull(reader.GetOrdinal(FIELD_IDUSER)))
                    {
                        this.IdUser = reader.GetInt32(reader.GetOrdinal(FIELD_IDUSER));
                    }
                    if (!reader.IsDBNull(reader.GetOrdinal(FIELD_DESCRIPTION)))
                    {
                        this.Description = reader.GetString(reader.GetOrdinal(FIELD_DESCRIPTION));
                    }
                    this.Items = GetItems(this.Id);
                }
            }
            catch (Exception ex) { }
            finally
            {
                comm.Dispose();
            }
        }

        private List<SaleItem> GetItems(int saleId)
        {
            List<SaleItem> list = new List<SaleItem>();

            string sql = "SELECT * FROM " + SaleItem.TABLENAME +
                " WHERE " + SaleItem.FIELD_IDMOVE + "= " + saleId.ToString();
            MySQLCommand comm = new MySQLCommand(sql, Conn);
            try
            {
                MySQLDataReader reader = comm.ExecuteReaderEx();
                string itemType="";
                int itemId=-1;
                while (reader.Read())
                {
                    itemType = "";
                    itemId = -1;
                    itemType = reader.GetString(reader.GetOrdinal(SaleItem.FIELD_ITEMTYPE));
                    itemId = reader.GetInt32(reader.GetOrdinal(SaleItem.FIELD_IDITEM));
                    if (itemType == "COMBO")
                    {
                        sql = "SELECT * FROM " + SaleItem.COMBOTABLENAME +
                            " WHERE " + SaleItem.FIELD_ID + "= " + itemId;
                    }
                    else
                    {
                        sql = "SELECT * FROM " + SaleItem.PRODTABLENAME +
                            " WHERE " + SaleItem.FIELD_ID + "= " + itemId;
                    }
                    MySQLCommand commItems = new MySQLCommand(sql, Conn);
                    try
                    {
                        MySQLDataReader readerItems = commItems.ExecuteReaderEx();
                        if (readerItems.HasRows)
                        {
                            readerItems.Read();
                            SaleItem item = new SaleItem();
                            item.Id = readerItems.GetInt32(readerItems.GetOrdinal(SaleItem.FIELD_ID));
                            item.Name = readerItems.GetString(readerItems.GetOrdinal(SaleItem.FIELD_NAME));
                            item.Description = readerItems.GetString(readerItems.GetOrdinal(SaleItem.FIELD_DESCRIPTION));
                            item.Price = readerItems.GetDouble(readerItems.GetOrdinal(SaleItem.FIELD_PRICE));
                            item.IVA = 21;

                            list.Add(item);
                        }
                    }
                    catch (Exception ex) { }
                    finally
                    {
                        commItems.Dispose();
                    }
                }
                return list;
            }
            finally
            {
                comm.Dispose();
            }
        }
    }
}
