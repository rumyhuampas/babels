﻿using System;

namespace BabelsPrinter
{
    public class PrintJob
    {
        public static string TABLENAME = "Prints";
        public static string FIELD_ID = "Id";
        public static string FIELD_IDMOVE = "IdMove";
        public static string FIELD_DATEPOSTED = "DatePosted";
        public static string FIELD_STATUS = "Status";
        public static string FIELD_DATEPRINTED = "DatePrinted";
        public static string FIELD_PRINTER = "Printer";
        public static string FIELD_RETRIES = "Retries";
        public static string FIELD_DATA = "Data";

        public static string ST_PEND = "PENDING";
        public static string ST_PRIN = "PRINTING";
        public static string ST_COMP = "COMPLETED";
        public static string ST_FAIL = "FAILED";

        private int _Id;
        private Movement _Move;
        private DateTime _DatePosted;
        private string _Status;
        private DateTime _DatePrinted;
        private string _Printer;
        private int _Restries;
        private string _Data;

        public int Id { get { return _Id; } set { _Id = value; } }
        public Movement Move { get { return _Move; } set { _Move = value; } }
        public DateTime DatePosted { get { return _DatePosted; } set { _DatePosted = value; } }
        public string Status { get { return _Status; } set { _Status = value; } }
        public DateTime DatePrinted { get { return _DatePrinted; } set { _DatePrinted = value; } }
        public string Printer { get { return _Printer; } set { _Printer = value; } }
        public int Retries { get { return _Restries; } set { _Restries = value; } }
        public string Data { get { return _Data; } set { _Data = value; } }
    }
}
