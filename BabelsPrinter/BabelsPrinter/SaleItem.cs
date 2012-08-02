using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace BabelsPrinter
{
    public class SaleItem
    {
        public static string TABLENAME = "Sale_Details";
        public static string COMBOTABLENAME = "Combos";
        public static string PRODTABLENAME = "Products";
        public static string FIELD_ID = "Id";
        public static string FIELD_IDMOVE = "IdMove";
        public static string FIELD_IDITEM = "IdItem";
        public static string FIELD_ITEMTYPE = "ItemType";

        public static string FIELD_NAME = "Name";
        public static string FIELD_DESCRIPTION = "Description";
        public static string FIELD_PRICE = "Price";

        public static string T_COMBO = "COMBO";
        public static string T_PROD = "PRODUCT";

        private int _Id;
        private string _Name;
        private string _Description;
        private double _Price;
        private int _IVA;
        private int _Amount;
        private string _Type;

        public int Id { get { return _Id; } set { _Id = value; } }
        public string Name { get { return _Name; } set { _Name = value; } }
        public string Description { get { return _Description; } set { _Description = value; } }
        public double Price { get { return _Price; } set { _Price = value; } }
        public int IVA { get { return _IVA; } set { _IVA = value; } }
        public int Amount { get { return _Amount; } set { _Amount = value; } }
        public string Type { get { return _Type; } set { _Type = value; } }
    }

    public class SaleItemList
    {
        public List<SaleItem> items;

        public SaleItemList()
        {
            items = new List<SaleItem>();
        }

        public void AddItem(SaleItem item)
        {
            bool exists = false;
            foreach (SaleItem i in items)
            {
                if (i.Id == item.Id && i.Type == item.Type)
                {
                    i.Amount++;
                    exists = true;
                    break;
                }
            }
            if (!exists)
            {
                item.Amount = 1;
                items.Add(item);
            }
        }
    }
}
